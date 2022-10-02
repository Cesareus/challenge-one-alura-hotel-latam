package ar.com.alura.hotel.conversorMoneda.selectorMoneda;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

import ar.com.alura.hotel.conversorMoneda.aplicaciones.ConversionMoneda;

public class SelectorMonedas {

	public String OpcionesConversor(String cambio, String valor) {
		String resultado = "";

		switch (cambio) {

		case "De pesos a dolar": {
			resultado = ConversorMoneda(valor, "ARS", "USD")+" USD";
			break;
		}
		case "De pesos a euro": {
			resultado = ConversorMoneda(valor, "ARS", "EUR")+" Euros";
			break;
		}
		case "De pesos a Libras": {
			resultado = ConversorMoneda(valor, "ARS", "GBP")+" Libras";
			break;
		}
		case "De pesos a yen": {
			resultado = ConversorMoneda(valor, "ARS", "JPY")+" Yens";
			break;
		}
		case "De pesos a Won coreano": {
			resultado = ConversorMoneda(valor, "ARS", "KRW")+" Wons";
			break;
		}
		case "De dolar a pesos": {
			resultado = ConversorMoneda(valor, "USD", "ARS");
			break;
		}
		case "De Euro a pesos": {
			resultado = ConversorMoneda(valor, "EUR", "ARS");
			break;
		}
		case "De libras a pesos": {
			resultado = ConversorMoneda(valor, "GBP", "ARS");
			break;
		}
		case "De won coreano a pesos": {
			resultado = ConversorMoneda(valor, "KRW", "ARS");
			break;
		}
		default: {
			resultado = valor;
		}
			;
		}
		return resultado;
	}
	
	public String ConversorMoneda(String valor, String UnidadOrigen, String unidadCambio) {
		String redondeo = "";
		try (ConversionMoneda conversor = new ConversionMoneda()) {				
				Object data = conversor.retornarValores(valor, UnidadOrigen, unidadCambio);
				redondeo = String.valueOf((double)Math.round(Double.valueOf(data.toString())*100d)/100);		
			
		} catch (Exception ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return redondeo;
	}	

}
