package telegram_bot;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Lista com todos os drinks disponíveis no menu 
 * A cada nova adicao de item deve ser vinculado um numero sequencial e inserido
 * o novo item no switch do construtor da classe
 * @author fabio
 * @version 1.0
 */

public enum Bebidas {
	PINACOLADA(1, "Pina Colada",                 15.00),
	CUBALIBRE( 2, "Cuba Libre",                  20.00),
	CERVEJABUD(3, "Cerveja Budweiser 330ml",     12.00),
	CAIPIRINHA(4, "Caipirinha de Limao",         14.00),
	TEQUILA(   5, "Shot de Tequila Jose Cuervo", 22.00);
	
	private int item;
	private String nomeBebida;
	private double preco;

	Bebidas(int item,  String nome, double preco) {
		this.item = item;
		this.preco = preco;
		this.nomeBebida = nome;
	}

	/**
	 * @return nomeBebida - Nome da bebida em portugues corrente
	 */
	public String getNomeBebida() {
		return nomeBebida;
	}

	/**
	 * @return item - Numero do item no cardapio, utiliza o enum
	 */
	public int getNum() {
		return item;
	}

	/**
	 * @return O preco da bebida em questao
	 */
	public double getPreco() {
		return preco;
	}
	
	public static String mostrarMenu() {
		int tamlinha = 30;
		String format = "0,00";
		NumberFormat formatter = new DecimalFormat(format);
		
		String menu = "     ***  MENU DE BEBIDAS  ***"
				+ "\n--------------------------------------";
		for (Bebidas bebida : Bebidas.values()) {
			menu+= "\n" +bebida.getNum()+ ". " +bebida.getNomeBebida();
			
			String linha ="";
			while ((linha.length() + bebida.getNomeBebida().length()) < tamlinha ){
				linha+= ".";
			}
			menu+=  linha+ " R$ " + formatter.format(bebida.getPreco());
		}
		return menu;
	}

}
