package conversacao;

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
	

}
