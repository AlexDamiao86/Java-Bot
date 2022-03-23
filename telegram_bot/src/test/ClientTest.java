package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telegram_bot.Cliente;

public class ClientTest {
	
	Cliente cliente = new Cliente(new Long(123456), "Valdisnei", "Joinha");

	@Test
	public void deveConsistirDadosDoCliente() {
		
		assertEquals(123456, cliente.getIdentificador());
		assertEquals("Valdisnei", cliente.getNome());
		assertEquals("Joinha", cliente.getSobrenome());
	}
	@Test
	public void deveAbrirConta() {
		assertEquals("CONTA ABERTA", cliente.abrirConta().getSituacao().getDescricao());
	}
	@Test
	public void deveIniciarConversa() {
	
	assertEquals(cliente, cliente.iniciarConversa(new Long(123456789)).getCliente());
	}
}
