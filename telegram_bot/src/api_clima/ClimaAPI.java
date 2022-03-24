package api_clima;

import org.springframework.web.client.RestTemplate;

/**
 * *Classe que faz o acesso aos dados da API de clima da openweathermap
 * Gerar o token no site da openweather e colocar na vari�vel TOKEN.
 * @author Vanessa
 * @version 1.0
 */

public class ClimaAPI {
	private static final String TOKEN = "75be859539bd162d18bf2ff72277dfb3";
	private static RestTemplate restTemplate = new RestTemplate();
	
	
	/*Consulta os dados da API a partir da cidade informada e coloca em um objeto da classe ClimaCidade*/
	private static ClimaCidade obtemDadosClima(String cidade) throws Exception {
		
		ClimaCidade climaAtual = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="
			+ cidade + "&units=metric&lang=PT_BR&appid=" + TOKEN, ClimaCidade.class);
		return climaAtual;
					
	}
	/*Consulta os dados da API a partir da latitude e longitude informada e coloca em um objeto da classe ClimaCidade*/
	private static ClimaCidade obtemDadosClima(String latitude, String longitude) throws Exception {
		
		
		ClimaCidade climaAtual = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?lat="
		+ latitude + "&lon=" + longitude +"&units=metric&lang=PT_BR&appid=" + TOKEN, ClimaCidade.class);	
		return climaAtual;
			
	    
	}
	
	/*Consulta a temperatura por nome da cidade e devolve um texto com a cidade e temperatura*/
	public static String obtemDadosTemperaturaTexto(String cidade) throws Exception {
		
		ClimaCidade climaAtual = obtemDadosClima(cidade);
		String clima = "Cidade: " + climaAtual.getName()  + "\nTemperatura: "
				+ climaAtual.getTemp().getTemp()  + " graus"; 

		return clima;

	}
	/*Consulta a temperatura por latitude longitude e devolve um texto com a cidade e temperatura*/
	public static String obtemDadosTemperaturaTexto(String latitude, String longitude) throws Exception {
		
		ClimaCidade climaAtual = obtemDadosClima(latitude, longitude);
		String clima = "Cidade: " + climaAtual.getName()  + "\nTemperatura: "
				+ climaAtual.getTemp().getTemp()  + " graus"; 

		return clima;

	}
	/*Consulta a temperatura pelo nome da cidade e devolve o valor no formato double*/
	public static Double obtemDadosTemperaturaNumero(String cidade) throws Exception {
		
		ClimaCidade climaAtual = obtemDadosClima(cidade);
		return climaAtual.getTemp().getTemp();

	}
	
	/*Consulta a temperatura pela latitude e longitude e devolve o valor no formato double*/
	public static Double obtemDadosTemperaturaNumero(String latitude, String longitude) throws Exception {
		
		ClimaCidade climaAtual = obtemDadosClima(latitude, longitude);
		return climaAtual.getTemp().getTemp();

	}

}



