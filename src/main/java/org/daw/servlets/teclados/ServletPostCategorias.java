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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		
        BufferedReader lector = request.getReader();
        StringBuffer sb = new StringBuffer();
        String linea;
        while ((linea = lector.readLine()) != null) {
        	sb.append(linea);
        }
        ObjectMapper mapper = new ObjectMapper();
        PostCategoriasRequestBody requestBody = mapper.readValue(sb.toString(), PostCategoriasRequestBody.class);
		
		
		// Si llego hasta aquí debo crear una nueva categoría
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(
				ControladorCategoria.createCategoria(requestBody.getDescripcion())));		
	}

}









