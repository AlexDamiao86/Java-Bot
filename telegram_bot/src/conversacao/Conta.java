package conversacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Conta {
	
	private Long identificador; 
	private EstadoConta situacao; 
	private LocalDateTime dataHoraAbertura; 
	private LocalDateTime dataHoraEncerramento;
	private Cliente cliente;
	private ArrayList<Pedido> pedidos;
	
	public Conta(Long identificador, Cliente cliente) {
		this.identificador = identificador; 
		this.cliente = cliente; 
		this.situacao = EstadoConta.CONTA_ABERTA;
		this.dataHoraAbertura = LocalDateTime.now();
		this.dataHoraEncerramento = null;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public EstadoConta getSituacao() {
		return situacao;
	}

	public LocalDateTime getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public LocalDateTime getDataHoraEncerramento() {
		return dataHoraEncerramento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	
	public boolean incluirPedidoConta(Pedido pedido) {
		return this.pedidos.add(pedido);
	}
	
	public String mostrarParcialConta() {
		
		final int TAM_LINHA = 40;
		BigDecimal somaValorPedidos = new BigDecimal(0.00);
		String linha = "";
		
		String cupom = "      ***  DEMONSTRATIVO CONTA  ***"
				+ "\n------------------------------------------";
				
		for (Pedido pedido: pedidos) {
			linha = "\n" + pedido.getQuantidade() + " "  
		                 + pedido.getBebida().getDescricao(); 
			
			while (linha.length() < TAM_LINHA) {
				linha += ".";
			}
			
			linha += " R$ " + pedido.getValorPedido().setScale(2);
			cupom += linha;
			
			somaValorPedidos.add(pedido.getValorPedido());
			
			linha = "";      
		}
		
		linha = "\n *** VALOR TOTAL DA CONTA";
		while (linha.length() < TAM_LINHA) {
			linha += ".";
		}
		linha += " R$ " + somaValorPedidos.setScale(2);
		cupom += linha;

		return cupom;
	}
	
	public void encerrarConta() {
		this.mostrarParcialConta();
		this.situacao = EstadoConta.CONTA_ENCERRADA;
		this.dataHoraEncerramento = LocalDateTime.now();
	}
	

}
