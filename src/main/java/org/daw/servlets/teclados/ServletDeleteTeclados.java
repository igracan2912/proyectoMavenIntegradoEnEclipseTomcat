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

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strId = request.getParameter("id");
		if (strId == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("Se necesita un parámetro 'id'")));
			return;
		}
				
		int id = -1;
		try {
			id = Integer.parseInt(strId);
		}
		catch (NumberFormatException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parámetro 'id' debe tener un valor entero")));
			return;
		}
		
		Teclado t = ControladorTeclado.getTeclado(id);
		if (t == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor de 'id' no corresponde con algun teclado existente")));
			return;
		}
		
		ControladorTeclado.getLista().remove(t);
		response.setStatus(HttpServletResponse.SC_OK);
	}

}







