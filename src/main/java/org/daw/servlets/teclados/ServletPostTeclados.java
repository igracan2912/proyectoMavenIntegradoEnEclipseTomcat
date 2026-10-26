package org.daw.servlets.teclados;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/teclados/crear")
public class ServletPostTeclados extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String strColor = request.getParameter("color");
		if (strColor == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("No ha especificado el parámetro 'color'")));
			return;
		}
		
		String strIdioma = request.getParameter("idioma");
		if (strIdioma == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("No ha especificado el parámetro 'idioma'")));
			return;
		}
		
		Integer idCategoria = Utils.getIntParameterWithCheck("idCategoria", request, response);
		if (idCategoria == null) return;
		
		Teclado nuevo = ControladorTeclado.createTeclado(strColor, strIdioma, idCategoria);
		response.setStatus(HttpServletResponse.SC_OK); // O SC_CREATED (201)
		response.getWriter().print(JSONUtils.toJSON(nuevo));		
	}
}