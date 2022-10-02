package ar.com.alura.hotel.conversorMoneda.selectorMoneda;

public class PaisMoneda {
	
	public static String paisMoneda(String pais) {
		String cambio = "";
		switch (pais) {
		
		case "Argentina": {
			cambio = "otro";
			break;
		}
		case "Francia": {
			cambio = "De pesos a euro";
			System.out.println(cambio);
			break;			
		}
		case "España": {
			cambio = "De pesos a euro";
			break;
		}
		case "Alemania": {
			cambio = "De pesos a euro";
			break;
		}
		case "Belgica": {
			cambio = "De pesos a euro";
			break;
		}
		case "Suiza": {
			cambio = "De pesos a euro";
			break;
		}
		case "Hungría": {
			cambio = "De pesos a euro";
			break;
		}
		case "Estados Unidos": {
			cambio = "De pesos a dolar";
			break;
		}
		case "Corea": {
			cambio = "De pesos a Won coreano";
			break;
		}
		case "Japón": {
			cambio = "De pesos a yen";
			break;
		}
		case "Inglaterra": {
			cambio = "De pesos a Libras";
			break;
		}
		default: {

		};
		}		
		return cambio;
	}

}
