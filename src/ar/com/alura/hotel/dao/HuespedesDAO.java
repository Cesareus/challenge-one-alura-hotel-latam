package ar.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.alura.hotel.factory.ConnectionFactory;
import ar.com.alura.hotel.modelo.Huesped;
import ar.com.alura.hotel.views.Exito;


public class HuespedesDAO {

    private Connection con;

    public HuespedesDAO(Connection con) {
        this.con = con;
    }
    
    public void guardar(Huesped huesped, int reserva) {

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES (nombre, apellido, f_nacimiento, nacion, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(huesped, reserva, statement);
				Exito.main(null);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void ejecutaRegistro(Huesped huesped, int reserva, PreparedStatement statement) throws SQLException {

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getF_nacimiento());
		statement.setString(4, huesped.getNacion());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, (reserva));

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {

			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println(String.format("Fue guardado el huesped en la reserva %s", reserva));

			}
		}
	}

	public List<Huesped> listarHuespedes() {
		List<Huesped> resultado = new ArrayList<>();
		try {
			var querySelect = "SELECT ID, NOMBRE, APELLIDO, F_NACIMIENTO, NACION, TELEFONO, ID_RESERVA FROM HUESPEDES";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("f_nacimiento"),
								resultSet.getString("nacion"), resultSet.getString("telefono"),
								resultSet.getInt("id_reserva"));
						resultado.add(huesped);
					}
				}
				;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}
		
	public List<Huesped> listarHuespedes(String busqueda) {
		List<Huesped> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {

			var querySelect = "select ID, NOMBRE, APELLIDO, F_NACIMIENTO, NACION, TELEFONO, ID_RESERVA from HUESPEDES WHERE nombre = ? or apellido = ? or nacion = ?";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				statement.setString(1, busqueda.toLowerCase());
				statement.setString(2, busqueda.toLowerCase());
				statement.setString(3, busqueda.toLowerCase());

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("F_NACIMIENTO"),
								resultSet.getString("NACION"), resultSet.getString("TELEFONO"),
								resultSet.getInt("ID_RESERVA"));

						resultado.add(fila);

					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


	public int eliminarHuesped(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificarHuesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacion, String telefono,
			int id_reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE HUESPEDES SET " + " NOMBRE = ?, " + " APELLIDO = ?," + " F_NACIMIENTO = ?,"
							+ " NACION = ?," + " TELEFONO = ?," + " ID_RESERVA = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fechaNacimiento);
				statement.setString(4, nacion);
				statement.setString(5, telefono);
				statement.setInt(6, id_reserva);
				statement.setInt(7, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
