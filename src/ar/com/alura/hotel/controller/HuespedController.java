package ar.com.alura.hotel.controller;

import java.sql.Date;
import java.util.List;

import ar.com.alura.hotel.dao.HuespedesDAO;
import ar.com.alura.hotel.factory.ConnectionFactory;
import ar.com.alura.hotel.modelo.Huesped;


public class HuespedController {

	private HuespedesDAO huespedDAO;

	public HuespedController() {		
		this.huespedDAO = new HuespedesDAO(new ConnectionFactory().recuperaConexion());
	}

	public List<Huesped> listarHuespedes() {
		return huespedDAO.listarHuespedes();
	}
	
	public List<Huesped> listarHuespedes(String busqueda) {
		return huespedDAO.listarHuespedes(busqueda);
	}
	
	public void guardar(Huesped huesped, int reserva) {
		huespedDAO.guardar(huesped, reserva);
	}

	public int modificarHuesped(Integer id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer id_reserva) {
		return huespedDAO.modificarHuesped(id,nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva);
	}

	public int eliminarHuesped(Integer id) {
		return huespedDAO.eliminarHuesped(id);
	}

}
