package telegram_bot;

import java.time.LocalDateTime;

public class Iteracao {
	
	private String estimulo;
	private Conversa conversa;
	
	public Iteracao(Conversa conversa) {
		this.setConversa(conversa);
	}
	
	public String getEstimulo() {
		return estimulo;
	}

	public void setEstimulo(String estimulo) {
		this.estimulo = estimulo;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}
	
	public String devolveRespostaBot(String estimulo) {
		
		String estimuloLC = estimulo.toLowerCase();
		
		switch (conversa.getIteracaoAtual().name()) {
		case "INICIO": 
			
		case "PEDIDO_PRODUTO": 
		case "PEDIDO_QUANTIDADE": 
		case "PEDIDO_ADICIONADO":
		case "CONTA_PARCIAL":
		case "CONTA_ENCERRA":
		case "FIM":
			break;
		}
		
		return estimuloClienteLC;
	}

	
	public boolean mudaEstado(String perguntaResposta) {
		if (estadoAtual.equals("inicio") || ehSaudacao(perguntaResposta)) {
			this.estadoAtual = Estado.INICIO;
			this.resposta = "Oi, " + cliente.getNome()+ ", " + this.mostraSaudacao() + "! Seja Bem-Vindo ao BarBot!\n"
					        + "Digite /Menu para consultar o Menu de Bebidas\nDigite /Ajuda para consultar a Lista de opções." ;
			return true;
		}
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equalsIgnoreCase("/menu")) {
			this.estadoAtual = Estado.INICIO;
			this.resposta = Bebida.mostrarMenu();
			return true;
		}
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equalsIgnoreCase("/ajuda")) {
			this.estadoAtual = Estado.INICIO;
			this.resposta = mostrarOpcoes();
			return true;
		}
		
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equalsIgnoreCase("/fazerpedido")) {
			this.estadoAtual = Estado.CONTA_ABERTA;
			this.resposta = "Digite o pedido no formato: Codigo - quantidade";
			return true;
		}
		
		return false;
	} 
	
	private boolean ehSaudacao(String perguntaResposta) {
		return perguntaResposta.matches("^(o|O)i!{0,1}|^(B|b)om (D|d)ia!{0,1}|^(B|b)oa (t|T)arde!{0,1}|^(B|b)oa (n|N)oite!{0,1}");
	}
	    
	public String getResposta() {
		return this.resposta;
	}
	
	private String mostrarOpcoes() {
		String result = "/FazerPedido"
	                +"\n/ConsultarPedido"
	                +"\n/Menu"
	                +"\n/RetirarPedido"
	                +"\n/Sair";
		return result;
	}
	
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
