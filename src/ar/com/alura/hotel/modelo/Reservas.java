package ar.com.alura.hotel.modelo;

import java.sql.Date;

public class Reservas {

	private Integer id;
	private Date checkin;
	private Date checkout;
	private String valorReserva;
	private String formaPago;
	private Integer huespedId;

		
	public Reservas(Integer id, Date checkin, Date checkout, String valorReserva, String formaPago) {
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
		this.valorReserva = valorReserva;
		this.formaPago = formaPago;
	}


	public Reservas(Date checkin, Date checkout, String valorReserva, String formaPago) {
		this.checkin = checkin;
		this.checkout = checkout;
		this.valorReserva = valorReserva;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	public String getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(String valorReserva) {
		this.valorReserva = valorReserva;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public void setHuespedId(Integer huespedId) {
		this.huespedId = huespedId;		
	}
	
	public int getHuespedId() {
		return this.huespedId;
	}
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, Checkin: %s, Checkout: %s, Valor: %s, Forma de Pago: %s}", this.id, this.checkin,
				this.checkout, this.valorReserva, this.formaPago);
	}
}
