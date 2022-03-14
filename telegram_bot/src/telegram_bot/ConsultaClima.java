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
		String latitude;
		String longitude;
		int opcao;
		Double temperatura;
		Scanner teclado1 = new Scanner(System.in);
		Scanner teclado2 = new Scanner(System.in);
		System.out.println("Digite 1 para consultar a temperatura por lat/long, digite 2 para consultar pelo nome da cidade.");
		opcao = teclado1.nextInt(); 
		if (opcao == 1) {
			System.out.println("Digite a latitude:");
			latitude = teclado2.nextLine();
			System.out.println("Digite a longitude:");
			longitude = teclado2.nextLine();
			System.out.println(ClimaAPI.obtemDadosTemperaturaTexto(latitude, longitude));
			temperatura = ClimaAPI.obtemDadosTemperaturaNumero(latitude, longitude);
			System.out.println("Temperatura na lat/long:"+ latitude + "/" + longitude + " é " + temperatura + " graus");
			teclado1.close();
			teclado2.close();
			
		}else { 
			if (opcao == 2) {
				System.out.println("Gostaria de saber a temperatura de qual cidade?");
				cidade = teclado2.nextLine();
				System.out.println(ClimaAPI.obtemDadosTemperaturaTexto(cidade));
		
				temperatura = ClimaAPI.obtemDadosTemperaturaNumero(cidade);
				System.out.println("Temperatura em "+ cidade + " é " + temperatura + " graus");
				teclado1.close();
				teclado2.close();
			}
		
		}
	}

}
