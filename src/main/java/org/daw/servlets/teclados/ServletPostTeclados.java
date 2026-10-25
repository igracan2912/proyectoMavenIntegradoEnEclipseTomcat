package org.daw.servlets.teclados;

import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/teclados/crear")
public class ServletPostTeclados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		String strColor = request.getParameter("color");
		
		if (strColor == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("No ha especificado el parámetro"
					+ " 'color'")));
			return;
		}
		
		String strIdioma = request.getParameter("idioma");
		
		if (strIdioma == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("No ha especificado el parámetro"
					+ " 'idioma'")));
			return;
		}
		
		String strIdCategoria = request.getParameter("idCategoria");
		
		if (strIdCategoria == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("No ha especificado el parámetro"
					+ " 'idCategoria'")));
			return;
		}
		
		
		int idCategoria = -1;
		try {
			idCategoria = Integer.parseInt(strIdCategoria);
		}
		catch (NumberFormatException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parámetro 'idCategoria' debe tener un valor entero")));
			return;
		}
		
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(
				ControladorTeclado.createTeclado(strColor, strIdioma, idCategoria)));		
	}

}









