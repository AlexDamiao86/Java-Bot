package telegram_bot;

/**
 * Pedido - Guarda o número de cada bebida solicitada pelo cliente
 * @author fabio
 * @version 1.0
 */
public class Pedido {
	
	private int[] quantidades;
	
	public Pedido() {
		quantidades = new int[Bebidas.values().length];
		
		for (int i = 0; i < quantidades.length; i++) {
			quantidades[i] = 0;
		}
	}

	/**
	 * anota - Faz a anotação da quantidade da bebida solicitada
	 * @param bebida A bebida que o cliente escolheu
	 * @param qtd A quantidade solicitada dessa bebida
	 */
	public void anota(Bebidas bebida, int qtd) {
		
		quantidades[bebida.getNum()] += qtd;
	}
	
	/**
	 * Metodo: consulta
	 * Faz a lista de todos os drinks solicitados no pedido e suas quantidades
	 * mostrando na tela apenas os que tiverem sido pedidos (quantidade > 0)
	 */
	public int consulta() {
		int qtdTotal = 0;
		for (Bebidas bebida : Bebidas.values()) {
			if (quantidades[bebida.getNum()] > 0) {
				System.out.println(bebida.getNomeBebida() + " ..... " + quantidades[bebida.getNum()]);
				qtdTotal += quantidades[bebida.getNum()];
			}
		}
		System.out.println("\nQUANTIDADE TOTAL DE BEBIDAS PEDIDAS: " +qtdTotal+ "\n\n");
		return qtdTotal;
	}
	
}
