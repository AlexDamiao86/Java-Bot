package telegram_bot;

public enum EstadoInteracao {
	
	INICIO(1, "INICIO"), 
	PEDIDO_PRODUTO(2, "SELECIONANDO PRODUTO"), 
	PEDIDO_QUANTIDADE(3, "SELECIONANDO QUANTIDADE PRODUTO"), 
	PEDIDO_ADICIONADO(4, "PEDIDO ADICIONADO"),
	PEDIDO_AJUDA(5, "PEDINDO AJUDA"),
	PEDIDO_SUGESTAO(6, "PEDINDO SUGESTAO"),
	PEDIDO_MENU(7,"PEDINDO O MENU"),
	CONTA_PARCIAL(8, "MOSTRANDO PARCIAL DA CONTA"), 
	CONTA_ENCERRA(9, "ENCERRANDO CONTA"), 
	FIM(10, "CONVERSA ENCERRADA");
	
	private int identificador; 
	private String descricao;
	
	EstadoInteracao(int identificador, String descricao) {
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
