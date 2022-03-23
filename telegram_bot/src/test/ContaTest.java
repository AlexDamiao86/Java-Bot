package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telegram_bot.Bebida;
import telegram_bot.Cliente;
import telegram_bot.Conta;
import telegram_bot.Pedido;

public class ContaTest {
	
	Cliente cliente = new Cliente((long) 1, "Alexandre", "Maia");
	Conta conta = cliente.abrirConta();
	Pedido pedido1 = new Pedido(1, conta, Bebida.PINACOLADA, 2);
	Pedido pedido2 = new Pedido(2, conta, Bebida.CUBALIBRE, 4);

	@Test
	public void deveMostrarParcialEGarantirIdentificadores() {
		
		assertEquals("CONTA ABERTA", conta.getSituacao().getDescricao());

		conta.incluirPedidoConta(pedido1);
		conta.incluirPedidoConta(pedido2);
		
		System.out.println(conta.encerrarConta());
		
		assertEquals("CONTA ENCERRADA",conta.getSituacao().getDescricao());
		assertEquals(1, pedido1.getIdentificador());
		assertEquals(2, pedido2.getIdentificador());
	}
	
	@Test
	public void deveManterConsistenciaDoCliente() {
		assertEquals(cliente, conta.getCliente());
	}
}
