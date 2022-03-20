package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import telegram_bot.Cliente;
import telegram_bot.Conversa;
import telegram_bot.EstadoIteracao;

public class ConversaTest {

	@Test
	public void instanciaConversa() {
		Cliente cliente = new Cliente((long) 1, "Alexandre", "Maia");
		Conversa conversa = cliente.iniciarConversa((long) 1);
		assertNotNull(conversa.getDataHoraInicio());
		assertNull(conversa.getDataHoraFim());
		assertEquals(cliente, conversa.getCliente());
		assertEquals(EstadoIteracao.INICIO, conversa.getIteracaoAtual());
	}
	
	@Test
	public void encerraConversa() {
		Cliente cliente = new Cliente((long) 1, "Alexandre", "Maia");
		Conversa conversa = cliente.iniciarConversa((long) 1);
		conversa.encerrarConversa();
		assertNotNull(conversa.getDataHoraInicio());
		assertNotNull(conversa.getDataHoraFim());
		assertEquals(cliente, conversa.getCliente());
		assertEquals(EstadoIteracao.FIM, conversa.getIteracaoAtual());
	}
}
