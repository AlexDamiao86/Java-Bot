package telegram_bot;

/**
 * Classe utilizada para integração com API de clima da openweathermap
 * Possui alguns dados retornados pela API.
 * @author Vanessa
 * @version 1.0
 */

public class Clima {
	private String id;
	private String main;
	private String description;
	private String icon;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	

}
