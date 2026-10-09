package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/libros/modificar")
public class LibroPutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/xml;charset=UTF-8");

		String idParam = request.getParameter("id");

		if (idParam == null) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			response.getWriter().print(XmlUtil.error("Debe indicar el parámetro id."));

			return;
		}

		try {

			int id = Integer.parseInt(idParam);

			Libro libro = ControladorLibro.buscarPorId(id);

			if (libro == null) {

				response.setStatus(HttpServletResponse.SC_NOT_FOUND);

				response.getWriter().print(XmlUtil.error("Libro no encontrado."));

				return;
			}

			String titulo = request.getParameter("titulo");

			String anioPublicacionParam = request.getParameter("anioPublicacion");

			/*
			 * Solo modificamos los datos que realmente hayan sido enviados.
			 */
			if (titulo != null) {

				libro.setTitulo(titulo);
			}

			if (anioPublicacionParam != null) {

				int anioPublicacion = Integer.parseInt(anioPublicacionParam);

				libro.setAnioPublicacion(anioPublicacion);
			}

			String xml = String.format(
				    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				    "<respuesta>\n" +
				    "    <operacion>modificar</operacion>\n" +
				    "    <mensaje>Libro modificado correctamente.</mensaje>\n" +
				    "    %s\n" +
				    "</respuesta>",
				    XmlUtil.libro(libro)
				);

			response.setStatus(HttpServletResponse.SC_OK);

			response.getWriter().print(xml);

		} catch (NumberFormatException e) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			response.getWriter().print(XmlUtil.error("El id y el año deben ser números enteros."));
		}
	}
}