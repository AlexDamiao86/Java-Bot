package telegram_bot;

import java.util.ArrayList;

import com.pengrad.telegrambot.model.request.Keyboard;

public class RespostaBot {
	
	private ArrayList<String> texto = new ArrayList<String>();
	private boolean possuiTeclado; 
	private Keyboard teclado;
	
	RespostaBot(ArrayList<String> texto) {
		this.texto = texto;
		this.possuiTeclado = false;
		this.teclado = null;
	}
	
	RespostaBot(ArrayList<String> texto, Keyboard teclado) {
		this.texto = texto; 
		this.possuiTeclado = true;
		this.teclado = teclado;
	}
	
	public ArrayList<String> getTexto() {
		return texto;
	}
	
	public boolean isPossuiTeclado() {
		return possuiTeclado;
	}
	
	public Keyboard getTeclado() {
		return teclado;
	} 

}
