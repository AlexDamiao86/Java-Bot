package conversacao;

public class Cliente {
	private Long identificador;
	private String nome;
	private String sobreNome;
	private Iteracao conversa;
	private String perguntaResposta;
	
	public Cliente(Long id, String nome, String sobreNome) {
		this.identificador = id;
		this.nome = nome;
		this.sobreNome = sobreNome;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public String getNome() {
		return nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}	
	
	public void setConversa(Iteracao conv) {
		this.conversa = conv;
	}
	
	public String resposta() {
		this.conversa.mudaEstado(perguntaResposta);
		return this.conversa.getResposta();
	}
	
	public void setPergunta(String perg) {
		this.perguntaResposta = perg;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.identificador.equals(((Cliente)obj).getIdentificador());
	}
}
