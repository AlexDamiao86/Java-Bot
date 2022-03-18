package api_clima;

/**
 * Classe utilizada para integra��o com API de clima da openweathermap
 * Possui alguns dados retornados pela API.
 * @author Vanessa
 * @version 1.0
 */

public class Vento {
	private String speed;
    private String deg;
    
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDeg() {
		return deg;
	}
	public void setDeg(String deg) {
		this.deg = deg;
	}

}
