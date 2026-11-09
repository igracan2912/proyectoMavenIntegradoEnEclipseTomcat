package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet encargado de autenticar a los usuarios.
 */
@WebServlet("/autenticacion")
public class ServletAutenticacion extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Credenciales de acceso para este ejercicio.
    private static final String USUARIO = "admin";
    private static final String PASSWORD = "1234";

    /**
     * Procesa las peticiones POST de autenticación.
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperamos los parámetros de la petición.
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        // Comprobamos que se han enviado ambos parámetros.
        if (usuario == null || password == null) {

            JSONUtils.enviarError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Es obligatorio indicar usuario y contraseña.");

            return;
        }

        // Comprobamos las credenciales.
        if (USUARIO.equals(usuario) && PASSWORD.equals(password)) {

            // Creamos una sesión HTTP para el usuario.
            HttpSession session = request.getSession();

            // Guardamos el nombre del usuario en la sesión.
            session.setAttribute("usuario", usuario);

            JSONUtils.enviarJSON(response,
                    HttpServletResponse.SC_OK,
                    new Error("Autenticación correcta."));

        } else {

            // Las credenciales no son correctas.
            JSONUtils.enviarError(response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Usuario o contraseña incorrectos.");
        }
    }
}