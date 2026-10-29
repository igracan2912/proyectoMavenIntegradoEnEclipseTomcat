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

@WebServlet("/categorias/crear")
public class ServletPostCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String strDescripcion = Utils.getStringParameterWithCheck("descripcion", request, response);
		Categoria nuevo = null;
		try {
			nuevo = ControladorCategoria.createCategoria(strDescripcion);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(nuevo));		
	}
}