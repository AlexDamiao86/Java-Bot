package conversacao;

public class Iteracao {
	
	private String estadoAtual;
	private String resposta;
	private Cliente cliente;
	
	public Iteracao(Cliente c) {
		this.estadoAtual = "inicio";
		this.resposta = "Não Entendi...";
		this.cliente = c;
	}
	
	public boolean mudaEstado(String perguntaResposta) {
		if (estadoAtual.equals("inicio")&& ehSaudacao(perguntaResposta)) {
			this.estadoAtual = "menu_inicio";
			this.resposta = "Oi, "+ cliente.getNome()+" , Tudo Bem!";
			return true;
		}
		return false;
	} 
	
	private boolean ehSaudacao(String perguntaResposta) {
		return perguntaResposta.matches("^(o|O)i!{0,1}|^(B|b)om (D|d)ia!{0,1}|^(B|b)oa (t|T)arde!{0,1}|^(B|b)oa (n|N)oite!{0,1}");
	}

	public String getResposta() {
		return this.resposta;
	}
}
