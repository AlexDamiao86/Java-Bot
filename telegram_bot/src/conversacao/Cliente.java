package conversacao;

import java.util.ArrayList;
import java.util.Stack;

public class Cliente {
	private Long identificador;
	private String nome;
	private String sobrenome;
	private Stack<Conversa> conversas = new Stack<Conversa>();
	private ArrayList<Conta> contas = new ArrayList<Conta>();
	
	public Cliente(Long identificador, String nome, String sobrenome) {
		this.identificador = identificador;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}	
	
	public Conversa iniciarConversa(Long identificadorConversa) {
		Conversa conversa = new Conversa(identificadorConversa, this); 
		return this.conversas.push(conversa);
	}
	
	public Conta abrirConta() {
		Long idProximaConta = (long) (contas.size() + 1);
		Conta conta = new Conta(idProximaConta, this);
		this.contas.add(conta);
		return conta;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.identificador.equals(((Cliente)obj).getIdentificador());
	}
	
}
