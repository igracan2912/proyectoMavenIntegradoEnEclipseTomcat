package org.daw.servlets.teclados;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/teclados")
public class ServletGetTeclados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetTeclados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		
		String strId = request.getParameter("id");
		
		if (strId == null) {
			response.getWriter().print(JSONUtils.toJSON(
					ControladorTeclado.getLista()));
		}
		else {
			Integer idTeclado = Utils.getIntParameterWithCheck("id", request, response);
			
			if (idTeclado == null) return;
			
			Teclado t = ControladorTeclado.getTeclado(idTeclado);
			if (t != null) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(JSONUtils.toJSON(t));
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				sb.append(JSONUtils.toJSON(new Error(
						"Teclado con id " + strId + " no encontrado")));
			}
			
			
			response.getWriter().append(sb.toString());
		}
		
		
	}

	
	
}









