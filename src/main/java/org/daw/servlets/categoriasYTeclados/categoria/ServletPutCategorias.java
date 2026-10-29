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
@WebServlet("/categorias/modificar")
public class ServletPutCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		// Comprobar que recibo los datos necesarios
		int id = Utils.getIntParameterWithCheck("id", request, response);
		
		String descripcion = Utils.getStringParameterWithCheck("descripcion", request, response);
		
		Categoria c = null;
		try {
			c = ControladorCategoria.getCategoria(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (c == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor de 'id' no corresponde con alguna categoría existente")));
			return;
		}
		
		// Si llego hasta aquí significa que puedo sobrescribir los datos de la categoría
		c.setDescripcion(descripcion);
		try {
			ControladorCategoria.updateCategoria(c);
		} catch (SQLException e) {
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en SQL")));
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(c));
	}

}







