package telegram_bot;

/**
 * Pedido - Guarda o número de cada bebida solicitada pelo cliente
 * @author fabio
 * @version 1.0
 */

public class Pedido {
	
	private static final int TAMANHOLINHA = 40;
	private int[] quantidades;
	
	/**
	 * Construtor da classe, inicializa o array com zeros varrendo de acordo 
	 * com o tamanho do Enum Bebidas.
	 */
	public Pedido() {
		quantidades = new int[Bebida.values().length];
		
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
	public String anota(Bebida bebida, int qtd) {
		
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
	public String risca(Bebida bebida, int qtd) {
		
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
		String consulta = "\nCONSULTA DE COMANDA" +criaLinha();
		
		for (Bebida bebida : Bebida.values()) {
			if (quantidades[bebida.getNum()] > 0) {
				consulta += ("\n" +bebida.getNomeBebida());
				String linha = "";
				for (int i = 0; i < (TAMANHOLINHA-bebida.getNomeBebida().length()); i++) {
					linha += ".";
				}
				consulta += linha + quantidades[bebida.getNum()];
			}
		}
		consulta += criaLinha();
		consulta += ("\nQUANTIDADE TOTAL DE BEBIDAS PEDIDAS:... " +contaBebidas()+ "\n");
		
		return consulta;
	}

	/**
	 * @return linha separadora de seções da comanda
	 */
	private String criaLinha() {
		String linha = "\n";
		while(linha.length() <= TAMANHOLINHA) {
			linha += "=";
		}
		return linha;
	}
	
	/**
	 * @return Quantidade total de bebidas solicitdas no pedido ate o momento
	 */
	public int contaBebidas() {
		int qtdTotal = 0;
		
		for (int i = 0; i < quantidades.length; i++) {
			qtdTotal += quantidades[i];
			System.out.println(i);
		}
		return qtdTotal;
	}
	
	/**
	 * Gera a conta com a descrição das bebidas e valores do pedido e o total
	 * mostra na tela a conta para o cliente.
	 */
	public void gerarConta() {
		double total = 0;
		System.out.println("\n*** DEMONSTRATIVO DE CUSTOS DO PEDIDO ***\n*");
		for (Bebida bebida : Bebida.values()) {
			if (quantidades[bebida.getNum()] > 0) {
				double totalDaBebida = quantidades[bebida.getNum()]*bebida.getPreco();
				System.out.println("* "+quantidades[bebida.getNum()] +" "+bebida.getNomeBebida()+" ........ R$ "+ totalDaBebida);
				total += totalDaBebida;
			}
		}
		System.out.println("*\n* VALOR TOTAL DO PEDIDO .............. R$ " + total);
	}
}
