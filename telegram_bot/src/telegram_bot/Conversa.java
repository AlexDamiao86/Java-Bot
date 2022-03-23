package telegram_bot;

import java.time.LocalDateTime;

public class Conversa {
	
	private Long identificador;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	private Cliente cliente;
	private EstadoInteracao interacaoAtual;
	
	public Conversa(Long identificador, Cliente cliente) {
		this.identificador = identificador;
		this.dataHoraInicio = LocalDateTime.now();
		this.dataHoraFim = null;
		this.cliente = cliente;
		this.interacaoAtual = EstadoInteracao.INICIO;
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

	public EstadoInteracao getInteracaoAtual() {
		return interacaoAtual;
	}

	public void mudarInteracaoAtual(EstadoInteracao interacao) {
		interacaoAtual = interacao; 
	}
	
	public void encerrarConversa() {
		mudarInteracaoAtual(EstadoInteracao.FIM);
		dataHoraFim = LocalDateTime.now();
	}
	
}
