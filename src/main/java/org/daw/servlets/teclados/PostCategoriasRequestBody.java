package org.daw.servlets.teclados;

public class PostCategoriasRequestBody {

	private String descripcion;

	public PostCategoriasRequestBody() {
		super();
	}

	public PostCategoriasRequestBody(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
