package ar.com.alura.hotel.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Huesped {

    private Integer id, id_reserva;

    private String nombre, apellido, nacion, telefono;
    
    private Date f_nacimiento;
    
	private List<Reservas> reserva;

	public Huesped(int id, String nombre, String apellido, Date f_nacimiento, String nacion, String telefono, int id_reserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.f_nacimiento = f_nacimiento;
		this.nacion = nacion;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacion, String telefono, Integer id_reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.f_nacimiento = fechaNacimiento;
		this.nacion = nacion;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
		}


    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
		return apellido;
	}
    
	public Date getF_nacimiento() {
		return f_nacimiento;
	}
	
	public String getNacion() {
		return nacion;
	}
	
	public String getTelefono() {
		return telefono;
	}


	public Integer getId_reserva() {
		return id_reserva;
	}

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setReserva(List<Reservas> reserva) {
		this.reserva = reserva;
	}

	public void agregar(Reservas reserva) {
		if (this.reserva == null) {
			this.reserva = new ArrayList<>();
		}
		
		this.reserva.add(reserva);
	}
	@Override
	public String toString() {
		return "nombre: " + this.nombre + ", apellido: " + this.apellido + ", fecha de nacimiento: " + this.f_nacimiento + ", nación: " + this.nacion + ", teléfono: " + this.telefono + ", reserva nro: " + this.id_reserva;
	}

	public List<Reservas> getReservas() {
		return this.reserva;
	}

}
