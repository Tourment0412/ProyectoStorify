package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Builder
@ToString
public class Cancion {
	@EqualsAndHashCode.Include
	@NonNull
	private String codigo;
	@NonNull
	private String nombreCancion;
	@NonNull
	private String nombreAlbum;
	@NonNull
	private String caratula;
	@NonNull
	private String anio;
	@NonNull
	private String duracion;
	@NonNull
	private String url;
	
	private Genero genero;
	
	private ListaDoble<Artista> lstArtista;

	public Cancion(@NonNull String codigo, @NonNull String nombreCancion, @NonNull String nombreAlbum,
			@NonNull String caratula, @NonNull String anio, @NonNull String duracion, @NonNull String url, Genero genero,
			ListaDoble<Artista> lstArtista) {
		super();
		this.codigo = codigo;
		this.nombreCancion = nombreCancion;
		this.nombreAlbum = nombreAlbum;
		this.caratula = caratula;
		this.anio = anio;
		this.duracion = duracion;
		this.url = url;
		this.genero = genero;
		this.lstArtista=lstArtista;
	}

	public Cancion() {
		lstArtista=new ListaDoble<Artista>();
	}
	
	public void agregarArtista(Artista artista) {
		lstArtista.agregarfinal(artista);
	}
	
	public void eliminarArtista(Artista artista) {
		lstArtista.eliminar(artista);
	}

}
