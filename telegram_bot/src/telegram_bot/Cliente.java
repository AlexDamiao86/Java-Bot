package telegram_bot;

import java.util.Stack;

public class Cliente {
	private Long identificador;
	private String nome;
	private String sobrenome;
	private Conversa conversa;
	private Stack<Conta> contas = new Stack<Conta>();

	public Cliente(Long identificador, String nome, String sobrenome) {
		this.identificador = identificador;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.conversa = new Conversa(identificador, this);
	}

	public Long getIdentificador() {
		return identificador;
	}

	public boolean isContaAberta() {
		if (contas.empty()) {
			return false;
		} else {
			return (contas.peek().getSituacao() == EstadoConta.CONTA_ABERTA);
		}
	}

	public Conta getConta() {
		if (!this.isContaAberta()) {
			this.abrirConta();
		}
		return contas.peek();
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public Conversa getConversa() {
		return this.conversa;
	}

	public Conta abrirConta() {
		Long idProximaConta = (long) (contas.size() + 1);
		Conta conta = new Conta(idProximaConta, this);
		this.contas.push(conta);
		return conta;
	}

	@Override
	public boolean equals(Object obj) {
		return this.identificador.equals(((Cliente) obj).getIdentificador());
	}

}
