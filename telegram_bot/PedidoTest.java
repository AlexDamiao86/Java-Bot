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
		
		pedido.anota(Bebida.CERVEJABUD, 5);
		
		assertEquals(5, pedido.contaBebidas());
	}
	
	@Test
	void deveAceitar1PedidoDeCadaDrink() {
		
		pedido.anota(Bebida.CAIPIRINHA, 1);
		pedido.anota(Bebida.CERVEJABUD, 1);
		pedido.anota(Bebida.CUBALIBRE, 1);
		pedido.anota(Bebida.PINACOLADA, 1);
		pedido.anota(Bebida.TEQUILA, 1);
		
		assertEquals(5, pedido.contaBebidas());
		
		System.out.println(pedido.consulta());
	}
	
	@Test
	void deveMostrarListaVaziaSeNaoHouverPedidos() {
		assertEquals(0, pedido.contaBebidas());
	}
	
	@Test
	void deveExcluir3ItensDaListaDe10() {
		pedido.anota(Bebida.CUBALIBRE, 7);
		pedido.anota(Bebida.CERVEJABUD, 3);
		
		System.out.println(pedido.risca(Bebida.CUBALIBRE, 3));
		
		assertEquals(7, pedido.contaBebidas());
	}
	
	@Test
	void naoDeveExcluir5DaListaDe3() {
		pedido.anota(Bebida.CAIPIRINHA, 3);
		
		System.out.println(pedido.risca(Bebida.CAIPIRINHA, 5));
		
		assertEquals(3, pedido.contaBebidas());
	}
	
	@Test
	void naoDeveExcluirDeListaVazia() {
				
		assertEquals("Não houve nenhum pedido de Pina Colada até o momento.", 
				        pedido.risca(Bebida.PINACOLADA, 1));
	}
	

}
