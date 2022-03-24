package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import telegram_bot.Bebida;

public class BebidaTest {
	
	@Test
	public void pegaPrecosDasBebidas() {
		
		System.out.println(Bebida.mostrarMenuBebidas());
		
		assertEquals(12.0, Bebida.CERVEJAHEINEKEN.getPreco().doubleValue());
		assertEquals(10.0, Bebida.CERVEJABUD.getPreco().doubleValue());
		assertEquals(9.0, Bebida.CERVEJASKOL.getPreco().doubleValue());
		assertEquals(15.0, Bebida.PINACOLADA.getPreco().doubleValue());
		assertEquals(20.0, Bebida.VINHO.getPreco().doubleValue());
		assertEquals(14.0, Bebida.CAIPIRINHA.getPreco().doubleValue());
		assertEquals(5.0, Bebida.AGUA.getPreco().doubleValue());
		assertEquals(6.0, Bebida.COCACOLA.getPreco().doubleValue());
		assertEquals(8.0, Bebida.SUCO.getPreco().doubleValue());
	}
}
