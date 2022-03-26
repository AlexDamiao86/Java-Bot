package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telegram_bot.Cliente;
import telegram_bot.Conversa;
import telegram_bot.EstadoInteracao;
import telegram_bot.Interacao;
import telegram_bot.RespostaBot;

public class InteracaoTest {
	Cliente cliente;
	Conversa conversa;
	Interacao interacao;
	
	@BeforeEach
	public void inicializaVariaveis() {
	cliente = new Cliente(new Long(1), "Mark", "Zuckeberger");
	conversa = new Conversa(new Long(1), cliente);
	interacao = new Interacao(conversa, "");
	}
	
	@Test
	public void deveIniciarConversa() {
		RespostaBot resposta = interacao.devolverResposta();
		System.out.println(resposta.getTexto());
		assertTrue(resposta.getTexto().toString().contains("Oi"));
	}
	
	@Test
	public void deveSerApresentadoOMenu() {
		conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_MENU);
		RespostaBot resposta = interacao.devolverResposta();
		assertTrue(resposta.getTexto().toString().contains("MENU"));
	}
	
	@Test
	public void deveSugerirBebidaPorClima() {
		conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_SUGESTAO);
		RespostaBot resposta = interacao.devolverResposta();
		assertTrue(resposta.getTexto().toString().contains("Hoje está uma temperatura")); 
		
	}
	
	@Test
	public void deveSolicitarPedidoProduto () {
		conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_PRODUTO);
		RespostaBot resposta = interacao.devolverResposta();
		assertTrue(resposta.getTexto().toString().contains("qual bebida gostaria"));
	}
	@Test
	public void deveSolicitarQuantidadePedidoProduto () {
		conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_QUANTIDADE);
		RespostaBot resposta = interacao.devolverResposta();
		assertTrue(resposta.getTexto().toString().contains("Digite a quantidade"));
	}
	
	@Test
	public void deveAdicionarPedidoProduto () {
		conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_ADICIONADO);
		RespostaBot resposta = interacao.devolverResposta();
		assertTrue(resposta.getTexto().toString().contains("Pedido adicionado"));
	}
	
	@Test
	public void deveMostrarMenuAjuda () {
		conversa.mudarInteracaoAtual(EstadoInteracao.PEDIDO_AJUDA);
		RespostaBot resposta = interacao.devolverResposta();
		assertTrue(resposta.getTexto().toString().contains("seguintes opções"));
	}
//	@Test
//	public void deveMostrarContaParcial () {
//		cliente.getConta().incluirPedidoConta(new Pedido(cliente.getConta(), Bebida.AGUA,1));
//		conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_PARCIAL);
//		RespostaBot resposta = interacao.devolverResposta();
//		System.out.println("oOo" + resposta.getTexto() + "oOo");
//		assertTrue(resposta.getTexto().toString().contains("DEMONSTRATIVO CONTA"));
//	}
//	@Test
//	public void deveEncerrarConta () {
//		conversa.mudarInteracaoAtual(EstadoInteracao.CONTA_ENCERRA);
//		RespostaBot resposta = interacao.devolverResposta();
//		assertTrue(resposta.getTexto().toString().contains("Conta recebida"));
//	}
//	@Test
//	public void deveFinalizarAtendimento () {
//		conversa.mudarInteracaoAtual(EstadoInteracao.FIM);
//		RespostaBot resposta = interacao.devolverResposta();
//		assertTrue(resposta.getTexto().toString().contains("Tchau, até logo"));
//	}
	
}

