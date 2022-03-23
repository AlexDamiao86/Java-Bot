package telegram_bot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Interacao {

	private Conversa conversa;
	private String estimulo;
	// private Pedido pedido = null;

	public Interacao(Conversa conversa, String estimulo) {
		this.conversa = conversa;
		this.estimulo = estimulo;
	}

	public String getEstimulo() {
		return estimulo;
	}

	private boolean ehSaudacao() {
		return estimulo.matches("^(o|O)i{0,1}|^(B|b)om (D|d)ia{0,1}|^(B|b)oa (t|T)arde{0,1}|^(B|b)oa (n|N)oite{0,1}");
	}

	private void verificarSeMudaEstado() {

		switch (conversa.getInteracaoAtual().name()) {
		case "INICIO":
			if (estimulo.equalsIgnoreCase("PEDIDO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
			}
			if (estimulo.equalsIgnoreCase("AJUDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
			}
			if (estimulo.equalsIgnoreCase("SAIR")) {
				conversa.encerrarConversa();
			}
			break;
		case "PEDIDO_PRODUTO":
			if (estimulo.equalsIgnoreCase("SAIR")) {
				conversa.encerrarConversa();
			}
			if (Bebida.ehBebidaValida(estimulo)) {
				Pedido pedido = new Pedido(conversa.getCliente().getConta(), Bebida.getBebidaPorDescricao(estimulo));
				conversa.getCliente().getConta().incluirPedidoConta(pedido);
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_QUANTIDADE);
			}
			break;
		case "PEDIDO_QUANTIDADE":
			if (estimulo.matches("[0-9]+")) {
				Pedido pedido = conversa.getCliente().getConta().getUltimoPedido();
				pedido.setQuantidade(Integer.parseInt(estimulo));
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_ADICIONADO);
			}
			break;
		case "PEDIDO_ADICIONADO":
			if (estimulo.equalsIgnoreCase("ADICIONAR BEBIDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
			}
			if (estimulo.equalsIgnoreCase("MOSTRAR PARCIAL")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_PARCIAL);
			}
			if (estimulo.equalsIgnoreCase("FECHAR CONTA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
			}
			break;
		case "PEDIDO_AJUDA":
			if (estimulo.equalsIgnoreCase("PEDIDO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
			}
			if (conversa.getCliente().isContaAberta()) {
				if (estimulo.equalsIgnoreCase("MOSTRAR PARCIAL")) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_PARCIAL);
				}
				if (estimulo.equalsIgnoreCase("FECHAR CONTA")) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
				}
			}
			break;
		case "CONTA_PARCIAL":
			conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
			break;
		case "CONTA_ENCERRA":
			break;
		default:
			break;
		}

	}

	public RespostaBot devolverResposta() {

		RespostaBot resposta = null;
		ArrayList<String> texto = new ArrayList<String>();

		if (ehSaudacao()) {
			if (!conversa.getCliente().isContaAberta()) {
				texto.add("Joinha?");
				this.conversa.mudarInteracaoAtual(EstadoInteracao.INICIO);
			} else {
				// Aqui foi feita uma saudação e possui conta está aberta
				texto.add("Belezinha? O que deseja?");
				this.conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
			}
		}

		verificarSeMudaEstado();

		switch (conversa.getInteracaoAtual().name()) {
		case "INICIO":
			texto.add("Oi, " + conversa.getCliente().getNome() + ", " + this.mostraSaudacao()
					+ "! Seja bem-vindo ao BarBot!");
			texto.add("Hoje o dia está bem quente, ideal para um happy hour com os amigos!");
			texto.add("Selecione PEDIDO ou AJUDA para consultar a lista de opções:");

			// Alternativa para usar teclado com botoes na mesma linha
			// String[] botoesMenuInicial = new String[2];
			// botoesMenuInicial[0] = "PEDIDO";
			// botoesMenuInicial[1] = "AJUDA";
			// Keyboard keyMenuInicial = new
			// ReplyKeyboardMarkup(botoesMenuInicial).resizeKeyboard(true).oneTimeKeyboard(true);

			Keyboard keyInicio = new ReplyKeyboardMarkup(new KeyboardButton[] { new KeyboardButton("PEDIDO") },
					new KeyboardButton[] { new KeyboardButton("AJUDA") },
					new KeyboardButton[] { new KeyboardButton("SAIR") }).resizeKeyboard(true).oneTimeKeyboard(true);

			resposta = new RespostaBot(texto, keyInicio);
			break;
		case "PEDIDO_PRODUTO":
			texto.add(conversa.getCliente().getNome() + ", me diga qual bebida deseja: ");

			String[] botoesBebidas = new String[Bebida.values().length];

			for (Bebida bebida : Bebida.values()) {
				botoesBebidas[bebida.getIdentificador() - 1] = bebida.getDescricao();
			}

			Keyboard keyPedidoProduto = new ReplyKeyboardMarkup(Arrays.copyOfRange(botoesBebidas, 0, 4))
					.resizeKeyboard(true).oneTimeKeyboard(true)
					.addRow(new KeyboardButton[] { new KeyboardButton("SAIR") });

			resposta = new RespostaBot(texto, keyPedidoProduto);

			break;
		case "PEDIDO_QUANTIDADE":
			texto.add("Digite a quantidade da bebida selecionada:");
			resposta = new RespostaBot(texto);
			break;
		case "PEDIDO_ADICIONADO":
			texto.add("Pedido adicionado com sucesso!");
			texto.add("Quer continuar seu pedido?");
			Keyboard keyMaisPedidos = new ReplyKeyboardMarkup(
					new KeyboardButton[] { new KeyboardButton("ADICIONAR BEBIDA") },
					new KeyboardButton[] { new KeyboardButton("MOSTRAR PARCIAL") },
					new KeyboardButton[] { new KeyboardButton("FECHAR CONTA") }).resizeKeyboard(true)
							.oneTimeKeyboard(true);

			resposta = new RespostaBot(texto, keyMaisPedidos);
			break;
		case "PEDIDO_AJUDA":
			texto.add("Selecione uma das seguintes opções:");

			Keyboard keyAjuda = new ReplyKeyboardMarkup(new KeyboardButton[] { new KeyboardButton("PEDIDO") },
					new KeyboardButton[] { new KeyboardButton("MOSTRAR PARCIAL") },
					new KeyboardButton[] { new KeyboardButton("FECHAR CONTA") }).resizeKeyboard(true)
							.oneTimeKeyboard(true);

			resposta = new RespostaBot(texto, keyAjuda);

			break;
		case "CONTA_PARCIAL":
			texto.add(conversa.getCliente().getConta().mostrarParcialConta());
			resposta = new RespostaBot(texto);
			break;
		case "CONTA_ENCERRA":
			texto.add(conversa.getCliente().getConta().encerrarConta());
			texto.add("Conta Paga! Valeu pela gorjeta!");
			texto.add("Tchau, até logo " + conversa.getCliente().getNome() + ", " + this.mostraSaudacao() + "!");
			texto.add("Te espero por aqui em uma próxima visita!!");
			resposta = new RespostaBot(texto);
			conversa.encerrarConversa();
			break;
		case "FIM":
			texto.add("Tchau, até logo " + conversa.getCliente().getNome() + ", " + this.mostraSaudacao() + "!");
			texto.add("Te espero por aqui em uma próxima visita!!");
			resposta = new RespostaBot(texto);
			break;
		default:
			texto.add("Nao entendi.. Tecle AJUDA para ver as opções disponiveis");
			resposta = new RespostaBot(texto);
		}

		return resposta;
	}

	private String mostraSaudacao() {

		String saudacao = "";
		int horaAtual = LocalDateTime.now().getHour();

		if (horaAtual <= 4) {
			saudacao = "boa noite";
		} else if (horaAtual <= 11) {
			saudacao = "bom dia";
		} else if (horaAtual <= 17) {
			saudacao = "boa tarde";
		} else {
			saudacao = "boa noite";
		}

		return saudacao;
	}

}