package telegram_bot;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Classe utilizada para integração com API de clima da openweathermap.
 * Possui todos os dados retornados pela API.
 * @author Vanessa
 * @version 1.0
 */

public class ClimaCidade {
	
	@JsonProperty("coord")
	Coordenada coord;
	@JsonProperty("weather")
	Clima clima[];
	private String base;
	@JsonProperty("main")
	Temperatura temp;
	private String visibility;
	@JsonProperty("wind")
	Vento vento;
	@JsonProperty("clouds")
	Nuvens nuvem;
	private String dt;
	Sys sys;
	private String timezone;
    private String id;
    private String name;
    private String cod;
    
	public Coordenada getCoord() {
		return coord;
	}
	public void setCoord(Coordenada coord) {
		this.coord = coord;
	}
	public Clima[] getClima() {
		return clima;
	}
	public void setClima(Clima[] clima) {
		this.clima = clima;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Temperatura  getTemp() {
		return temp;
	}
	public void setTemp(Temperatura temp) {
		this.temp = temp;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Vento getVento() {
		return vento;
	}
	public void setVento(Vento vento) {
		this.vento = vento;
	}
	public Nuvens getNuvem() {
		return nuvem;
	}
	public void setNuvem(Nuvens nuvem) {
		this.nuvem = nuvem;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Sys getSys() {
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
    
	

}
