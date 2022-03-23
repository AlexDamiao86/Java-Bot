package telegram_bot;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Conta {
	
	private Long identificador; 
	private EstadoConta situacao; 
	private LocalDateTime dataHoraAbertura; 
	private LocalDateTime dataHoraEncerramento;
	private Cliente cliente;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
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
	
	private BigDecimal valorTotalConta() {
		BigDecimal somaValorPedidos = new BigDecimal(0.00);

		for (Pedido pedido: pedidos) {
			somaValorPedidos = somaValorPedidos.add(pedido.getValorPedido());
			
		}
		return somaValorPedidos;
	}
	
	public String mostrarParcialConta() {
		
		final int TAM_LINHA = 40;
		String linha = "";
		
		String cupom = "```         ***  DEMONSTRATIVO CONTA  ***"
				+ "\n-------------------------------------------------";
				
		for (Pedido pedido: pedidos) {
			linha = "\n" + pedido.getQuantidade() + " "  
		                 + pedido.getBebida().getDescricao(); 
			
			while (linha.length() < TAM_LINHA) {
				linha += ".";
			}
			
			linha += " R$ " + pedido.getValorPedido().setScale(2);
			cupom += linha;
						
			linha = "";      
		}
		cupom += "\n_________________________________________________";
		linha = "\nVALOR TOTAL DA CONTA";
		while (linha.length() < TAM_LINHA) {
			linha += ".";
		}
		linha += " R$ " + valorTotalConta().setScale(2);
		cupom += linha;
		cupom += "\n-------------------------------------------------```";

		return cupom;
	}
	
	public String encerrarConta() {
		this.situacao = EstadoConta.CONTA_ENCERRADA;
		this.dataHoraEncerramento = LocalDateTime.now();
		return this.mostrarParcialConta();
	}
	
}
