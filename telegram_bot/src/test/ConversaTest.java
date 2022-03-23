package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import telegram_bot.Cliente;
import telegram_bot.Conversa;
import telegram_bot.EstadoInteracao;

public class ConversaTest {

	@Test
	public void instanciaConversa() {
		Cliente cliente = new Cliente((long) 1, "Alexandre", "Maia");
		Conversa conversa = cliente.getConversa();
		assertNotNull(conversa.getDataHoraInicio());
		assertNull(conversa.getDataHoraFim());
		assertEquals(cliente, conversa.getCliente());
		assertEquals(EstadoInteracao.INICIO, conversa.getInteracaoAtual());
	}
	
	@Test
	public void encerraConversa() {
		Cliente cliente = new Cliente((long) 1, "Alexandre", "Maia");
		Conversa conversa = cliente.getConversa();
		conversa.encerrarConversa();
		assertNotNull(conversa.getDataHoraInicio());
		assertNotNull(conversa.getDataHoraFim());
		assertEquals(cliente, conversa.getCliente());
		assertEquals(EstadoInteracao.FIM, conversa.getInteracaoAtual());
	}
}
