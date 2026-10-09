package org.daw.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de las operaciones GET.
 *
 * Ejemplos:
 *
 * GET /personas
 * GET /personas?id=2
 */
@WebServlet("/libros")
public class LibroGetServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Procesa las peticiones HTTP GET.
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// La respuesta del Servlet será XML.
		response.setContentType("application/xml;charset=UTF-8");

		String idParam = request.getParameter("id");

		/*
		 * GET /ej01_personas
		 *
		 * No se ha indicado ID. Devolvemos todas las personas.
		 */
		if (idParam == null) {

			List<Libro> libros = ControladorLibro.listar();

			StringBuilder xml = new StringBuilder();

			xml.append("""
					<?xml version="1.0" encoding="UTF-8"?>
					<libros>
					""");

			for (Libro libro : libros) {

				xml.append(XmlUtil.libro(libro));
			}

			xml.append("</libros>");

			response.setStatus(HttpServletResponse.SC_OK);

			response.getWriter().print(xml);

			return;
		}

		/*
		 * GET /ej01_personas?id=2
		 *
		 * Se ha indicado un ID.
		 */
		try {

			int id = Integer.parseInt(idParam);

			Libro libro = ControladorLibro.buscarPorId(id);

			if (libro == null) {

				response.setStatus(HttpServletResponse.SC_NOT_FOUND);

				response.getWriter().print(XmlUtil.error("Libro no encontrado."));

				return;
			}

			String xml = String.format(
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n%s\n",
					XmlUtil.libro(libro));
			
			

			response.setStatus(HttpServletResponse.SC_OK);

			response.getWriter().print(xml);

		} catch (NumberFormatException e) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			response.getWriter().print(XmlUtil.error("El parámetro id debe ser un número entero."));
		}
	}
}