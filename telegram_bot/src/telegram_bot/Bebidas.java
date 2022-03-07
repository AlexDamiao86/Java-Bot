package telegram_bot;

public enum Bebidas {
	PINACOLADA(0),
	CUBALIBRE(1),
	CERVEJABUD(2),
	CAPIRINHA(3);
	
	private int item;
	private String nomeBebida;

	Bebidas(int item) {
		this.item = item;
		
		switch (item) {
		case 0:
			nomeBebida = "Pina Colada";
			break;
		case 1:
			nomeBebida = "Cuba Libre";
			break;
		case 2:
			nomeBebida = "Cerveja Budweiser";
			break;
		case 3:
			nomeBebida = "Caipirinha de limao";
			break;

		default:
			//Criar e lançar exception
			nomeBebida = "Inexistente no menu!";
			break;
		}
	}

	public String getNomeBebida() {
		return nomeBebida;
	}

	int getNum() {
		return item;
	}
}
