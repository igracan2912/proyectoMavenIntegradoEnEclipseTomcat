
package org.daw.servlets.categoriasYTeclados.teclado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.daw.servlets.categoriasYTeclados.Utils;


public class ControladorTeclado {

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
	
	
	public static List<Teclado> buscarTeclados(int pagina, int tamanoPagina) throws SQLException {
		
		List<Teclado> teclados = new ArrayList<>();

		String sql = "SELECT id,color,idioma,idCategoria FROM teclado ORDER BY id LIMIT ? OFFSET ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {
			// Paginación.
			ps.setInt(1, tamanoPagina);
			ps.setInt(2, pagina * tamanoPagina);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Teclado teclado = new Teclado();

					teclado.setId(rs.getInt("id"));
					teclado.setColor(rs.getString("color"));
					teclado.setIdioma(rs.getString("idioma"));
					teclado.setIdCategoria(rs.getInt("idCategoria"));

					teclados.add(teclado);
				}
			}
		}

		return teclados;
	}

	
	public static Teclado getTeclado(int id) throws SQLException {

		String sql = "SELECT * FROM teclado WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Teclado teclado = new Teclado();

					teclado.setId(rs.getInt("id"));
					teclado.setColor(rs.getString("color"));
					teclado.setIdioma(rs.getString("idioma"));
					teclado.setIdCategoria(rs.getInt("idCategoria"));

					return teclado;
				}
			}
		} 

		return null;
	}
	
	private static int getMaxId() throws SQLException {
		String sql = "SELECT id FROM teclado ORDER BY id desc LIMIT 1";

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

	
	public static Teclado createTeclado (String color, String idioma, int idCategoria) throws SQLException {
		Teclado t = new Teclado(getMaxId() + 1, color, idioma, idCategoria);
		
		String sql = "INSERT INTO teclado VALUES(?,?,?,?)";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, t.getId());
			ps.setString(2, t.getColor());
			ps.setString(3, t.getIdioma());
			ps.setInt(4, t.getIdCategoria());
			
			if (ps.executeUpdate() == 0) {
				return null;
			}
		}
		
		return t;
	}
	
	public static void updateTeclado (Teclado t) throws SQLException {
		
		String sql = "UPDATE teclado SET color = ?, idioma = ?, idCategoria = ? WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, t.getColor());
			ps.setString(2, t.getIdioma());
			ps.setInt(3, t.getIdCategoria());
			ps.setInt(4, t.getId());
			
			ps.executeUpdate();
		}
	}
	
	public static void deleteTeclado (int id) throws SQLException {
		
		String sql = "DELETE FROM teclado WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);
			
			ps.executeUpdate();
		}
	}
}
