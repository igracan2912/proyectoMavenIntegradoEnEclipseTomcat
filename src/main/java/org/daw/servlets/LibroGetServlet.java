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
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Indicamos que devolveremos HTML utilizando UTF-8.
        response.setContentType("text/html;charset=UTF-8");

        // La consulta se ha realizado correctamente.
        response.setStatus(HttpServletResponse.SC_OK);

        /*
         * Obtenemos el parámetro "id".
         *
         * Si no existe, getParameter() devuelve null.
         */
        String idParam = request.getParameter("id");

        response.getWriter().println("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <title>Libros</title>
            </head>
            <body>
                <h1>Libros</h1>
            """);

        /*
         * Si recibimos un ID, buscamos una persona concreta.
         */
        if (idParam != null) {

            // Los parámetros HTTP llegan como String.
            int id = Integer.parseInt(idParam);

            Libro libro =
                    ControladorLibro.buscarPorId(id);

            if (libro == null) {

                // No existe ninguna persona con ese ID.
                response.setStatus(
                        HttpServletResponse.SC_NOT_FOUND
                );

                response.getWriter().println(
                        "<p>Libro no encontrado.</p>"
                );

            } else {

                response.getWriter().println(
                        "<h2>" + libro.getTitulo() + "</h2>"
                );

                response.getWriter().println(
                        "<p>ID: " + libro.getId() + "</p>"
                );

                response.getWriter().println(
                        "<p>Año publicación: " + libro.getAnioPublicacion() + "</p>"
                );
            }

        } else {

            // Sin ID, devolvemos todas las personas.
            List<Libro> libros =
                    ControladorLibro.listar();

            response.getWriter().println("<ul>");

            for (Libro libro : libros) {

                response.getWriter().println(
                    "<li>" +
                    libro.getId() +
                    " - " +
                    libro.getTitulo() +
                    " ( Año de publicación: " +
                    libro.getAnioPublicacion() +
                    " )" +
                    "</li>"
                );
            }

            response.getWriter().println("</ul>");
        }

        response.getWriter().println("""
            </body>
            </html>
            """);
    }
}