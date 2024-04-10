package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Builder
@ToString
public class Artista implements Comparable<Artista>{
	
	private String codigo;
	private String nombre;
	private String nacionalidad;
	private boolean esGrupo;
	private ListaDoble<Cancion> lstCanciones;
	
	@Override
	public int compareTo(Artista o) {
		return nombre.compareTo(o.getNombre());
	}

	public void agregarCancion(Cancion cancion) {
		lstCanciones.agregarfinal(cancion);
	}
	
	public void eliminarCancion(Cancion cancion) {
		lstCanciones.eliminar(cancion);
	}

}
