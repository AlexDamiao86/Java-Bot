package telegram_bot;

public enum EstadoIteracao {
	
	INICIO(1, "INICIO"), 
	PEDIDO_PRODUTO(2, "SELECIONANDO PRODUTO"), 
	PEDIDO_QUANTIDADE(3, "SELECIONANDO QUANTIDADE PRODUTO"), 
	PEDIDO_ADICIONADO(4, "PEDIDO ADICIONADO"),
	CONTA_PARCIAL(5, "MOSTRANDO PARCIAL DA CONTA"), 
	CONTA_ENCERRA(6, "ENCERRANDO CONTA"), 
	FIM(7, "CONVERSA ENCERRADA");
	
	private int identificador; 
	private String descricao;
	
	EstadoIteracao(int identificador, String descricao) {
		this.identificador = identificador;
		this.descricao = descricao;
	}
	
	public int getIdentificador() {
		return identificador;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
