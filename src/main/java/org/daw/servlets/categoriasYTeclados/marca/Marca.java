package org.daw.servlets.categoriasYTeclados.marca;
import java.time.LocalDate;

public class Marca {
	private int id;
	private String nombre;
	private LocalDate fechaConstitucion;

	public Marca() {
		super();
	}

	public Marca(int id, String nombre, LocalDate fechaConstitucion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaConstitucion = fechaConstitucion;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public LocalDate getFechaConstitucion() { return fechaConstitucion; }
	public void setFechaConstitucion(LocalDate fechaConstitucion) { this.fechaConstitucion = fechaConstitucion; }
}