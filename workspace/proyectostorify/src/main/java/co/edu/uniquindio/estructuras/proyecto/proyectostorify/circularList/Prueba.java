package co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList;

import java.util.HashMap;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;

public class Prueba {
	
	public static void main(String[] args) {
		Storify tiendaMusica=new Storify();
		inicializarDatos(tiendaMusica);
		tiendaMusica.obtenerCancionesArtistas();
	}
	
	public static void inicializarDatos(Storify tiendaMusica) {
	    // Inicialización de la tienda de música
	    tiendaMusica.setLstArtistas(new BinaryTree<Artista>());
	    tiendaMusica.setLstCanciones(new CircularList<Cancion>());
	    tiendaMusica.setLstCuentas(new HashMap<String, Cuenta>());

	    // Creación de cuentas de administrador y usuario
	    Administrador admin = new Administrador("pepe", "123", "code1");
	    Usuario usuario = new Usuario("juan", "456", "@789");
	    tiendaMusica.getLstCuentas().put(usuario.getUsername(), usuario);
	    tiendaMusica.getLstCuentas().put(admin.getUsername(), admin);

	    // Creación de artistas y canciones
	    Artista artista1 = new Artista("aaaaa", "Evanescence", "Estadounidense", true, new ListaDoble<Cancion>());
	    Artista artista2 = new Artista("aaaab", "Michael Jackson", "Estadounidense", false, new ListaDoble<Cancion>());
	    Artista artista3 = new Artista("aaaac", "Maluma", "Colombiana", false, new ListaDoble<Cancion>());
	    tiendaMusica.getLstArtistas().add(artista1);
	    tiendaMusica.getLstArtistas().add(artista2);
	    tiendaMusica.getLstArtistas().add(artista3);

	    // Creación y adición de canciones para los artistas
	    Cancion cancion1 = new Cancion("aaaaa", "Bring Me To Life", "Fallen", "", "2003", "3:55", "evan1.mp3", Genero.ROCK, new CircularList<Artista>());
	    Cancion cancion2 = new Cancion("aaaab", "Call Me When You're Sober", "The Open Door", "", "2006", "3:32", "evan2.mp3", Genero.ROCK, new CircularList<Artista>());
	    Cancion cancion3 = new Cancion("aaaac", "Billie Jean", "Thriller", "", "1982", "4:45", "Mich1.mp3", Genero.POP, new CircularList<Artista>());
	    Cancion cancion4 = new Cancion("aaaad", "Beat It", "Thriller", "", "1982", "4:48", "Mich2.mp3", Genero.POP, new CircularList<Artista>());
	    Cancion cancion5 = new Cancion("aaaae", "Felices los 4", "F.A.M.E.", "", "2018", "4:50", "Malu1.mp3", Genero.REGGAETON, new CircularList<Artista>());
	    Cancion cancion6 = new Cancion("aaaaf", "Hawai", "Papi Juancho", "", "2020", "4:18", "Malu2.mp3", Genero.REGGAETON, new CircularList<Artista>());
	    artista1.agregarCancion(cancion1);
	    artista1.agregarCancion(cancion2);
	    artista2.agregarCancion(cancion3);
	    artista2.agregarCancion(cancion4);
	    artista3.agregarCancion(cancion5);
	    artista3.agregarCancion(cancion6);
	    tiendaMusica.agregarCancion(cancion1);
	    tiendaMusica.agregarCancion(cancion2);
	    tiendaMusica.agregarCancion(cancion3);
	    tiendaMusica.agregarCancion(cancion4);
	    tiendaMusica.agregarCancion(cancion5);
	    tiendaMusica.agregarCancion(cancion6);

	    // Establecimiento de canciones para cada artista
	    tiendaMusica.establecerCancionesArtista(artista1);
	    tiendaMusica.establecerCancionesArtista(artista2);
	    tiendaMusica.establecerCancionesArtista(artista3);

	}

}
