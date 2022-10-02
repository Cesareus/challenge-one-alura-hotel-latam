package ar.com.alura.hotel.controller;

import java.sql.Date;
import java.util.List;

import ar.com.alura.hotel.dao.ReservasDAO;
import ar.com.alura.hotel.factory.ConnectionFactory;
import ar.com.alura.hotel.modelo.Reservas;

public class ReservaController {

	private ReservasDAO reservasDAO;

	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservasDAO = new ReservasDAO(factory.recuperaConexion());

	}

	public int modificar(Integer id, Date fecha_entrada, Date fecha_salida, String valor, String forma_Pago) {
		return reservasDAO.modificar(id, fecha_entrada, fecha_salida, valor, forma_Pago);
	}

	public int eliminar(Integer id) {
		return reservasDAO.eliminar(id);
	}

	public List<Reservas> listarReservas() {
		return reservasDAO.listarReservas();
	}

	public List<Reservas> listarReservas(String busqueda) {
		return reservasDAO.listarReservas(busqueda);
	}
	
	public void guardar(Reservas reserva) {
		reservasDAO.guardar(reserva);
	}

}
