package telegram_bot;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Lista com todos os drinks disponíveis no menu 
 * A cada nova adicao de item deve ser vinculado um numero sequencial e inserido
 * o novo item no switch do construtor da classe
 * @author fabio
 * @version 1.0
 */
public enum Bebida {	
	CERVEJAHEINEKEN(1, "Heineken",                    new BigDecimal(12.00)),
	CERVEJABUD(		2, "Budweiser",                   new BigDecimal(10.00)),
	CERVEJASKOL(	3, "Skol",			       	      new BigDecimal(09.00)),	
	PINACOLADA(		4, "Pina Colada",                 new BigDecimal(15.00)),
	VINHO( 	   	    5, "Vinho",                       new BigDecimal(20.00)),
	CAIPIRINHA(		6, "Caipirinha",                  new BigDecimal(14.00)),
	AGUA(			7, "Agua",                        new BigDecimal(05.00)),
	COCACOLA(  		8, "Coca-cola",		              new BigDecimal(06.00)),
	SUCO(			9, "Suco", 						  new BigDecimal(08.00));

	private static final Map<String, Bebida> bebidaPorDescricao = new HashMap<>();
	
	static {
		for(Bebida bebida: Bebida.values()) {
			bebidaPorDescricao.put(bebida.getDescricao(), bebida);
		}
	}
	
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
		String linha = "";
		
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
	
	public static Bebida getBebidaPorDescricao(String nomeBebida) {
		return bebidaPorDescricao.get(nomeBebida);
	}
	
	public static boolean ehBebidaValida(String nomeBebida) {
		return (bebidaPorDescricao.get(nomeBebida) != null);
	}
	
}
