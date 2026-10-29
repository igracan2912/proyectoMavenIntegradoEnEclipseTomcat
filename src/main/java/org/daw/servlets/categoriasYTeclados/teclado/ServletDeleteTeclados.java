package org.daw.servlets.categoriasYTeclados.teclado;

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

@WebServlet("/teclados/eliminar")
public class ServletDeleteTeclados extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Integer id = Utils.getIntParameterWithCheck("id", request, response);
		if (id == null) return;
		
		Teclado t = null;
		try {
			t = ControladorTeclado.getTeclado(id);
		} catch (SQLException e) {
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
			e.printStackTrace();
		}
		
		if (t == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor de 'id' no corresponde con algún teclado existente")));
			return;
		}
		
		try {
			ControladorTeclado.deleteTeclado(t.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(t));
	}
}