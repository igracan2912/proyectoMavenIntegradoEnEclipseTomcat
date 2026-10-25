package org.daw.servlets.teclados;

import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPostCategorias
 */
@WebServlet("/categorias/crear")
public class ServletPostCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
        BufferedReader lector = request.getReader();
        StringBuffer sb = new StringBuffer();
        String linea;
        while ((linea = lector.readLine()) != null) {
        	sb.append(linea);
        }
        ObjectMapper mapper = new ObjectMapper();
        PostCategoriasRequestBody requestBody = mapper.readValue(sb.toString(), PostCategoriasRequestBody.class);
		
		
		
//		String strDescripcion = request.getParameter("descripcion");
//		
//		// Si no me dan una descripción, remito error 400 Bad Request
//		if (strDescripcion == null) {
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			response.getWriter().print(JSONUtils.toJSON(new Error("No ha especificado el parámetro"
//					+ " 'descripcion'")));
//			return;
//		}
		
		// Si llego hasta aquí debo crear una nueva categoría
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(
				ControladorCategoria.createCategoria(requestBody.getDescripcion())));		
	}

}









