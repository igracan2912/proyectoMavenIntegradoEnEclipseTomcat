
package org.daw.servlets.categoriasYTeclados.categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.daw.servlets.categoriasYTeclados.Utils;


public class ControladorCategoria {

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
	
	/**
	 * Busca categorías por descripción y pagina los resultados.
	 *
	 * El filtro por nombre es opcional y realiza una búsqueda parcial, ignorando
	 * mayúsculas y minúsculas.
	 *
	 * La primera página es la página 0.
	 *
	 * @param nombre       texto que debe contener la descripción
	 * @param pagina       número de página, empezando por 0
	 * @param tamanoPagina número máximo de resultados por página
	 * @return lista de categorías encontradas
	 * @throws SQLException si se produce un error en la consulta
	 */
	public static List<Categoria> buscarCategorias(String nombre, int pagina, int tamanoPagina) throws SQLException {
		
		List<Categoria> categorias = new ArrayList<>();

		String sql = "SELECT id, descripcion FROM categoria WHERE LOWER(descripcion) LIKE LOWER(?) ORDER BY id LIMIT ? OFFSET ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			// Filtro contains ignorecase.
			ps.setString(1, "%" + (nombre == null ? "" : nombre.trim()) + "%");

			// Paginación.
			ps.setInt(2, tamanoPagina);
			ps.setInt(3, pagina * tamanoPagina);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Categoria categoria = new Categoria();

					categoria.setId(rs.getInt("id"));
					categoria.setDescripcion(rs.getString("descripcion"));

					categorias.add(categoria);
				}
			}
		}

		return categorias;
	}

	/**
	 * Busca una categoría por su identificador.
	 *
	 * @param id identificador de la categoría
	 * @return la categoría encontrada o null si no existe
	 * @throws SQLException si se produce un error en la consulta
	 */
	public static Categoria getCategoria(int id) throws SQLException {

		String sql = "SELECT id, descripcion FROM categoria WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Categoria categoria = new Categoria();

					categoria.setId(rs.getInt("id"));
					categoria.setDescripcion(rs.getString("descripcion"));

					return categoria;
				}
			}
		} 

		return null;
	}
	
	private static int getMaxId() throws SQLException {
		String sql = "SELECT id FROM categoria ORDER BY id desc LIMIT 1";

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

	
	public static Categoria createCategoria (String descripcion) throws SQLException {
		Categoria c = new Categoria(getMaxId() + 1, descripcion);
		
		String sql = "INSERT INTO categoria VALUES(?,?)";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, c.getId());
			ps.setString(2, c.getDescripcion());
			
			if (ps.executeUpdate() == 0) {
				return null;
			}
		}
		
		return c;
	}
	
	public static void updateCategoria (Categoria c) throws SQLException {
		
		String sql = "UPDATE categoria SET descripcion = ? WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, c.getDescripcion());
			ps.setInt(2, c.getId());
			
			ps.executeUpdate();
		}
	}
	
	public static void deleteCategoria (int id) throws SQLException {
		
		String sql = "DELETE FROM categoria WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);
			
			ps.executeUpdate();
		}
	}
}
