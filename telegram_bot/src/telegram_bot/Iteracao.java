package telegram_bot;

import java.time.LocalDateTime;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Iteracao {
	
	private Conversa conversa;
	private String estimulo;
	private RespostaBot resposta;
	
	public Iteracao(Conversa conversa) {
		this.setConversa(conversa);
		this.setEstimulo("");
	}
	
	public String getEstimulo() {
		return estimulo;
	}

	public void setEstimulo(String estimulo) {
		this.estimulo = estimulo;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}
	
	public RespostaBot devolverResposta() {
		
		switch (conversa.getIteracaoAtual().name()) {
		case "INICIO": 
			String[] linhasTexto = new String[5];
			linhasTexto[0] = "Oi, " + conversa.getCliente().getNome() + ", " + 
					this.mostraSaudacao() + "! Seja bem-vindo ao BarBot!";
			linhasTexto[1] = "Hoje o dia está bem quente, ideal para um happy hour com os amigos!";
			linhasTexto[2] = "Selecione FAZER PEDIDO ou AJUDA para consultar a lista de opções:";
				
			String[] botoesMenuInicial = new String[2];
			botoesMenuInicial[0] = "FAZER PEDIDO";
			botoesMenuInicial[1] = "AJUDA"; 
			Keyboard keyMenuInicial = new ReplyKeyboardMarkup(botoesMenuInicial).resizeKeyboard(true).oneTimeKeyboard(true);
				
			resposta = new RespostaBot(linhasTexto, keyMenuInicial);
			break;
		case "PEDIDO_PRODUTO": 
		case "PEDIDO_QUANTIDADE": 
		case "PEDIDO_ADICIONADO":
		case "CONTA_PARCIAL":
		case "CONTA_ENCERRA":
		case "FIM":
			break;
		}
		
		return resposta;
	}

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
	 * private boolean ehSaudacao(String perguntaResposta) { return
	 * perguntaResposta.
	 * matches("^(o|O)i!{0,1}|^(B|b)om (D|d)ia!{0,1}|^(B|b)oa (t|T)arde!{0,1}|^(B|b)oa (n|N)oite!{0,1}"
	 * ); }
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
		} else if(horaAtual <= 11 ) { 
			saudacao = "bom dia";
		} else if(horaAtual <= 17) {
			saudacao = "boa tarde";
		} else {
			saudacao = "boa noite";
		}

		return saudacao;		
	}
    
}
