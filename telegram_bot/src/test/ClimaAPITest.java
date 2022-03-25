package test;

import org.junit.jupiter.api.Test;

import api_clima.ClimaAPI;
import api_clima.ClimaCidade;

/**
 * Testa as quatro formas de consulta de clima usando a API
 * @author fabio
 * @version 1.0
 */

public class ClimaAPITest {
	ClimaCidade clima;
	ClimaAPI climaapi;
	
	@Test
	public void deveObterDadosTemperaturaPorCidade() {
		try {
			System.out.println(ClimaAPI.obtemDadosTemperaturaTexto("Brasilia"));		
		} catch (Exception e) {
			assert(false);
		}
		imprimeLinha();
	}
	
	@Test
	public void deveObterDadosTemperaturaPorCoordenadas() {
		try {
			System.out.println(ClimaAPI.obtemDadosTemperaturaTexto("-15.7801","-47.9292"));		
		} catch (Exception e) {
			assert(false);
		}
		imprimeLinha();
	}

	@Test
	public void deveObterValorTemperaturaPorCidade() {
		try {
			System.out.println("Temperatura em Brasília: " + ClimaAPI.obtemDadosTemperaturaNumero("Brasilia"));		
		} catch (Exception e) {
			assert(false);
		}
		imprimeLinha();		
	}
	@Test
	public void deveObterValorTemperaturaPorCoordenadas() {
		try {
			System.out.println("Temperatura em Brasília: " + ClimaAPI.obtemDadosTemperaturaNumero("-15.7801","-47.9292"));		
		} catch (Exception e) {
			assert(false);
		}
		imprimeLinha();
	}
	
	public void imprimeLinha() {
		System.out.println("\n-------------------------------------------");
	}
}
