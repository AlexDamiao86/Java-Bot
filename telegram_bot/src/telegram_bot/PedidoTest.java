package telegram_bot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class PedidoTest {

	@Test
	void deveAceitarPedidoDe5Cervejas() {
		Pedido pedido = new Pedido();
		
		pedido.anota(Bebidas.CERVEJABUD, 5);
		
		assertEquals(5, pedido.consulta());
	}
	
	@Test
	void deveAceitar1PedidoDeCadaDrink() {
		Pedido pedido = new Pedido();
		
		pedido.anota(Bebidas.CAPIRINHA, 1);
		pedido.anota(Bebidas.CERVEJABUD, 1);
		pedido.anota(Bebidas.CUBALIBRE, 1);
		pedido.anota(Bebidas.PINACOLADA, 1);
		
		assertEquals(4, pedido.consulta());;
	}

}
