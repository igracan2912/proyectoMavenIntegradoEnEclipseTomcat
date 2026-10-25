package org.daw.servlets.teclados;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletGetCategorias
 */
@WebServlet("/categorias")
public class ServletGetCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetCategorias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		
		// Si recibo un parámetro de nombre "id" sólo debo devolver una única categoría, si existe
		
		// Compruebo si recibo un parámetro llamado "id"
		String strId = request.getParameter("id");
		
		// Si no recibo "id" devuelvo un arrary JSON con todas las categorías
		if (strId == null) {
			response.getWriter().print(JSONUtils.toJSON(
					ControladorCategoria.getLista()));
		}
		// Si recibo un "id" sólo devuelvo la categoría correspondiente
		else {
			Integer idCategoria = Utils.getIntParameterWithCheck("id", request, response);
			
			if (idCategoria == null) return;
			
			// si acabo el for sin encontrar el id que me han pedido
			Categoria c = ControladorCategoria.getCategoria(idCategoria);
			if (c != null) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(JSONUtils.toJSON(c));
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				sb.append(JSONUtils.toJSON(new Error(
						"Categoría con id " + strId + " no encontrado")));
			}
			
			
			response.getWriter().append(sb.toString());
		}
		
		
	}

	
	
}









