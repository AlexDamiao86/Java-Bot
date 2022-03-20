package test;

import telegram_bot.Bebida;
import telegram_bot.Cliente;
import telegram_bot.Conta;
import telegram_bot.Pedido;

public class ContaTest {
	
	public static void main(String[] args) {
		Cliente cliente = new Cliente((long) 1, "Alexandre", "Maia");
		Conta conta = cliente.abrirConta();
		
		System.out.println(conta.getSituacao());
		System.out.println(conta.getDataHoraAbertura());

		Bebida bebida1 = Bebida.PINACOLADA;
		Pedido pedido1 = new Pedido(1, conta, bebida1, 2);
		conta.incluirPedidoConta(pedido1);

		Bebida bebida2 = Bebida.CUBALIBRE;
		Pedido pedido2 = new Pedido(2, conta, bebida2, 4);
		conta.incluirPedidoConta(pedido2);
		
		System.out.println("\n>>> PEDE PARCIAL\n");
		System.out.println(conta.mostrarParcialConta());
		
		System.out.println("\n>>> PEDE ENCERRAMENTO\n"); 
		System.out.println(conta.encerrarConta());
		System.out.println(conta.getSituacao());
		System.out.println(conta.getDataHoraEncerramento());
	}

}
