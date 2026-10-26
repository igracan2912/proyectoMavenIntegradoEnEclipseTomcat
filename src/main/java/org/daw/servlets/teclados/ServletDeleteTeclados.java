package org.daw.servlets.teclados;

import java.io.IOException;
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
		
		Teclado t = ControladorTeclado.getTeclado(id);
		if (t == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor de 'id' no corresponde con algún teclado existente")));
			return;
		}
		
		ControladorTeclado.getLista().remove(t);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(t));
	}
}