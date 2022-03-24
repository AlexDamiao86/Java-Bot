package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telegram_bot.Bebida;
import telegram_bot.Cliente;
import telegram_bot.Conta;
import telegram_bot.Pedido;

public class PedidoTest {

	Conta conta;
	Cliente cliente;
	
	@BeforeEach
	public void inicializa() {
	cliente = new Cliente(new Long(123), "Rafael", "Tartaruga Vermelha");
	conta = new  Conta(new Long(456), cliente);
	}
	
	@Test
	public void deveConsistirOsDadosNoPedido() {
		Pedido pedido = new Pedido(conta, Bebida.CAIPIRINHA, 5);
		
		assertEquals(5, pedido.getQuantidade());
		assertEquals(123, pedido.getConta().getCliente().getIdentificador());
		assertEquals("Rafael", pedido.getConta().getCliente().getNome());
		assertEquals(456, pedido.getConta().getIdentificador());
		assertEquals(conta, pedido.getConta());
		assertEquals(cliente, pedido.getConta().getCliente());
		
	}
}
