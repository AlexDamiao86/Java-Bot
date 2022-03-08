package telegram_bot;

/**
 * Pedido - Guarda o número de cada bebida solicitada pelo cliente
 * @author fabio
 * @version 1.0
 */

/**
 * Construtor da classe, inicializa o array com zeros varrendo de acordo 
 * com o tamanho do Enum Bebidas.
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
	 * Faz a anotação da quantidade da bebida solicitada
	 * @param bebida A bebida que o cliente escolheu
	 * @param qtd    A quantidade solicitada dessa bebida
	 * @return Informação do pedido anotado ou de erro por quantidade
	 */
	public String anota(Bebidas bebida, int qtd) {
		
		if (qtd > 0) {                 //Evita que engraçadinhos façam pedidos negativos
			quantidades[bebida.getNum()] += qtd;
			
			return (qtd+ " " +bebida.getNomeBebida()+ ". Anotado no pedido!");
		}
		
		return ("A quantidade deve ser 1 ou mais para anotar seu pedido!");
	}
	
	/**
	 * Exclui bebidas do pedido do cliente
	 * @param bebida    Tipo de bebida que deve ser excluida do pedido
	 * @param qtd       Quantidade de bebidas que deve ser excluida do pedido
	 * @return Resposta para o cliente sobre o sucesso ou o motivo de falha do pedido
	 */
	public String risca(Bebidas bebida, int qtd) {
		
		if (quantidades[bebida.getNum()] == 0) {
			return ("Não houve nenhum pedido de " + bebida.getNomeBebida() + " até o momento.");
		} else if (quantidades[bebida.getNum()] < qtd) {
			return ("Foi pedido apenas " +quantidades[bebida.getNum()]+ " até o momento, não posso riscar " +qtd);
		}
		quantidades[bebida.getNum()] -= qtd;
		return ("Risquei " +qtd+ " " + bebida.getNomeBebida() + " do seu pedido");
	}
	
	/**
	 * Faz a lista de todos os drinks solicitados no pedido e suas quantidades
	 * mostrando na tela apenas os que tiverem sido pedidos (quantidade > 0)
	 * 
	 * @return A lista contendo todas as bebidas do pedido até o momento.
	 */
	public String consulta() {
		String consulta = "";
		
		for (Bebidas bebida : Bebidas.values()) {
			if (quantidades[bebida.getNum()] > 0) {
				consulta += ("\n" +bebida.getNomeBebida() + " ................ " 
						+ quantidades[bebida.getNum()]);
			}
		}
		consulta += ("\nQUANTIDADE TOTAL DE BEBIDAS PEDIDAS: " +contaBebidas()+ "\n");
		
		return consulta;
	}
	
	public int contaBebidas() {
		int qtdTotal = 0;
		for (int i = 0; i < quantidades.length; i++) {
			qtdTotal += quantidades[i];
		}
		return qtdTotal;
	}
	
}
