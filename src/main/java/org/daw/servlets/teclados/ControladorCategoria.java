package org.daw.servlets.teclados;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

public class ControladorCategoria {

	private static List<Categoria> lista = new ArrayList<Categoria>();
	
	
	static {
		lista.add(new Categoria(1, "Mecánicos"));
		lista.add(new Categoria(2, "Inalámbricos"));
		lista.add(new Categoria(3, "Membrana"));
	}
	
	public static List<Categoria > getLista() {
		return lista;
	}

	
	private static int getMaxId () {
		if (lista.size() == 0) return 1;
		else {
			int mayorId = lista.getFirst().getId();
			for (Categoria c : lista) {
				if (c.getId() > mayorId) mayorId = c.getId();
			}
			return mayorId;
		}
	}

	
	public static Categoria createCategoria (String descripcion) {
		Categoria c = new Categoria(getMaxId() + 1, descripcion);
		lista.add(c);
		return c;
	}
	
	
	public static Categoria getCategoria (int id) {
		for (Categoria c : ControladorCategoria.getLista()) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
}






