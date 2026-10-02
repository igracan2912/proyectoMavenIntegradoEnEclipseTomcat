package org.daw.servlets;

/**
 * Representa una persona que vamos a gestionar mediante los Servlets.
 */
public class Libro {

    // Identificador único de la persona.
    private int id;

    // Nombre de la persona.
    private String titulo;

    // Edad de la persona.
    private int anioPublicacion;

    /**
     * Crea una persona con los datos indicados.
     */
    public Libro(int id, String titulo, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}