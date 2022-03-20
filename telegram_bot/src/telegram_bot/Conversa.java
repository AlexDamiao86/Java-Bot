package telegram_bot;

import java.time.LocalDateTime;

public class Conversa {
	
	private Long identificador;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	private Cliente cliente;
	private EstadoIteracao iteracaoAtual;
	
	public Conversa(Long identificador, Cliente cliente) {
		this.identificador = identificador;
		this.dataHoraInicio = LocalDateTime.now();
		this.dataHoraInicio = null;
		this.cliente = cliente;
		this.iteracaoAtual = EstadoIteracao.INICIO;
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

	public EstadoIteracao getInteracaoAtual() {
		return iteracaoAtual;
	}

	public void mudarInteracaoAtual(EstadoIteracao interacao) {
		iteracaoAtual = interacao; 
	}
	
	public void encerrarConversa() {
		mudarInteracaoAtual(EstadoIteracao.FIM);
		dataHoraFim = LocalDateTime.now();
	}
	
}
