package org.daw.servlets;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las personas almacenadas en memoria.
 *
 * De momento no utilizamos una base de datos.
 */
public class ControladorLibro {

    /*
     * Lista compartida por todos los Servlets.
     * Todos trabajan sobre los mismos datos.
     */
    private static final List<Libro> libros = new ArrayList<>();

    // Próximo identificador que se asignará.
    private static int siguienteId = 4;

    /*
     * Datos iniciales para poder probar el CRUD directamente.
     */
    static {
        libros.add(new Libro(1, "Quijote", 1999));
        libros.add(new Libro(2, "Red Dead", 2002));
        libros.add(new Libro(3, "1987", 2005));
    }

    /**
     * Devuelve todas las personas.
     */
    public static List<Libro> listar() {
        return libros;
    }

    /**
     * Busca una persona por su ID.
     *
     * @return la persona encontrada o null si no existe.
     */
    public static Libro buscarPorId(int id) {

        for (Libro libro : libros) {

            if (libro.getId() == id) {
                return libro;
            }
        }

        return null;
    }

    /**
     * Crea una persona y la añade a la lista.
     */
    public static Libro crear(String titulo, int anioPublicacion) {

        Libro libro = new Libro(
                siguienteId++,
                titulo,
                anioPublicacion
        );

        libros.add(libro);

        return libro;
    }

    /**
     * Elimina una persona por su ID.
     *
     * @return true si se ha eliminado, false si no existe.
     */
    public static boolean eliminar(int id) {

        Libro libro = buscarPorId(id);

        if (libro == null) {
            return false;
        }

        libros.remove(libro);

        return true;
    }
}