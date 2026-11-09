package org.daw.servlets.categoriasYTeclados.marca;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.daw.servlets.Error;
import org.daw.servlets.JSONUtils;
import org.daw.servlets.Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/marcas/crear")
public class ServletPostMarcas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		 // Comprobamos si existe una sesión para el usuario.
		if (!Utils.comprobarSesion(request, response)) return;
		
		String nombre = Utils.getStringParameterWithCheck("nombre", request, response);
		if (nombre == null) return;
		
		LocalDate fechaConstitucion = Utils.getDateParameterWithCheck("fechaConstitucion", request, response);
		if (fechaConstitucion == null) return;
		
		Marca m = null;
		try {
			m = ControladorMarca.createMarca(nombre, fechaConstitucion);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(m));		
	}
}