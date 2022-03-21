package telegram_bot;

import com.pengrad.telegrambot.model.request.Keyboard;

public class RespostaBot {
	
	private String[] texto = new String[5];
	private boolean possuiTeclado; 
	private Keyboard teclado;
	
	RespostaBot(String[] texto) {
		this.texto = texto;
		this.possuiTeclado = false;
		this.teclado = null;
	}
	
	RespostaBot(String[] texto, Keyboard teclado) {
		this.texto = texto; 
		this.possuiTeclado = true;
		this.teclado = teclado;
	}
	
	public String[] getTexto() {
		return texto;
	}
	
	public boolean isPossuiTeclado() {
		return possuiTeclado;
	}
	
	public Keyboard getTeclado() {
		return teclado;
	} 

}
