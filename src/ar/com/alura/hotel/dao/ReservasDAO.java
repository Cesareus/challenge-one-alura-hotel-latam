package ar.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import ar.com.alura.hotel.factory.ConnectionFactory;
import ar.com.alura.hotel.modelo.Reservas;


public class ReservasDAO {

    private Connection con;

    public ReservasDAO(Connection con) {
        this.con = con;
    }
	public void guardar(Reservas reserva) {

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS (f_entrada, f_salida, Valor, f_pago) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(reserva, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void ejecutaRegistro(Reservas reserva, PreparedStatement statement) throws SQLException {

		statement.setDate(1, reserva.getCheckin()); 
		statement.setDate(2, reserva.getCheckout());
		statement.setString(3, reserva.getValorReserva());
		statement.setString(4, reserva.getFormaPago());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {

			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(String.format("Fue guardada la reserva %s", reserva));

			}
		}
	}

	public List<Reservas> listarReservas() {

		List<Reservas> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {

			final PreparedStatement statement = con
					.prepareStatement("select id, f_entrada, f_salida, Valor, f_Pago from reservas");

			try (statement) {

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Reservas fila = new Reservas(resultSet.getInt("Id"), resultSet.getDate("f_entrada"),
								resultSet.getDate("f_salida"), resultSet.getString("Valor"),
								resultSet.getString("f_Pago"));

						resultado.add(fila);

					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO PUEDE ELIMINAR UNA RESERVA QUE TENGA ASOCIADOS HUÉSPEDES");
			return 0;
		}
	}

	public int modificar( Integer id,Date fecha_entrada, Date fecha_salida, String valor, String forma_Pago) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " + " F_ENTRADA = ?, "
					+ " F_SALIDA = ?," + " VALOR = ?," + " F_PAGO = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setDate(1, (java.sql.Date) fecha_entrada);
				statement.setDate(2, (java.sql.Date) fecha_salida);
				statement.setString(3, valor);
				statement.setString(4, forma_Pago);
				statement.setInt(5, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
    /*public List<Reservas> listarConHuespedes() {
        List<Reservas> resultado = new ArrayList<>();

        try {
            String sql = "SELECT R.ID, R.F_ENTRADA, R.F_SALIDA, R.VALOR, R.F_PAGO,"
            		+ " H.ID, H.NOMBRE, H.APELLIDO, H.F_NACIMIENTO, H.NACION, H.TELEFONO, H.ID_RESERVA "
                    + " FROM RESERVAS R INNER JOIN HUESPEDES H "
                    + " ON R.ID = P.RESERVA_ID";
            
            System.out.println(sql);
            
            final PreparedStatement statement = con
                    .prepareStatement(sql);

            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        int reservasId = resultSet.getInt("R.ID");
                        Date reservasF_entrada = resultSet.getDate("R.F_ENTRADA");
                        Date reservasF_salida = resultSet.getDate("F_SALIDA");
                        String reservasvalor =  resultSet.getString("VALOR");
                        String reservasF_pago = resultSet.getString("F_PAGO");
                        
                        Reservas reservas = resultado
                            .stream()
                            .filter(cat -> cat.getId().equals(reservasId))
                            .findAny().orElseGet(() -> {
                            	Reservas res = new Reservas(
                            			reservasId,
                            			reservasF_entrada,
                            			reservasF_salida,
                            			reservasvalor,
                            			reservasF_pago);
                                resultado.add(res);
                                
                                return res;
                            });
                        
                        Huesped huesped = new Huesped(
                                resultSet.getInt("H.ID"),
                                resultSet.getString("H.NOMBRE"),
                                resultSet.getString("H.APELLIDO"),
                                resultSet.getDate("H.F_NACIMIENTO"),
                                resultSet.getString("H.NACION"),
                                resultSet.getString("H.TELEFONO"),
                                resultSet.getInt("H.ID_RESERVA")                        		
                        		);
                        
                        reservas.agregar(huesped);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
*/
	public List<Reservas> listarReservas(String busqueda) {
		List<Reservas> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {

			var querySelect = "select ID, F_ENTRADA, F_SALIDA, VALOR, F_PAGO from RESERVAS WHERE Id = ?";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				statement.setString(1, busqueda);

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Reservas fila = new Reservas(resultSet.getInt("ID"), resultSet.getDate("F_ENTRADA"),
								resultSet.getDate("F_SALIDA"), resultSet.getString("VALOR"),
								resultSet.getString("F_PAGO"));

						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
  
}
