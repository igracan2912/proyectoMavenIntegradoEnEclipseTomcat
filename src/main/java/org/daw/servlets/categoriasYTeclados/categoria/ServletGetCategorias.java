
package org.daw.servlets.categoriasYTeclados.categoria;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.daw.servlets.categoriasYTeclados.Error;
import org.daw.servlets.categoriasYTeclados.JSONUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/categorias")
public class ServletGetCategorias extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int TAMANO_PAGINA_POR_DEFECTO = 10;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Si se recibe un parámetro id, se consulta una categoría.
		String strId = request.getParameter("id");

		if (strId != null) {
			obtenerCategoriaPorId(strId, response);
			return;
		}

		// Obtener los parámetros de búsqueda y paginación.
		String nombre = request.getParameter("nombre");

		Integer pagina = obtenerParametroEntero(request, response, "pagina", 0);

		if (pagina == null) {
			return;
		}

		Integer tamanoPagina = obtenerParametroEntero(request, response, "tamanoPagina", TAMANO_PAGINA_POR_DEFECTO);

		if (tamanoPagina == null) {
			return;
		}

		// Validar los valores de paginación.
		if (pagina < 0 || tamanoPagina <= 0) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST,
					"La página debe ser mayor o igual que 0 " + "y tamanoPagina debe ser mayor que 0.");
			return;
		}

		// Buscar categorías en la base de datos.
		try {
			List<Categoria> categorias = ControladorCategoria.buscarCategorias(nombre, pagina, tamanoPagina);

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(categorias));

		} catch (SQLException e) {
			getServletContext().log("Error al consultar las categorías.", e);

			enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar las categorías.");
		}
	}

	/**
	 * Obtiene un parámetro entero de la petición HTTP. Devuelve el valor por
	 * defecto si el parámetro no existe. Devuelve null y responde con HTTP 400 si
	 * no es un entero válido.
	 */
	private Integer obtenerParametroEntero(HttpServletRequest request, HttpServletResponse response,
			String nombreParametro, int valorPorDefecto) throws IOException {

		String valor = request.getParameter(nombreParametro);

		if (valor == null) {
			return valorPorDefecto;
		}

		try {
			return Integer.valueOf(valor.trim());

		} catch (NumberFormatException e) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST,
					"El parámetro '" + nombreParametro + "' debe ser un número entero.");

			return null;
		}
	}

	/**
	 * Obtiene una categoría a partir de su identificador.
	 */
	private void obtenerCategoriaPorId(String strId, HttpServletResponse response) throws IOException {

		final int idCategoria;

		try {
			idCategoria = Integer.parseInt(strId.trim());

		} catch (NumberFormatException e) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'id' debe ser un número entero.");
			return; 
		}

		try {
			Categoria categoria = ControladorCategoria.getCategoria(idCategoria);

			if (categoria == null) {
				enviarError(response, HttpServletResponse.SC_NOT_FOUND,
						"Categoría con id " + idCategoria + " no encontrada.");
				return;
			}

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(categoria));

		} catch (SQLException e) {
			getServletContext().log("Error al consultar la categoría " + idCategoria, e);

			enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar la categoría.");
		}
	}

	/**
	 * Envía una respuesta de error en formato JSON.
	 */
	private void enviarError(HttpServletResponse response, int codigoEstado, String mensaje) throws IOException {

		response.setStatus(codigoEstado);
		response.getWriter().print(JSONUtils.toJSON(new Error(mensaje)));
	}
}
