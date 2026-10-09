package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/libros/crear")
public class LibroPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/xml;charset=UTF-8");

		String titulo = request.getParameter("titulo");

		String anioPublicacionParam = request.getParameter("anioPublicacion");

		if (titulo == null || anioPublicacionParam == null) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			response.getWriter().print(XmlUtil.error("Faltan los parámetros titulo y año."));

			return;
		}

		try {

			int anioPublicacion = Integer.parseInt(anioPublicacionParam);

			Libro libro = ControladorLibro.crear(titulo, anioPublicacion);

			String xml = String.format(
				    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				    "<respuesta>\n" +
				    "    <operacion>crear</operacion>\n" +
				    "    <mensaje>Libro creado correctamente.</mensaje>\n" +
				    "    %s\n" +
				    "</respuesta>",
				    XmlUtil.libro(libro)
				);

			response.setStatus(HttpServletResponse.SC_CREATED);

			response.getWriter().print(xml);

		} catch (NumberFormatException e) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			response.getWriter().print(XmlUtil.error("El año debe ser un número entero."));
		}
	}
}