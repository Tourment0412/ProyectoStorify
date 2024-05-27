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
	 * Metodo costructor
	 */
	public Cancion() {
		super();
		lstArtistas=new CircularList<Artista>();
	}
	
	/**
	 * Metodo constructor
	 * @param codigo Codigo de la cancion
	 * @param nombreCancion Nombre de la cancion
	 * @param nombreAlbum Nombre del album del que hace parte la cancion
	 * @param caratula Caratula  de la cancion
	 * @param anio Anio de salida de la cancion
	 * @param duracion Duracion de la cancion
	 * @param url Direccion del audio de la cancion
	 * @param genero Genero de la cancion
	 * @param lstCircularList Lista de los artitas de los que hace parte la cancion
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
	
	/**
	 * Obtiene la duracion de las canciones en segundos
	 * @return Duracion en segundos
	 */
	public int getDuracionEnSegundos() {
        String[] partes = duracion.split(":");
        int minutos = Integer.parseInt(partes[0]);
        int segundos = Integer.parseInt(partes[1]);
        return minutos * 60 + segundos;
    }

	/**
	 * Convierte la cancion en una cadena
	 */
	@Override
	public String toString() {
		return "Cancion [codigo=" + codigo + ", nombreCancion=" + nombreCancion + "]";
	}
	
	

}
