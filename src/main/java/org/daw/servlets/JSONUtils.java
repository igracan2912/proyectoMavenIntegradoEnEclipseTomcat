package org.daw.servlets;


import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

public class JSONUtils {

    // Objeto encargado de convertir objetos Java a JSON.
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convierte un objeto Java en una cadena JSON.
     */
    public static String toJSON(Object objeto) throws IOException {

        return objectMapper.writeValueAsString(objeto);
    }

    /**
     * Envía una respuesta JSON con el código de estado indicado.
     */
    public static void enviarJSON(HttpServletResponse response,
            int codigoEstado, Object objeto) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.setStatus(codigoEstado);

        response.getWriter().print(toJSON(objeto));
    }

    /**
     * Envía una respuesta JSON que contiene un mensaje.
     */
    public static void enviarError(HttpServletResponse response,
            int codigoEstado, String mensaje) throws IOException {

        enviarJSON(response, codigoEstado, new Error(mensaje));
    }
}
