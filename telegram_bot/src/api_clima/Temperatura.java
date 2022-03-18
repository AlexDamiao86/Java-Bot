package api_clima;

/**
 * Classe utilizada para integra��o com API de clima da openweathermap
 * Possui alguns dados retornados pela API.
 * @author Vanessa
 * @version 1.0
 */

public class Temperatura {
	private Double temp;	
    private String feels_like;
    private String temp_min;
    private String temp_max;
    private String pressure;
    private String humidity;
    
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public String getFeels_like() {
		return feels_like;
	}
	public void setFeels_like(String feels_like) {
		this.feels_like = feels_like;
	}
	public String getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}
	public String getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

}
