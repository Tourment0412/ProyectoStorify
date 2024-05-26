package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
//@ToString
public class Cancion {
	@EqualsAndHashCode.Include
	@NonNull
	private String codigo;
	@NonNull
	private String nombreCancion;
	@NonNull
	private String nombreAlbum;
	@NonNull
	private String caratula="";
	@NonNull
	private String anio;
	@NonNull
	private String duracion;
	@NonNull
	private String url;
	@NonNull
	private Genero genero;
	@NonNull
	private CircularList<Artista> lstArtistas;
	
	/**
	 * 
	 */
	public Cancion() {
		super();
		lstArtistas=new CircularList<Artista>();
	}
	
	/**
	 * 
	 * @param codigo
	 * @param nombreCancion
	 * @param nombreAlbum
	 * @param caratula
	 * @param anio
	 * @param duracion
	 * @param url
	 * @param genero
	 * @param lstCircularList
	 */
	public Cancion(@NonNull String codigo, @NonNull String nombreCancion, @NonNull String nombreAlbum,
			@NonNull String caratula, @NonNull String anio, @NonNull String duracion, @NonNull String url,
			@NonNull Genero genero, @NonNull CircularList<Artista> lstCircularList) {
		super();
		this.codigo = codigo;
		this.nombreCancion = nombreCancion;
		this.nombreAlbum = nombreAlbum;
		this.caratula = caratula;
		this.anio = anio;
		this.duracion = duracion;
		this.url = url;
		this.genero = genero;
		this.lstArtistas = lstCircularList;
	}

}
