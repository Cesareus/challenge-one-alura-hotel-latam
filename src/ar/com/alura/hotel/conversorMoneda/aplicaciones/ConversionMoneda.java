package ar.com.alura.hotel.conversorMoneda.aplicaciones;

import java.io.IOException;
import org.json.JSONObject;

import ar.com.alura.hotel.conversorMoneda.getDataApi.DataConverter;


public class ConversionMoneda implements AutoCloseable {

	DataConverter data = new DataConverter();

	public Object retornarValores(String valor, String Monedaorigen, String Monedacambio)
			throws IOException {

		JSONObject response = data.run("https://api.apilayer.com/currency_data/convert?from=" + Monedaorigen + "&to="
				+ Monedacambio + "&amount=" + valor);
		System.out.println("https://api.apilayer.com/currency_data/convert?from=" + Monedaorigen + "&to=" + Monedacambio
				+ "&amount=" + valor);
		return response.get("result");
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}
}
