package telegram_bot;

import java.math.BigDecimal;

/**
 * Lista com todos os drinks disponíveis no menu 
 * A cada nova adicao de item deve ser vinculado um numero sequencial e inserido
 * o novo item no switch do construtor da classe
 * @author fabio
 * @version 1.0
 */
public enum Bebida {
	PINACOLADA(1, "Pina Colada",                 new BigDecimal(15.00)),
	CUBALIBRE( 2, "Cuba Libre",                  new BigDecimal(20.00)),
	CERVEJABUD(3, "Cerveja Budweiser 330ml",     new BigDecimal(12.00)),
	CAIPIRINHA(4, "Caipirinha de Limao",         new BigDecimal(14.00)),
	TEQUILA(   5, "Shot de Tequila Jose Cuervo", new BigDecimal(22.00)),
	HEINEKEN(  6, "Cerveja Heineken",            new BigDecimal(12.00));
	
	private int identificador;
	private String descricao;
	private BigDecimal preco;

	Bebida(int identificador,  String descricao, BigDecimal preco) {
		this.identificador = identificador;
		this.descricao = descricao;
		this.preco = preco;
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public static String mostrarMenuBebidas() {
		final int TAM_LINHA = 40;
		String linha = "```";
		
		String menu = "```             ***  MENU DE BEBIDAS  ***"
				+ "\n------------------------------------------------";
		for (Bebida bebida : Bebida.values()) {
			linha = "\n" + bebida.getIdentificador()+ ". " 
					     + bebida.getDescricao();
			while (linha.length() < TAM_LINHA) {
				linha += ".";
			}
			linha += " R$ " + bebida.getPreco().setScale(2);
			menu  += linha;
			linha = "";
		}
		menu  += "```";
		return menu;
	}

}
