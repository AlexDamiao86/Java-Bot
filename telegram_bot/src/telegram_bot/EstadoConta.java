package telegram_bot;

public enum EstadoConta {
	
	CONTA_ABERTA("CONTA ABERTA"), 
	CONTA_ENCERRADA("CONTA ENCERRADA");
	
	private String descricao;
	
	EstadoConta(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
