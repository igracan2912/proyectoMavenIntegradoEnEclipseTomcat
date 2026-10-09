package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de eliminar personas.
 *
 * Ejemplo:
 *
 * DELETE /personas?id=2
 */
@WebServlet("/libros/eliminar")
public class LibroDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Procesa las peticiones HTTP DELETE.
     */
    @Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
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

			boolean eliminado = ControladorLibro.eliminar(id);

			if (!eliminado) {

				response.setStatus(HttpServletResponse.SC_NOT_FOUND);

				response.getWriter().print(XmlUtil.error("Libro no encontrado."));

				return;
			}

			String plantilla = """
			        <?xml version="1.0" encoding="UTF-8"?>
			        <respuesta>
			            <operacion>eliminar</operacion>
			            <mensaje>Libro eliminado correctamente.</mensaje>
			            <id>%s</id>
			        </respuesta>
			        """;

			String xml = String.format(plantilla, id);

			response.setStatus(HttpServletResponse.SC_OK);

			response.getWriter().print(xml);

		} catch (NumberFormatException e) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			response.getWriter().print(XmlUtil.error("El parámetro id debe ser un número entero."));
		}
	}
}