package telegram_bot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Iteracao {

	private Conversa conversa;
	private String estimulo;
//    private Pedido pedido;
    
	public Iteracao(Conversa conversa, String estimulo) {
		this.conversa = conversa;
		this.estimulo = estimulo;
	}

	public String getEstimulo() {
		return estimulo;
	}
	
	private boolean ehSaudacao() { 
		return estimulo.matches("^(o|O)i{0,1}|^(B|b)om (D|d)ia{0,1}|^(B|b)oa (t|T)arde{0,1}|^(B|b)oa (n|N)oite{0,1}"); 
	}
	
	private boolean ehSair() {
		return estimulo.matches("(s|S)air|(a|A)té\\s|(j|J)oinha|(B|b)elezinha");
	}	
	
	private boolean ehPedido() { 
		return estimulo.matches("Fazer Pedido"); 
	}	

	public RespostaBot devolverResposta() {

//		RespostaBot resposta = null;
		ArrayList<String> texto = new ArrayList<String>();
	    
		if(ehSaudacao()) {
			texto.add("Oi, " + conversa.getCliente().getNome() + ", " + this.mostraSaudacao()
					+ "! Seja bem-vindo ao BarBot!");
			texto.add("Hoje o dia está bem quente, ideal para um happy hour com os amigos!");
			texto.add("Selecione Fazer Pedido para ver a lista de opções:");
            
			Keyboard keyInicio = new ReplyKeyboardMarkup(
					new String[]{"Fazer Pedido" , "Sair"}
					).resizeKeyboard(true).oneTimeKeyboard(true);

//			RespostaBot resposta = new RespostaBot(texto, keyInicio);
			return new RespostaBot(texto, keyInicio);
		};
		
		if(ehPedido()) {
			texto.add("ok ");
			texto.add("Escolha uma bebida");
			
			Keyboard keyPedido = new ReplyKeyboardMarkup(
					new String[]{"HEINEKEN", "BUD" , "SKOL" },
			        new String[]{"PINACOLADA", "CUBALIBRE" , "CAPIRINHA" },
			        new String[]{"AGUA" , "REFRIGERANTE" , "SUCO" },
					new String[]{"Sair" }
			).resizeKeyboard(true).oneTimeKeyboard(true);
			
//			String[] botoesBebidas = new String[Bebida.values().length];
//			
//			for (Bebida bebida : Bebida.values()) {
//				botoesBebidas[bebida.getIdentificador() - 1] = bebida.getDescricao();
//			}
//		
//			Keyboard keyPedido = 
//					new ReplyKeyboardMarkup(Arrays.copyOfRange(botoesBebidas, 0, 4))
//						.resizeKeyboard(true).oneTimeKeyboard(true).addRow(
//								new KeyboardButton[] { new KeyboardButton("Sair")});
			
			return new RespostaBot(texto, keyPedido);
		};
		
		if(ehSair()) {
			texto.add("Agradecemos o seu contato");
			texto.add("Tchau, até logo!");
			
			return new RespostaBot(texto);
		};		
		
		//nenhuma das condicoes acima
		texto.add("ops");
		texto.add("Não entendi...");
		return new RespostaBot(texto);
		
//		case "PEDIDO_QUANTIDADE":
//			texto.add("Digite a quantidade da bebida selecionada:");
//			resposta = new RespostaBot(texto);
//			break;
//		case "PEDIDO_ADICIONADO":
//			//TODO Adicionar o Pedido aqui
//			texto.add("Pedido adicionado com sucesso");
//			texto.add("Adicione mais bebidas ou digite SAIR para encerrar");
//			resposta = new RespostaBot(texto);
//			conversa.mudarIteracaoAtual(EstadoIteracao.PEDIDO_PRODUTO);
//			break;
//		case "PEDIDO_AJUDA":
//			texto.add("Digite ou selecione PEDIDO para fazer um pedido de uma bebida.");
//			resposta = new RespostaBot(texto);
//			break;
//		case "CONTA_PARCIAL":
//		case "CONTA_ENCERRA":
//		case "FIM":
//			texto.add("Tchau, até logo! Te espero por aqui para uma próxima visita!!");
//			resposta = new RespostaBot(texto);
//			break;
//		default: 
//			texto.add("Nao entendi.. Tecle AJUDA para ver as opções disponiveis");
//			resposta = new RespostaBot(texto);
//		}
//
//		return resposta;
	}
	
//	private void verificarSeMudaEstado() {
//		
//		switch (conversa.getIteracaoAtual().name()) {
//		case "INICIO":
//			if (estimulo.equalsIgnoreCase("PEDIDO")) {
//				conversa.mudarIteracaoAtual(EstadoIteracao.PEDIDO_PRODUTO);
//			}
//			if (estimulo.equalsIgnoreCase("AJUDA")) {
//				conversa.mudarIteracaoAtual(EstadoIteracao.PEDIDO_AJUDA);
//			}
//			if (estimulo.equalsIgnoreCase("SAIR")) {
//				conversa.encerrarConversa();
//			}
//			break;
//		case "PEDIDO_PRODUTO": 
//			if (estimulo.equalsIgnoreCase("SAIR")) {
//				conversa.encerrarConversa();
//			}
//			if (Bebida.ehBebidaValida(estimulo)){
//				conversa.mudarIteracaoAtual(EstadoIteracao.PEDIDO_QUANTIDADE);
//			}
//			break;
//		case "PEDIDO_QUANTIDADE":
//			if (estimulo.matches("[0-9]+")){
//				conversa.mudarIteracaoAtual(EstadoIteracao.PEDIDO_ADICIONADO);
//			}
//			break;
//		case "PEDIDO_ADICIONADO":
//			
//			
//			break;
//		case "PEDIDO_AJUDA":
//			conversa.mudarIteracaoAtual(EstadoIteracao.PEDIDO_PRODUTO);
//			break;
//		case "CONTA_PARCIAL":
//			conversa.mudarIteracaoAtual(EstadoIteracao.PEDIDO_PRODUTO);
//			break;
//		case "CONTA_ENCERRA":	
//		default:
//			break;
//		}

	/*
	 * public boolean mudaEstado(String perguntaResposta) { if
	 * (estadoAtual.equals("inicio") || ehSaudacao(perguntaResposta)) {
	 * this.estadoAtual = Estado.INICIO; this.resposta = "Oi, " + cliente.getNome()+
	 * ", " + this.mostraSaudacao() + "! Seja Bem-Vindo ao BarBot!\n" +
	 * "Digite /Menu para consultar o Menu de Bebidas\nDigite /Ajuda para consultar a Lista de opções."
	 * ; return true; } if (estadoAtual.equals("menu_inicio")&&
	 * perguntaResposta.equalsIgnoreCase("/menu")) { this.estadoAtual =
	 * Estado.INICIO; this.resposta = Bebida.mostrarMenu(); return true; } if
	 * (estadoAtual.equals("menu_inicio")&&
	 * perguntaResposta.equalsIgnoreCase("/ajuda")) { this.estadoAtual =
	 * Estado.INICIO; this.resposta = mostrarOpcoes(); return true; }
	 * 
	 * if (estadoAtual.equals("menu_inicio")&&
	 * perguntaResposta.equalsIgnoreCase("/fazerpedido")) { this.estadoAtual =
	 * Estado.CONTA_ABERTA; this.resposta =
	 * "Digite o pedido no formato: Codigo - quantidade"; return true; }
	 * 
	 * return false; }
	 * 
	 * private String mostrarOpcoes() { String result = "/FazerPedido"
	 * +"\n/ConsultarPedido" +"\n/Menu" +"\n/RetirarPedido" +"\n/Sair"; return
	 * result; }
	 */
	
	

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