package telegram_bot;

/**
 * Lista com todos os drinks disponíveis no menu 
 * A cada nova adicao de item deve ser vinculado um numero sequencial e inserido
 * o novo item no switch do construtor da classe
 * @author fabio
 * @version 1.0
 */

public enum Bebidas {
	PINACOLADA(0, "Pina Colada",                 15.00),
	CUBALIBRE( 1, "Cuba Libre",                  20.00),
	CERVEJABUD(2, "Garrafa Budweiser 330ml",     12.00),
	CAPIRINHA( 3, "Capirinha de limao",          14.00),
	TEQUILA(   4, "Shot de Tequila Jose Cuervo", 22.00);
	
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
}
