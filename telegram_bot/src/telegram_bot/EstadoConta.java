package telegram_bot;

public enum EstadoConta {
	
	CONTA_ABERTA(1, "CONTA ABERTA"), 
	CONTA_ENCERRADA(2, "CONTA ENCERRADA");
	
	private int identificador;
	private String descricao;
	
	EstadoConta(int identificador, String descricao) {
		this.identificador = identificador;
		this.descricao = descricao;
	}
	
	public int getIdentificador() {
		return this.identificador;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
