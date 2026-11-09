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

@WebServlet("/teclados/modificar")
public class ServletPutMarcas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		 // Comprobamos si existe una sesión para el usuario.
		if (!Utils.comprobarSesion(request, response)) return;
		
		
		
		Integer id = Utils.getIntParameterWithCheck("id", request, response);
		if (id == null) return;
		
		String nombre = Utils.getStringParameterWithCheck("nombre", request, response);
		if (nombre == null) return;
		
		LocalDate fechaConstitucion = Utils.getDateParameterWithCheck("fechaConstitucion", request, response);
		if (fechaConstitucion == null) return;
		
		Marca m = null;
		try {
			m = ControladorMarca.getMarca(id);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
		}
		
		if (m == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor de 'id' no corresponde con alguna marca existente")));
			return;
		}
		
		m.setNombre(nombre);
		m.setFechaConstitucion(fechaConstitucion);
		
		try {
			ControladorMarca.updateMarca(m);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(m));
	}
}