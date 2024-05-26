package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@ToString
public class Artista implements Comparable<Artista>{
	
	private String codigo;
	private String nombre;
	private String nacionalidad;
	private boolean esGrupo;
	private ListaDoble<Cancion> lstCanciones;
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Artista o) {
		return nombre.compareTo(o.getNombre());
		
	}
	
	/**
	 * 
	 * @param cancion
	 */
	public void agregarCancion(Cancion cancion) {
		cancion.getLstArtistas().add(this);
		lstCanciones.agregarfinal(cancion);
	}
	
	/**
	 * 
	 * @param cancion
	 */
	public void eliminarCancion(Cancion cancion) {
		lstCanciones.eliminar(cancion);
	}

	public Artista(String codigo, String nombre, String nacionalidad, boolean esGrupo,
			ListaDoble<Cancion> lstCanciones) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.esGrupo = esGrupo;
		this.lstCanciones = lstCanciones;
	}

	public Artista() {
		super();
		lstCanciones=new ListaDoble<Cancion>();
	}
	
	


}
