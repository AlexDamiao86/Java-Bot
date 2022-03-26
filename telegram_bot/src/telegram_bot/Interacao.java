package telegram_bot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import api_clima.ClimaAPI;

public class Interacao {

	private Conversa conversa;
	private String estimulo;

	public Interacao(Conversa conversa, String estimulo) {
		this.conversa = conversa;
		this.estimulo = estimulo;
	}

	public String getEstimulo() {
		return estimulo;
	}

	private String sugerirBebidaPorClima() {
		Double temp = 0.0;
		String sugestao = "";
		try {
			temp = ClimaAPI.obtemDadosTemperaturaNumero("Brasilia");
			if (temp < 18) {
				sugestao = "Hoje está frio em Brasilia: " + temp + "ºC, te recomendo um vinho! O que acha?";
			} else if (temp > 24.5) {
				sugestao = "Hoje está quente em Brasília: " + temp + "ºC, vamos de cerveja?";
			} else {
				sugestao = "Hoje está uma temperatura bem agradável em Brasília: " + temp
						+ "ºC, por que nao prova uma Caipirinha?";
			}
			return sugestao;
		} catch (Exception e) {
			return sugestao = "O clima está dificil de prever hoje.. aceita um suco?";
		}
	}

	private boolean ehSaudacao() {
		return estimulo.matches("^(o|O)i{0,1}|^(B|b)om (D|d)ia{0,1}|^(B|b)oa (t|T)arde{0,1}|^(B|b)oa (n|N)oite{0,1}");
	}

	private void verificarSeMudaEstado() {

		switch (conversa.getInteracaoAtual().name()) {
		case "INICIO":
			if (estimulo.equalsIgnoreCase("FAZER PEDIDO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("AJUDA") || estimulo.equalsIgnoreCase("/AJUDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("SUGESTAO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_SUGESTAO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("MENU BEBIDAS")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_MENU);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("SAIR")) {
				conversa.encerrarConversa();
			}
			break;
		case "PEDIDO_SUGESTAO":
			if (estimulo.equalsIgnoreCase("FAZER PEDIDO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("AJUDA") || estimulo.equalsIgnoreCase("/AJUDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("SAIR")) {
				if (conversa.getCliente().isContaAberta()) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
				} else {
					conversa.encerrarConversa();
				}
			}
			break;
		case "PEDIDO_MENU":
			if (estimulo.equalsIgnoreCase("FAZER PEDIDO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("AJUDA") || estimulo.equalsIgnoreCase("/AJUDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("SAIR")) {
				if (conversa.getCliente().isContaAberta()) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
				} else {
					conversa.encerrarConversa();
				}
			}
			break;
		case "PEDIDO_PRODUTO":
			if (estimulo.equalsIgnoreCase("SAIR")) {
				if (conversa.getCliente().isContaAberta()) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
				} else {
					conversa.encerrarConversa();
				}
			}
			if (conversa.getCliente().isContaAberta()) {
				if (estimulo.equalsIgnoreCase("MOSTRAR PARCIAL")) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_PARCIAL);
				}
			}
			if (estimulo.equalsIgnoreCase("AJUDA") || estimulo.equalsIgnoreCase("/AJUDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
				estimulo = "";
			}
			if (Bebida.ehBebidaValida(estimulo)) {
				Pedido pedido = new Pedido(conversa.getCliente().getConta(), Bebida.getBebidaPorDescricao(estimulo));
				conversa.getCliente().getConta().incluirPedidoConta(pedido);
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_QUANTIDADE);
				estimulo = "";
			}
			break;
		case "PEDIDO_QUANTIDADE":
			if (estimulo.matches("[0-9]+")) {
				Pedido pedido = conversa.getCliente().getConta().getUltimoPedido();
				pedido.setQuantidade(Integer.parseInt(estimulo));
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_ADICIONADO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("ALTERAR BEBIDA")) {
				conversa.getCliente().getConta().excluirUltimoPedidoConta();
				if (conversa.getCliente().getConta().getPedidos().size() == 0) {
					conversa.getCliente().getConta().encerrarConta();
				}
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
				estimulo = "";
			}
			break;
		case "PEDIDO_ADICIONADO":
			if (estimulo.equalsIgnoreCase("ADICIONAR BEBIDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("MOSTRAR PARCIAL")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_PARCIAL);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("FECHAR CONTA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("AJUDA") || estimulo.equalsIgnoreCase("/AJUDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
				estimulo = "";
			}
			break;
		case "PEDIDO_AJUDA":
			if (estimulo.equalsIgnoreCase("FAZER PEDIDO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("SUGESTAO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_SUGESTAO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("MENU BEBIDAS")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_MENU);
				estimulo = "";
			}
			if (conversa.getCliente().isContaAberta()) {
				if (estimulo.equalsIgnoreCase("MOSTRAR PARCIAL")) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_PARCIAL);
					estimulo = "";
				}
				if (estimulo.equalsIgnoreCase("FECHAR CONTA")) {
					conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
					estimulo = "";
				}
			}
			break;
		case "CONTA_PARCIAL":
			if (estimulo.equalsIgnoreCase("CONTINUAR PEDINDO")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("FECHAR CONTA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
				estimulo = "";
			}
			if (estimulo.equalsIgnoreCase("AJUDA") || estimulo.equalsIgnoreCase("/AJUDA")) {
				conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
				estimulo = "";
			}
			break;
		case "CONTA_ENCERRA":
			conversa.mudarInteracaoAtual(EstadoInteracao.INICIO);
			break;
		case "FIM": 
			conversa.mudarInteracaoAtual(EstadoInteracao.INICIO);
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
			texto.add("Deseja uma SUGESTAO? Ou já gostaria de FAZER PEDIDO? Deseja ver o MENU?");
			texto.add("Você pode ainda informar AJUDA para consultar a lista de opções.");

			Keyboard keyInicio = new ReplyKeyboardMarkup(
					new String[] { "Sugestao", "Menu Bebidas" }, 
					new String[] { "Fazer Pedido", "Ajuda"},
					new String[] { "Sair" }).resizeKeyboard(true).oneTimeKeyboard(true);

			resposta = new RespostaBot(texto, keyInicio);
			break;
		case "PEDIDO_MENU":
			texto.add(Bebida.mostrarMenuBebidas());

			Keyboard keyMenu = new ReplyKeyboardMarkup(
					new String[] { "Fazer Pedido", "Ajuda" }, 
            		new String[] { "Sair" }).resizeKeyboard(true).oneTimeKeyboard(true);

			resposta = new RespostaBot(texto,keyMenu);
			break;
		case "PEDIDO_SUGESTAO":
			texto.add(sugerirBebidaPorClima());

			Keyboard keySugestao = new ReplyKeyboardMarkup(
					new String[] { "Fazer Pedido", "Ajuda" },
					new String[] { "Sair" }).resizeKeyboard(true).oneTimeKeyboard(true);

			resposta = new RespostaBot(texto, keySugestao);
			break;
		case "PEDIDO_PRODUTO":
			if (!Bebida.ehBebidaValida(estimulo) && (estimulo != "")) {
				texto.add("Humm.. nao entendi.. Poderia repetir o pedido?");
			}
			texto.add(conversa.getCliente().getNome() + ", qual bebida gostaria? ");

			String[] botoesBebidas = new String[Bebida.values().length];

			for (Bebida bebida : Bebida.values()) {
				botoesBebidas[bebida.getIdentificador() - 1] = bebida.getDescricao();
			}
			
			String[] botoesBebidasLinha1 = Arrays.copyOfRange(botoesBebidas, 0, 3);
			String[] botoesBebidasLinha2 = Arrays.copyOfRange(botoesBebidas, 3, 6);
			String[] botoesBebidasLinha3 = Arrays.copyOfRange(botoesBebidas, 6, 9);
			
			Keyboard keyPedido = null;
			if (conversa.getCliente().isContaAberta()) {
				 keyPedido = new ReplyKeyboardMarkup(
						botoesBebidasLinha1, 
						botoesBebidasLinha2, 
						botoesBebidasLinha3)
						.resizeKeyboard(true).oneTimeKeyboard(true)
						.addRow(new String[] { "Mostrar Parcial" })
						.addRow(new String[] { "Sair" });
			} else {
				 keyPedido = new ReplyKeyboardMarkup(
							botoesBebidasLinha1, 
							botoesBebidasLinha2, 
							botoesBebidasLinha3)
							.resizeKeyboard(true).oneTimeKeyboard(true)
							.addRow(new String[] { "Sair" });
				
			}
			resposta = new RespostaBot(texto, keyPedido);

			break;
		case "PEDIDO_QUANTIDADE":
			if (!estimulo.matches("[0-9]+") && (estimulo != "")) {
				texto.add("Nao consegui anotar o pedido, me diga um numero maior que 0..");
			}
			texto.add("Digite a quantidade da bebida selecionada:");
			Keyboard keyQuantidade = new ReplyKeyboardMarkup(new String[] { "1", "2", "3", "4", "5" },
					new String[] { "Alterar Bebida" }).resizeKeyboard(true).oneTimeKeyboard(true);
			resposta = new RespostaBot(texto, keyQuantidade);
			break;
		case "PEDIDO_ADICIONADO":
			texto.add("Pedido adicionado com sucesso!");
			texto.add("Quer continuar seu pedido?");
			Keyboard keyMaisPedidos = new ReplyKeyboardMarkup(
					new String[] { "Adicionar Bebida" },
					new String[] { "Mostrar Parcial" }, 
					new String[] { "Fechar Conta" }).resizeKeyboard(true)
							.oneTimeKeyboard(true);

			resposta = new RespostaBot(texto, keyMaisPedidos);
			break;
		case "PEDIDO_AJUDA":
			texto.add(conversa.getCliente().getNome() + ", você possui as seguintes opções:");
			Keyboard keyAjuda = null;
			if (conversa.getCliente().isContaAberta()) {
				keyAjuda = new ReplyKeyboardMarkup(
						new String[] { "Sugestao" }, 
						new String[] { "Fazer Pedido" },
						new String[] { "Mostrar Parcial" },
						new String[] { "Menu Bebidas" },
						new String[] { "Fechar Conta" }).resizeKeyboard(true)
								.oneTimeKeyboard(true);
			} else {
				keyAjuda = new ReplyKeyboardMarkup(
						new String[] { "Menu Bebidas" },
						new String[] { "Sugestao" }, 
						new String[] { "Fazer Pedido" })
						.resizeKeyboard(true).oneTimeKeyboard(true);
			}
			resposta = new RespostaBot(texto, keyAjuda);
			break;
		case "CONTA_PARCIAL":
			texto.add(conversa.getCliente().getConta().mostrarParcialConta());
			Keyboard keyContaParcial = new ReplyKeyboardMarkup(
					new String[] { "Continuar pedindo" }, 
					new String[] { "Fechar conta" }).resizeKeyboard(true).oneTimeKeyboard(true);
			resposta = new RespostaBot(texto, keyContaParcial);
			break;
		case "CONTA_ENCERRA":
			texto.add(conversa.getCliente().getConta().encerrarConta());
			texto.add("Conta recebida!");
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
