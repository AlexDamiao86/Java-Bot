package telegram_bot;

import java.math.BigDecimal;
import java.time.Duration;
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
	
	public Pedido getUltimoPedido() {
		return this.pedidos.get(this.pedidos.size()-1);
	}
	private BigDecimal valorTotalConta() {
		BigDecimal somaValorPedidos = new BigDecimal(0.00);

		for (Pedido pedido: pedidos) {
			somaValorPedidos = somaValorPedidos.add(pedido.getValorPedido());
			
		}
		return somaValorPedidos;
	}
	
	private long[] tempoPermanenciaCliente() {
		
	    final int MINUTES_PER_HOUR = 60;
	    final int SECONDS_PER_MINUTE = 60;
	    final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
	    
		Duration duration = Duration.between(dataHoraAbertura, LocalDateTime.now());
        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
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

		cupom += "\n-------------------------------------------------";
		linha += "\nTEMPO DE PERMANENCIA: ";
		long[] tempoPermanencia = tempoPermanenciaCliente();
		linha += tempoPermanencia[0] + ":" + tempoPermanencia[1] + ":" + tempoPermanencia[2];
		cupom += linha;
		return cupom;
	}
	
	public String encerrarConta() {
		this.situacao = EstadoConta.CONTA_ENCERRADA;
		this.dataHoraEncerramento = LocalDateTime.now();
		return this.mostrarParcialConta();
	}
	
}
