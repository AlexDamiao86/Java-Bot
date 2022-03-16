package conversacao;

import telegram_bot.Bebidas;

public class Iteracao {
	
	private String estadoAtual;
	private String resposta;
	private Cliente cliente;
	
	public Iteracao(Cliente c) {
		this.estadoAtual = "inicio";
		this.resposta = "Não Entendi...";
		this.cliente = c;
	}
	
	public boolean mudaEstado(String perguntaResposta) {
		if (estadoAtual.equals("inicio")&& ehSaudacao(perguntaResposta)) {
			this.estadoAtual = "menu_inicio";
			this.resposta = "Oi, "+ cliente.getNome()+" , Bem Vindo ao BarBot!\n"
					        + "Digite /Menu para consultar o Menu de Bebidas\nDigite /Ajuda para consultar a Lista de opções." ;
			return true;
		}
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equals("/Menu")) {
			this.estadoAtual = "menu_inicio";
			this.resposta = mostrarMenu();
			return true;
		}
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equals("/Ajuda")) {
			this.estadoAtual = "menu_inicio";
			this.resposta = mostrarOpcoes();
			return true;
		}
		
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equals("/FazerPedido")) {
			this.estadoAtual = "anota_pedido";
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
	
	private  String mostrarMenu() {
		int tamlinha = 30;
		String menu = "     ***  MENU DE BEBIDAS  ***"
				+ "\n--------------------------------------";
		for (Bebidas bebida : Bebidas.values()) {
			menu+= "\n" +bebida.getNum()+ ". " +bebida.getNomeBebida();
			
			String linha ="";
			while ((linha.length() + bebida.getNomeBebida().length()) < tamlinha ){
				linha+= ".";
			}
			menu+=  linha+ " R$ " +bebida.getPreco();
		}
		return menu;
	}
	
	private String mostrarOpcoes() {
		String result = "/FazerPedido"
	                +"\n/ConsultarPedido"
	                +"\n/Menu"
	                +"\n/RetirarPedido"
	                +"\n/Sair";
		return result;
	}
}
