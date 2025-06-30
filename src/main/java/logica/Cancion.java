package logica;

import java.io.Serializable;

public class Cancion implements Serializable {
	private static final long serialVersionUID = 1L;
	String nombre;
	String artista;
	Genero genero;
	int duracion;
	int añoPublicacion;
	
	public Cancion(String nombre, String artista, Genero genero, int duracion, int añoPublicacion) {
		super();
		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.duracion = duracion;
		this.añoPublicacion = añoPublicacion;
	}

	@Override
	public String toString() {
		return "Cancion [nombre=" + nombre + ", artista=" + artista + ", genero=" + genero + ", duracion=" + duracion
				+ ", añoPublicacion=" + añoPublicacion + "]";
	}

	void mostrarInfo() {
		System.out.println(toString());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getAñoPublicacion() {
		return añoPublicacion;
	}

	public void setAñoPublicacion(int añoPublicacion) {
		this.añoPublicacion = añoPublicacion;
	}
}
