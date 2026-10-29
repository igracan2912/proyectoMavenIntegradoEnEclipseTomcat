package org.daw.servlets.categoriasYTeclados.categoria;

import java.io.IOException;
import java.sql.SQLException;

import org.daw.servlets.categoriasYTeclados.Error;
import org.daw.servlets.categoriasYTeclados.JSONUtils;
import org.daw.servlets.categoriasYTeclados.Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPutCategorias
 */
@WebServlet("/categorias/eliminar")
public class ServletDeleteCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		// Comprobar que recibo los datos necesarios
		Integer id = Utils.getIntParameterWithCheck("id", request, response);
		if (id == null) return;
		
		Categoria c = null;
		try {
			c = ControladorCategoria.getCategoria(id);
		} catch (SQLException e) {
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en SQL")));
			e.printStackTrace();
			return;
		}
		
		if (c == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor de 'id' no corresponde con alguna categoría existente")));
			return;
		}
		
		// Si llego hasta aquí significa que puedo sobrescribir los datos de la categoría
		try {
			ControladorCategoria.deleteCategoria(c.getId());
		} catch (SQLException e) {
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en SQL")));
			e.printStackTrace();
			return;
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(c));
	}

}







