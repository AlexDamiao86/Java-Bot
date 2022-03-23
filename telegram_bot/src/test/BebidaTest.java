package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telegram_bot.Bebida;

public class BebidaTest {
	
	@Test
	public void pegaPrecosDasBebidas() {
		
		System.out.println(Bebida.mostrarMenuBebidas());
		
		assertEquals(15.0, Bebida.PINACOLADA.getPreco().doubleValue());
		assertEquals(20.0, Bebida.CUBALIBRE.getPreco().doubleValue());
		assertEquals(12.0, Bebida.CERVEJABUD.getPreco().doubleValue());
		assertEquals(14.0, Bebida.CAIPIRINHA.getPreco().doubleValue());
		assertEquals(22.0, Bebida.TEQUILA.getPreco().doubleValue());
		assertEquals(12.0, Bebida.HEINEKEN.getPreco().doubleValue());
	}
}
