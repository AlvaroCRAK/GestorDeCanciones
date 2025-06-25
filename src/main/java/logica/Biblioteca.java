package logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Biblioteca {
	private Scanner sc = new Scanner(System.in);
	private List<Cancion> canciones;

	public Biblioteca() {
		this.canciones = new ArrayList<>();
		cargarCanciones();
		ordenarCanciones();
	}
	
	public List<Cancion> getCanciones(){
		return this.canciones;
	}
	
	public Cancion getCancion(int index) {
		return canciones.get(index);
	}
	
	//Metodos adicionales
	void cargarCanciones() {
		canciones.add(new Cancion("Yellow", "Coldplay", Genero.ROCK, 269, 2000));
	    canciones.add(new Cancion("Oops!... I Did It Again", "Britney Spears", Genero.POP, 212, 2000));
	    canciones.add(new Cancion("The Real Slim Shady", "Eminem", Genero.RAP, 284, 2000));
	    canciones.add(new Cancion("Whenever, Wherever", "Shakira", Genero.POP, 210, 2001));
	    canciones.add(new Cancion("By The Way", "Red Hot Chili Peppers", Genero.ROCK, 218, 2002));
	    canciones.add(new Cancion("Gasolina", "Daddy Yankee", Genero.REGUETON, 197, 2004));
	    canciones.add(new Cancion("Yeah!", "Usher", Genero.ELECTRONICA, 250, 2004));
	    canciones.add(new Cancion("La Camisa Negra", "Juanes", Genero.SALSA, 223, 2005));
	    canciones.add(new Cancion("Fix You", "Coldplay", Genero.ROCK, 295, 2005));
	    canciones.add(new Cancion("Rehab", "Amy Winehouse", Genero.JAZZ, 214, 2006));
	    canciones.add(new Cancion("Umbrella", "Rihanna", Genero.POP, 260, 2007));
	    canciones.add(new Cancion("Viva la Vida", "Coldplay", Genero.ROCK, 242, 2008));
	    canciones.add(new Cancion("Bad Romance", "Lady Gaga", Genero.ELECTRONICA, 295, 2009));
	    canciones.add(new Cancion("Rolling in the Deep", "Adele", Genero.POP, 228, 2010));
	    canciones.add(new Cancion("Ai Se Eu Te Pego", "Michel Teló", Genero.SALSA, 210, 2011));
	    canciones.add(new Cancion("Gangnam Style", "PSY", Genero.ELECTRONICA, 219, 2012));
	    canciones.add(new Cancion("Get Lucky", "Daft Punk", Genero.ELECTRONICA, 248, 2013));
	    canciones.add(new Cancion("Happy", "Pharrell Williams", Genero.POP, 233, 2014));
	    canciones.add(new Cancion("Uptown Funk", "Bruno Mars", Genero.POP, 269, 2015));
	    canciones.add(new Cancion("Sorry", "Justin Bieber", Genero.POP, 200, 2015));
	    canciones.add(new Cancion("Chantaje", "Shakira ft. Maluma", Genero.REGUETON, 210, 2016));
	    canciones.add(new Cancion("Despacito", "Luis Fonsi", Genero.REGUETON, 230, 2017));
	    canciones.add(new Cancion("Havana", "Camila Cabello", Genero.POP, 217, 2018));
	    canciones.add(new Cancion("Old Town Road", "Lil Nas X", Genero.RAP, 157, 2019));
	    canciones.add(new Cancion("Blinding Lights", "The Weeknd", Genero.ELECTRONICA, 200, 2020));
	    canciones.add(new Cancion("Dakiti", "Bad Bunny", Genero.REGUETON, 205, 2020));
	    canciones.add(new Cancion("Pepas", "Farruko", Genero.ELECTRONICA, 258, 2021));
	    canciones.add(new Cancion("As It Was", "Harry Styles", Genero.POP, 167, 2022));
	    canciones.add(new Cancion("Flowers", "Miley Cyrus", Genero.POP, 198, 2023));
	    canciones.add(new Cancion("Nuevo Amanecer", "Artista Ficticio", Genero.CLASICA, 312, 2025));
	}
	
	void ordenarCanciones() {
		canciones.sort((c1, c2) -> c1.getNombre().compareTo(c2.getNombre()));
	}
	
	//Muestra las canciones disponibles de una lista de canciones
	public void mostrarCanciones(List<Cancion> lista) {
		if(lista.size()==0) {
			System.out.println("Vacio");
			return;
		}
		int i = 1;
		for (Cancion cancion : lista) {
			System.out.println(i+") "+cancion);
			i++;
		}
	}
	
	//Metodo para elegir una cancion de entre una lista de canciones
	public Cancion elegirCancion(List<Cancion> canciones) {
		mostrarCanciones(canciones);
		System.out.print("Seleccione una cancion: ");
		int op = sc.nextInt()-1;
		if(op < 0 || op >= canciones.size()) {
			System.out.println("Opcion invalida!!");
			elegirCancion(canciones);
		}
		return getCancion(op);//Retorna cancion elegida
	}
	
	//Filtra una lista de canciones segun el año de publicacion en un intervalo dado
	public List<Cancion> filtrarPorAnio(int inicio, int fin){
		List<Cancion> resultado = new ArrayList<Cancion>(canciones);
		return resultado.stream()
				.filter((c) -> c.getAñoPublicacion()>= inicio && c.getAñoPublicacion()<= fin)
				.collect(Collectors.toList());
	}
	
	// Filtrar por Genero, recibe parametro Genero y devuelve una List<Cancion> ya
	  // filtrada
	  public List<Cancion> filtrarPorGenero(Genero genero) {
	    return canciones.stream()
	        .filter(c -> c.getGenero() == genero)
	        .collect(Collectors.toList());
	  }

	  // < 4 minutos
	  public List<Cancion> filtrarDuracionMenorA4Min() {
	    return canciones.stream()
	        .filter(c -> c.getDuracion() < 240)
	        .collect(Collectors.toList());
	  }

	  // > 4 minutos
	  public List<Cancion> filtrarDuracionMayorOIgualA4Min() {
	    return canciones.stream()
	        .filter(c -> c.getDuracion() >= 240)
	        .collect(Collectors.toList());
	  }
	
	//Buscador(Principal) de coincidencias en el titulo de una cancion
	public List<Cancion> buscarPorTitulo(String patron) {
		List<Cancion> resultado = new ArrayList<Cancion>(canciones);
		String regex = ".*"+patron.toLowerCase()+".*";
		return resultado.stream()
				.filter((c) -> c.getNombre().matches(regex))
				.collect(Collectors.toList());
	}
}
