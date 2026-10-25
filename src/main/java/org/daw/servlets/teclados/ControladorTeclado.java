package org.daw.servlets.teclados;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

public class ControladorTeclado {

	private static List<Teclado> lista = new ArrayList<Teclado>();
	
	
	static {
		lista.add(new Teclado(1, "rojo", "ruso", 1));
		lista.add(new Teclado(2, "verde", "ruso", 2));
		lista.add(new Teclado(3, "azul", "ruso", 3));
	}
	
	public static List<Teclado > getLista() {
		return lista;
	}

	
	private static int getMaxId () {
		if (lista.size() == 0) return 1;
		else {
			int mayorId = lista.get(0).getId();
			for (Teclado t : lista) {
				if (t.getId() > mayorId) mayorId = t.getId();
			}
			return mayorId;
		}
	}

	
	public static Teclado createTeclado (String color, String idioma, int idCategoria) {
		Teclado t = new Teclado(getMaxId() + 1, color, idioma, idCategoria);
		lista.add(t);
		return t;
	}
	
	
	public static Teclado getTeclado (int id) {
		for (Teclado t : ControladorTeclado.getLista()) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}
}






