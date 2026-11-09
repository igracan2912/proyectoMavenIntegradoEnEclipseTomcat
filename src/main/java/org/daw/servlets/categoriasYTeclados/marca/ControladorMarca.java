package org.daw.servlets.categoriasYTeclados.marca;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.daw.servlets.Utils;
import org.daw.servlets.categoriasYTeclados.teclado.Teclado;

public class ControladorMarca {
	
	private static final String URL = Utils.obtenerVariableEntorno("DB_URL");
	private static final String USUARIO = Utils.obtenerVariableEntorno("DB_USER");
	private static final String PASSWORD = Utils.obtenerVariableEntorno("DB_PASSWORD");

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}
	}
	
	public static List<Marca> buscarMarcas(int pagina, int tamanoPagina) throws SQLException {
		
		List<Marca> marcas = new ArrayList<>();

		String sql = "SELECT * FROM marca ORDER BY id LIMIT ? OFFSET ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {
			// Paginación.
			ps.setInt(1, tamanoPagina);
			ps.setInt(2, pagina * tamanoPagina);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Marca m = new Marca();

					m.setId(rs.getInt("id"));
					m.setNombre(rs.getString("nombre"));
					m.setFechaConstitucion(rs.getDate("fechaConstitucion").toLocalDate());

					marcas.add(m);
				}
			}
		}

		return marcas;
	}
	
	public static Marca getMarca(int id) throws SQLException {

		String sql = "SELECT * FROM marca WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Marca m = new Marca();

					m.setId(rs.getInt("id"));
					m.setNombre(rs.getString("nombre"));
					m.setFechaConstitucion(rs.getDate("fechaConstitucion").toLocalDate());

					return m;
				}
			}
		} 

		return null;
	}
	
	private static int getMaxId() throws SQLException {
		String sql = "SELECT id FROM marca ORDER BY id desc LIMIT 1";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("id");
				}
			}
		}

		return 0;
	}

	
	public static Marca createMarca (String nombre, LocalDate fechaConstitucion) throws SQLException {
		Marca m = new Marca(getMaxId() + 1, nombre, fechaConstitucion);
		
		String sql = "INSERT INTO marca VALUES(?,?,?)";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, m.getId());
			ps.setString(2, m.getNombre());
			ps.setDate(3, Date.valueOf(m.getFechaConstitucion()));
			
			if (ps.executeUpdate() == 0) {
				return null;
			}
		}
		
		return m;
	}
	
	public static void updateMarca (Marca m) throws SQLException {
		
		String sql = "UPDATE marca SET nombre = ?, fechaConstitucion = ? WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, m.getNombre());
			ps.setDate(2, Date.valueOf(m.getFechaConstitucion()));
			ps.setInt(3, m.getId());
			
			ps.executeUpdate();
		}
	}
	
	public static void deleteMarca (int id) throws SQLException {
		
		String sql = "DELETE FROM marca WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);
			
			ps.executeUpdate();
		}
	}
}
