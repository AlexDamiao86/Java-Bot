package telegram_bot;

import java.util.Scanner;

/**
 * Garcom - Classe de testes de interacao.
 * Possibilita testar unidades e sistema com a interacao com o cliente em ambiente
 * de desenvolvimento, permite toda a interação implementada até o momento.
 * @author fabio
 * @version 1.0
 */
public class Garcom {

	private static final int TAMLINHA = 40;

	/**
	 * @param args
	 * Todo o fluxo é controlado pela 'opcao' colhida pela 'teclado'
	 */
	public static void main(String[] args) {
		int opcao;
		String resposta;
		Pedido pedido = new Pedido();
		Bebidas bebidaPedida[] = Bebidas.values();
		
		// saudacao inicial
		Scanner teclado = new Scanner(System.in);
		System.out.println("Saudações, gostaria de ver nosso menu? (s/n)");
		resposta = teclado.nextLine();
		System.out.println(resposta);
		if (resposta.charAt(0) == 's') {
			mostrarMenu();
		}
		
		opcao = 98;  //Força a entrada no laço de conversação
		
		while (opcao < 99 && opcao >= 0) {
			System.out.println("\nPeça pelo número da bebida ou "
					+ "\n40 Consultar sua comanda"
					+ "\n50 Ver o menu novamente "
					+ "\n60 Retirar um item do pedido"
					+ "\n99 Fechar a conta");
			opcao = teclado.nextInt();
			
			if (opcao > 0 && opcao < Bebidas.values().length) {  //Pedido valido de bebidas
				int quantidade = 0;
				while (quantidade < 1) {   //Previne engraçadinhos de usar número negativo
					System.out.println("Qual a quantidade?");
					quantidade = teclado.nextInt();
				}
				System.out.println(pedido.anota(bebidaPedida[opcao], quantidade));
			} else if (opcao == 40) {
				System.out.println(pedido.consulta());
			} else if (opcao == 50) {
				mostrarMenu();
			} else if (opcao == 60) {
				System.out.println("Qual item gostaria de retirar do pedido?");
				mostrarMenu();
				opcao = teclado.nextInt();
				if (opcao > 0 && opcao < Bebidas.values().length) {
					int quantidade = 0;
					while (quantidade < 1) {   //Previne engraçadinhos de usar número negativo
						System.out.println("Qual a quantidade?");
						quantidade = teclado.nextInt();
					}
					System.out.println(pedido.risca(bebidaPedida[opcao], quantidade));
				}
			} else if (opcao == 99) {
				pedido.gerarConta();
				break;
			}
		}
		System.out.println("\n   FOI UM PRAZER ATENDER, VOLTE SEMPRE!");
		teclado.close();
	}

	/**
	 * Gera menu de todas as bebidas disponíveis e seus respectivos números
	 */
	private static void mostrarMenu() {
		String menu = "              ***  MENU DE BEBIDAS  ***"
				+ "\n---------------------------------------------------";
		for (Bebidas bebida : Bebidas.values()) {
			menu+= "\n" +bebida.getNum()+ ". " +bebida.getNomeBebida();
			
			String linha ="";
			while ((linha.length() + bebida.getNomeBebida().length()) < TAMLINHA ){
				linha+= ".";
			}
			menu+=  linha+ " R$ " +bebida.getPreco();
		}
		System.out.println(menu);
	}
}
