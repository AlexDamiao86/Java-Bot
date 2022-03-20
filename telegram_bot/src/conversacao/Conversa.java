package conversacao;

import java.time.LocalDateTime;

public class Conversa {
	
	private Long identificador;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	private Cliente cliente;
	private Interacao interacaoAtual;
	
	public Conversa(Long identificador, Cliente cliente) {
		this.identificador = identificador;
		this.dataHoraInicio = LocalDateTime.now();
		this.dataHoraInicio = null;
		this.cliente = cliente;
		this.interacaoAtual = null;
	}
	
	public Long getIdentificador() {
		return identificador;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Interacao getInteracaoAtual() {
		return interacaoAtual;
	}

	public void mudarInteracaoAtual(Interacao interacao) {
		this.interacaoAtual = interacao; 
	}
	
	public void encerrarConversa() {
		this.dataHoraFim = LocalDateTime.now();
	}
	
}
