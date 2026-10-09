package org.daw.servlets;


public class XmlUtil {

    private XmlUtil() {
    }

    public static String escapar(String texto) {

        if (texto == null) {
            return "";
        }

        return texto
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }


    public static String libro(Libro libro) {
        return String.format("""
                <libro>
                    <id>%d</id>
                    <titulo>%s</titulo>
                    <anioPublicacion>%d</anioPublicacion>
                </libro>
                """, 
                libro.getId(),
                escapar(libro.getTitulo()),
                libro.getAnioPublicacion()
        );
    }

    public static String error(String mensaje) {
        return String.format("""
                <?xml version="1.0" encoding="UTF-8"?>
                <error>
                    <mensaje>%s</mensaje>
                </error>
                """, 
                escapar(mensaje)
        );
    }
}