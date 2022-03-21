package conversacao;

import java.time.LocalDateTime;

import api_clima.ClimaAPI;
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
		if (estadoAtual.equals("inicio") || ehSaudacao(perguntaResposta)) {
			this.estadoAtual = "menu_inicio";
			this.resposta = "Oi, " + cliente.getNome()+ ", " + this.mostraSaudacao() + "! Seja Bem-Vindo ao BarBot!\n"
		                    + checaTemperaturaSugere() +"\n" 			
					        + "Digite /FazerPedido para inciar um pedido\nDigite /Ajuda para consultar a Lista de opções." ;
			
			return true;
		}
		
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equalsIgnoreCase("/FazerPedido")) {
			this.estadoAtual = "anota_pedido";
			this.resposta = Bebidas.mostrarMenu() + "\n" + "Digite o pedido no formato: Bebida-Quantidade";
			return true;
		}
		
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equalsIgnoreCase("/Ajuda")) {
			this.estadoAtual = "menu_inicio";
			this.resposta = mostrarOpcoes();
			return true;
		}
		
		if (estadoAtual.equals("anota_pedido")&& ehPedido(perguntaResposta)){
			this.estadoAtual = "mais_pedido";
			this.resposta = "Pedido Anotado!";
		}
		
		if (estadoAtual.equals("mais_pedido")&& ehPedido(perguntaResposta)){
			this.estadoAtual = "mais_pedido";
			this.resposta = "Pedido Anotado! \n Digite /ConsultarComanda para verificar o pedido.\n Digite /Sair para encerrar o pedido ";
		}
		
		if (estadoAtual.equals("mais_pedido")&& perguntaResposta.equalsIgnoreCase("/sair")) {
			this.estadoAtual = "encerramento";
			this.resposta = "O valor a pagar eh: ... "+ " Tchau," + cliente.getNome()+ " ,Até logo!";
			return true;
		}
		
		if (estadoAtual.equals("menu_inicio")&& perguntaResposta.equalsIgnoreCase("/sair")) {
			this.estadoAtual = "encerramento";
			this.resposta = "Tchau," + cliente.getNome()+ " ,Até logo!";
			return true;
		}
		
		return false;
	} 
	
	private boolean ehPedido(String perguntaResposta) {
		return perguntaResposta.matches("[a-zA-Z ]+\\-[0-9]+");
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
	
	private String checaTemperaturaSugere() {
	    Double temp;
		try {
			temp = ClimaAPI.obtemDadosTemperaturaNumero("Brasilia");
			if (temp > 24.5) {	
				return "Hoje está quente em Brasília: "+temp+ " ºC, te recomendo tomar uma cerveja!";
			}
			return "Hoje está frio em Brasilia: "+temp+ " ºC, te recomendo tomar um vinho!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Não foi possivel consultar o clima de hoje";
		} 
	}
    
}
