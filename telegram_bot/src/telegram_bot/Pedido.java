package telegram_bot;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {
	
	private int identificador; 
	private Conta conta; 
	private Bebida bebida; 
	private int quantidade; 
	private LocalDateTime dataHoraPedido;
	private BigDecimal valorPedido;
	
	public Pedido(int identificador, Conta conta, Bebida bebida, int quantidade) {
		this.identificador = identificador;
		this.conta = conta;
		this.bebida = bebida;
		this.setQuantidade(quantidade);
		this.dataHoraPedido = LocalDateTime.now();		
		BigDecimal bdQuantidade = BigDecimal.valueOf(quantidade); 
		this.valorPedido = bebida.getPreco().multiply(bdQuantidade);
	}
	
	public Pedido(int identificador, Conta conta, Bebida bebida) {
		this.identificador = identificador;
		this.conta = conta;
		this.bebida = bebida;
		this.setQuantidade(0);
		this.dataHoraPedido = LocalDateTime.now();		 
	}
	
	public int getIdentificador() {
		return identificador;
	}

	public Conta getConta() {
		return conta;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getDataHoraPedido() {
		return dataHoraPedido;
	}

	public BigDecimal getValorPedido() {
		BigDecimal bdQuantidade = BigDecimal.valueOf(quantidade); 
		this.valorPedido = bebida.getPreco().multiply(bdQuantidade);
		return valorPedido;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade; 
	}
	
	

}
