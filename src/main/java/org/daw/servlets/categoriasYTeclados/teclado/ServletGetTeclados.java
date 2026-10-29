package org.daw.servlets.categoriasYTeclados.teclado;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import org.daw.servlets.categoriasYTeclados.Error;
import org.daw.servlets.categoriasYTeclados.JSONUtils;
import org.daw.servlets.categoriasYTeclados.Utils;

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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		StringBuffer sb = new StringBuffer();
		
		String strId = request.getParameter("id");
		
		if (strId == null) {
			Integer pagina = Utils.getOptionalIntParameter("pagina", request, response);
			Integer tamanoPagina = Utils.getOptionalIntParameter("tamanoPagina", request, response);
			
			if (pagina != null && tamanoPagina != null) {
				try {
					List <Teclado> teclados = ControladorTeclado.buscarTeclados(pagina, tamanoPagina);
					response.getWriter().print(JSONUtils.toJSON(teclados));
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
				}
			}
			if (pagina != null && tamanoPagina == null) {
				try {
					List <Teclado> teclados = ControladorTeclado.buscarTeclados(pagina, 10);
					response.getWriter().print(JSONUtils.toJSON(teclados));
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
				}
			}
			else {
				try {
					List <Teclado> teclados = ControladorTeclado.buscarTeclados(0, 10);
					response.getWriter().print(JSONUtils.toJSON(teclados));
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
				}
			}
			
		}
		
		
		else {
			Integer idTeclado = Utils.getIntParameterWithCheck("id", request, response);
			
			if (idTeclado == null) return;
			Teclado t = null;
			try {
				t = ControladorTeclado.getTeclado(idTeclado);
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
			}
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









