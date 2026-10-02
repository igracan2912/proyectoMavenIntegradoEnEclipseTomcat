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
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Devolvemos una respuesta de texto.
        response.setContentType("text/plain;charset=UTF-8");

        // Obtenemos el ID mediante un query parameter.
        String idParam = request.getParameter("id");

        // El ID es necesario para localizar la persona.
        if (idParam == null) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().println(
                    "Debe indicar el parámetro id."
            );

            return;
        }

        // Convertimos el ID de String a int.
        int id = Integer.parseInt(idParam);

        // Intentamos eliminar la persona.
        boolean eliminado =
                ControladorLibro.eliminar(id);

        if (!eliminado) {

            // No existe ninguna persona con ese ID.
            response.setStatus(
                    HttpServletResponse.SC_NOT_FOUND
            );

            response.getWriter().println(
                    "Libro no encontrado."
            );

            return;
        }

        /*
         * HTTP 204 indica que la operación ha sido correcta
         * y que no hay contenido que devolver.
         */
        response.setStatus(
                HttpServletResponse.SC_NO_CONTENT
        );
    }
}