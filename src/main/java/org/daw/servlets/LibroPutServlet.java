package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de modificar personas.
 *
 * Ejemplo:
 *
 * PUT /personas?id=2&nombre=Luis&edad=32
 */
@WebServlet("/libros/modificar")
public class LibroPutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Procesa las peticiones HTTP PUT.
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // La respuesta será texto.
        response.setContentType("text/plain;charset=UTF-8");

        // Obtenemos el ID de la persona que queremos modificar.
        String idParam = request.getParameter("id");

        // El ID es obligatorio.
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

        // Buscamos la persona.
        Libro libro =
                ControladorLibro.buscarPorId(id);

        // Si no existe, devolvemos HTTP 404.
        if (libro == null) {

            response.setStatus(
                    HttpServletResponse.SC_NOT_FOUND
            );

            response.getWriter().println(
                    "Libro no encontrado."
            );

            return;
        }

        /*
         * Estos parámetros son opcionales.
         * Solo modificamos los que se hayan recibido.
         */
        String titulo = request.getParameter("titulo");
        String anioPublicacionParam = request.getParameter("anioPublicacion");

        if (titulo != null) {
            libro.setTitulo(titulo);
        }

        if (anioPublicacionParam != null) {

            int anioPublicacion = Integer.parseInt(anioPublicacionParam);

            libro.setAnioPublicacion(anioPublicacion);
        }

        // La modificación se ha realizado correctamente.
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println(
                "Libro modificado correctamente."
        );

        // Devolvemos los datos actuales de la persona.
        response.getWriter().println(
                "ID: " + libro.getId()
        );

        response.getWriter().println(
                "Titulo: " + libro.getTitulo()
        );

        response.getWriter().println(
                "Año publicacion: " + libro.getAnioPublicacion()
        );
    }
}