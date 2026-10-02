package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de crear personas.
 *
 * Ejemplo:
 *
 * POST /personas?nombre=Pedro&edad=35
 */
@WebServlet("/libros/crear")
public class LibroPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Procesa las peticiones HTTP POST.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Devolvemos una respuesta de texto.
        response.setContentType("text/plain;charset=UTF-8");

        // Obtenemos los datos enviados como query parameters.
        String titulo = request.getParameter("titulo");
        String anioPublicacionParam = request.getParameter("anioPublicacion");

        // Comprobamos que se hayan recibido los dos parámetros.
        if (titulo == null || anioPublicacionParam == null) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().println(
                    "Faltan los parámetros titulo y anioPublicacion."
            );

            return;
        }

        // Convertimos la edad de String a int.
        int anioPublicacion = Integer.parseInt(anioPublicacionParam);

        // Creamos la nueva persona.
        Libro libro =
                ControladorLibro.crear(titulo, anioPublicacion);

        // HTTP 201 indica que se ha creado un nuevo recurso.
        response.setStatus(
                HttpServletResponse.SC_CREATED
        );

        response.getWriter().println(
                "Libro creado correctamente."
        );

        response.getWriter().println(
                "ID asignado: " + libro.getId()
        );
    }
}