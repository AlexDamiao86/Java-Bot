package telegram_bot;

import java.util.Scanner;
/**
 * Classe para teste de consulta do clima
 * @author Vanessa
 * @version 1.0
 */


public class ConsultaClima {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String cidade;
		Double temperatura;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Gostaria de saber a temperatura de qual cidade?");
		cidade = teclado.nextLine();
		System.out.println(ClimaAPI.obtemDadosTemperaturaTexto(cidade));
		
		temperatura = ClimaAPI.obtemDadosTemperaturaNumero(cidade);
		System.out.println("Temperatura em "+ cidade + " é " + temperatura + " graus");
		teclado.close();
		
	}

}
