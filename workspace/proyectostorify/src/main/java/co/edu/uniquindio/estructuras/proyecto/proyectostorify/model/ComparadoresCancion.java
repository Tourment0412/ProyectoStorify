package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import java.util.Comparator;

public class ComparadoresCancion {
	
	public static final Comparator<Cancion> POR_NOMBRE = Comparator.comparing(Cancion::getNombreCancion, String::compareToIgnoreCase);
    public static final Comparator<Cancion> POR_ALBUM = Comparator.comparing(Cancion::getNombreAlbum, String::compareToIgnoreCase);
    public static final Comparator<Cancion> POR_DURACION = Comparator.comparingInt(Cancion::getDuracionEnSegundos);
    public static final Comparator<Cancion> POR_GENERO = Comparator.comparing(c -> c.getGenero().toString(), String::compareToIgnoreCase);

}
