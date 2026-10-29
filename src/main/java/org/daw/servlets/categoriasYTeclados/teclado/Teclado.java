package org.daw.servlets.categoriasYTeclados.teclado;

public class Teclado {
	private int id;
	private String color;
	private String idioma;
	private int idCategoria;
	
	public Teclado() {
		super();
	}
	public Teclado(int id, String color, String idioma, int idCategoria) {
		super();
		this.id = id;
		this.color = color;
		this.idioma = idioma;
		this.idCategoria = idCategoria;
	}
	
	public int getId() {
		return this.id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public String getColor() {
		return this.color;
	}


	public void setColor(String color) {
		this.color = color;
	}
	
	public String getIdioma() {
		return this.idioma;
	}


	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public int getIdCategoria() {
		return this.idCategoria;
	}


	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
