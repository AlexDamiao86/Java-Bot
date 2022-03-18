package telegram_bot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PedidoTest {

	Pedido pedido;
	
	@BeforeEach
	private void criaPedido() {
	 pedido = new Pedido();
	}
	
	@Test
	void deveAceitarPedidoDe5Cervejas() {
		
		pedido.anota(Bebidas.CERVEJABUD, 5);
		
		assertEquals(5, pedido.contaBebidas());
	}
	
	@Test
	void deveAceitar1PedidoDeCadaDrink() {
		
		pedido.anota(Bebidas.CAIPIRINHA, 1);
		pedido.anota(Bebidas.CERVEJABUD, 1);
		pedido.anota(Bebidas.CUBALIBRE, 1);
		pedido.anota(Bebidas.PINACOLADA, 1);
		pedido.anota(Bebidas.TEQUILA, 1);
		
		assertEquals(5, pedido.contaBebidas());
		
		System.out.println(pedido.consulta());
	}
	
	@Test
	void deveMostrarListaVaziaSeNaoHouverPedidos() {
		assertEquals(0, pedido.contaBebidas());
	}
	
	@Test
	void deveExcluir3ItensDaListaDe10() {
		pedido.anota(Bebidas.CUBALIBRE, 7);
		pedido.anota(Bebidas.CERVEJABUD, 3);
		
		System.out.println(pedido.risca(Bebidas.CUBALIBRE, 3));
		
		assertEquals(7, pedido.contaBebidas());
	}
	
	@Test
	void naoDeveExcluir5DaListaDe3() {
		pedido.anota(Bebidas.CAIPIRINHA, 3);
		
		System.out.println(pedido.risca(Bebidas.CAIPIRINHA, 5));
		
		assertEquals(3, pedido.contaBebidas());
	}
	
	@Test
	void naoDeveExcluirDeListaVazia() {
				
		assertEquals("Não houve nenhum pedido de Pina Colada até o momento.", 
				        pedido.risca(Bebidas.PINACOLADA, 1));
	}
	

}
