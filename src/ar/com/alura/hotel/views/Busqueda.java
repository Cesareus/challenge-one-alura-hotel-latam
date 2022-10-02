package ar.com.alura.hotel.views;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.alura.hotel.controller.HuespedController;
import ar.com.alura.hotel.controller.ReservaController;
import ar.com.alura.hotel.modelo.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	private List<Reservas> reservas;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 320, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/reservado.png")), tbReservas,
				null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		modelo.addRow(new Object[] { "ID", "CHECK-IN", "CHECK-OUT", "VALOR", "FORMA DE PAGO" });
		cargarTablaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/pessoas.png")), tbHuespedes,
				null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");

		modeloH.addRow(new Object[] { "ID", "NOMBRE", "APELLIDO", "FECHA DE NACIMIENTO", "NACION", "TELEFONO",
				"ID RESERVA" });
		cargarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				System.out.println(txtBuscar.getText());
				if (txtBuscar.getText() != "") {
					cargarBusqueda();
				} else if (txtBuscar.getText() == "") {
					cargarTablaHuespedes();
					cargarTablaReservas();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
				limpiarTabla();
				cargarTablaHuespedes();
				cargarTablaReservas();
			}
		});

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// FUNCION PARA ELIMINAR
				eliminar();
				limpiarTabla();
				cargarTablaHuespedes();
				cargarTablaReservas();
			}
		});
	}

	private void cargarTablaReservas() {

		ReservaController reservaController = new ReservaController();

		var reservas = reservaController.listarReservas();

		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getCheckin(),
				reserva.getCheckout(), reserva.getValorReserva(), reserva.getFormaPago() }));
	}

	private void cargarTablaHuespedes() {

		HuespedController huespedController = new HuespedController();

		var huespedes = huespedController.listarHuespedes();

		huespedes.forEach(huesped -> modeloH.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getF_nacimiento(), huesped.getNacion(), huesped.getTelefono(),
				huesped.getId_reserva() }));
	}

	private void cargarBusqueda() {
		reservas = new ArrayList<>();

		ReservaController reservaController = new ReservaController();
		HuespedController huespedController = new HuespedController();

		var huespedes = huespedController.listarHuespedes(txtBuscar.getText());
		huespedes.forEach(huesped -> {modeloH.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getF_nacimiento(), huesped.getNacion(), huesped.getTelefono(),
				huesped.getId_reserva() });
				reservas = reservaController.listarReservas(String.valueOf(huesped.getId_reserva()));	
		});
			

		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getCheckin(),
				reserva.getCheckout(), reserva.getValorReserva(), reserva.getFormaPago() }));


	}

	  private void limpiarTabla() {
			while (modeloH.getRowCount() != 1) {
				modeloH.removeRow(1);
			}
			while (modelo.getRowCount() != 1) {
				modelo.removeRow(1);
			}
	    }

	private boolean noTieneFilaElegidaHuespedes() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}

	private boolean noTieneFilaElegidaReservas() {
		return tbReservas.getSelectedColumnCount() == 0 || tbReservas.getSelectedRowCount() == 0;
	}

	private void modificar() {

		ReservaController reservaController = new ReservaController();
		HuespedController huespedController = new HuespedController();	

		if(noTieneFilaElegidaReservas() && noTieneFilaElegidaHuespedes()) {
			  JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
		}else if (!noTieneFilaElegidaReservas() && noTieneFilaElegidaHuespedes() ) {
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						if(tbReservas.getSelectedColumn()!= 0) {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						Date checkin = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
						Date checkout = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
						String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
						String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
						reservaController.modificar(id,checkin, checkout, valor, formaPago);

						Exito.main(null);
						}else {
							JOptionPane.showMessageDialog(this, "No se puede modificar el id de Reservas");
						}
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	        }else if (!noTieneFilaElegidaHuespedes() && noTieneFilaElegidaReservas()) {
		    	Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					if(tbHuespedes.getSelectedColumn()!= 6) {
					Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					String nombre = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1);
					String apellido = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2);
					Date fechaNacimiento = Date
							.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
					String nacion = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4);
					String telefono = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5);
					int idReserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
					huespedController.modificarHuesped(id, nombre, apellido, fechaNacimiento, nacion, telefono,
							idReserva);

					Exito.main(null);
					}else {
						JOptionPane.showMessageDialog(this, "No se puede modificar el id_reserva de Huespedes");
					}
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	        }
	}

	private void eliminar() {

		ReservaController reservaController = new ReservaController();
		HuespedController huespedController = new HuespedController();

		if (noTieneFilaElegidaHuespedes() && noTieneFilaElegidaReservas()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		if (!noTieneFilaElegidaReservas() && noTieneFilaElegidaHuespedes()) {
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						if(reservaController.eliminar(id) >0) {
							Exito.main(null);
						}
						
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
		if (!noTieneFilaElegidaHuespedes() && noTieneFilaElegidaReservas()) {
			Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						huespedController.eliminarHuesped(id);
						
						Exito.main(null);
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
