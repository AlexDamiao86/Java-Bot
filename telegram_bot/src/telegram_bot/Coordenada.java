package telegram_bot;

/**
 * Classe utilizada para integração com API de clima da openweathermap
 * Possui alguns dados retornados pela API.
 * @author Vanessa
 * @version 1.0
 */

public class Coordenada {
	
	private String lon;
	private String lat;
	
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	} 

}
