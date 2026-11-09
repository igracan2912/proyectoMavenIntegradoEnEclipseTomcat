package org.daw.servlets.categoriasYTeclados.marca;

import java.io.IOException;
import java.sql.SQLException;

import org.daw.servlets.Error;
import org.daw.servlets.JSONUtils;
import org.daw.servlets.Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/marcas/eliminar")
public class ServletDeleteMarcas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		 // Comprobamos si existe una sesión para el usuario.
		if (!Utils.comprobarSesion(request, response)) return;
		
		
		
		Integer id = Utils.getIntParameterWithCheck("id", request, response);
		if (id == null) return;
		
		Marca m = null;
		try {
			m = ControladorMarca.getMarca(id);
		} catch (SQLException e) {
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
			e.printStackTrace();
		}
		
		if (m == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor de 'id' no corresponde con alguna marca existente")));
			return;
		}
		
		try {
			ControladorMarca.deleteMarca(m.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(m));
	}
}