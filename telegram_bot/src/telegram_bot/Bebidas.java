package telegram_bot;

/**
 * Lista com todos os drinks disponíveis no menu 
 * A cada nova adicao de item deve ser vinculado um numero sequencial e inserido
 * o novo item no switch do construtor da classe
 * @author fabio
 * @version 1.0
 */

public enum Bebidas {
	PINACOLADA(0),
	CUBALIBRE(1),
	CERVEJABUD(2),
	CAPIRINHA(3),
	TEQUILA(4);
	
	private int item;
	private String nomeBebida;

	Bebidas(int item) {
		this.item = item;
		
		switch (item) {
		case 0:
			nomeBebida = "Pina Colada";
			break;
		case 1:
			nomeBebida = "Cuba Libre";
			break;
		case 2:
			nomeBebida = "Cerveja Budweiser";
			break;
		case 3:
			nomeBebida = "Caipirinha de limao";
			break;
		case 4:
			nomeBebida = "Shot de Tequila";
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 * @return nomeBebida - Nome da bebida em portugues corrente
	 */
	public String getNomeBebida() {
		return nomeBebida;
	}

	/**
	 * 
	 * @return item - Numero do item no cardapio, utiliza o enum
	 */
	public int getNum() {
		return item;
	}
}
