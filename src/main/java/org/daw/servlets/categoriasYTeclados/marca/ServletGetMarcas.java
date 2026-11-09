package org.daw.servlets.categoriasYTeclados.marca;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import org.daw.servlets.Error;
import org.daw.servlets.JSONUtils;
import org.daw.servlets.Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/marcas")
public class ServletGetMarcas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetMarcas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		 // Comprobamos si existe una sesión para el usuario.
		if (!Utils.comprobarSesion(request, response)) return;
		
		
		StringBuffer sb = new StringBuffer();
		
		String strId = request.getParameter("id");
		
		if (strId == null) {
			Integer pagina = Utils.getOptionalIntParameter("pagina", request, response);
			Integer tamanoPagina = Utils.getOptionalIntParameter("tamanoPagina", request, response);
			
			if (pagina != null && tamanoPagina != null) {
				try {
					List <Marca> marcas = ControladorMarca.buscarMarcas(pagina, tamanoPagina);
					response.getWriter().print(JSONUtils.toJSON(marcas));
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
				}
			}
			if (pagina != null && tamanoPagina == null) {
				try {
					List <Marca> marcas =  ControladorMarca.buscarMarcas(pagina, 10);
					response.getWriter().print(JSONUtils.toJSON(marcas));
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
				}
			}
			else {
				try {
					List <Marca> marcas = ControladorMarca.buscarMarcas(0, 10);
					response.getWriter().print(JSONUtils.toJSON(marcas));
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
				}
			}
			
		}
		
		
		else {
			Integer idMarca = Utils.getIntParameterWithCheck("id", request, response);
			
			if (idMarca == null) return;
			Marca m = null;
			try {
				m = ControladorMarca.getMarca(idMarca);
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(JSONUtils.toJSON(new Error("Error en BD")));
			}
			if (m != null) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(JSONUtils.toJSON(m));
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				sb.append(JSONUtils.toJSON(new Error(
						"Marca con id " + strId + " no encontrado")));
			}
			
			
			response.getWriter().append(sb.toString());
		}
		
		
	}

	
	
}









