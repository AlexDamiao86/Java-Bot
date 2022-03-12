package telegram_bot;

import org.springframework.web.client.RestTemplate;

/**
 * *Classe que faz o acesso aos dados da API de clima da openweathermap
 * Gerar o token no site da openweather e colocar na variável TOKEN.
 * @author Vanessa
 * @version 1.0
 */

public class ClimaAPI {
	private static final String TOKEN = "";
	private static RestTemplate restTemplate = new RestTemplate();
	
	
	/*Consulta os dados da API a partir da cidade informada e coloca em um objeto da classe ClimaCidade*/
	private static ClimaCidade obtemDadosClima(String cidade) throws Exception {
		
		ClimaCidade climaAtual = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="
				+ cidade + "&units=metric&lang=PT_BR&appid=" + TOKEN, ClimaCidade.class);	
		return climaAtual;
	}
	/*Consulta a temperatura e devolve um texto com a cidade e temperatura*/
	public static String obtemDadosTemperaturaTexto(String cidade) throws Exception {
		
		ClimaCidade climaAtual = obtemDadosClima(cidade);
		String clima = "Cidade: " + climaAtual.getName()  + "\nTemperatura: "
				+ climaAtual.getTemp().getTemp()  + " graus"; 

		return clima;

	}
	/*Consulta a temperatura e devolve o valor no formato double*/
	public static Double obtemDadosTemperaturaNumero(String cidade) throws Exception {
		
		ClimaCidade climaAtual = obtemDadosClima(cidade);
		return climaAtual.getTemp().getTemp();

	}

}



