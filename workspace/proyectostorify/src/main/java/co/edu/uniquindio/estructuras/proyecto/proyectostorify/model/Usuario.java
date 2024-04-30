package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
@ToString
public class Usuario extends Cuenta {

	@EqualsAndHashCode.Include
	@NonNull
	private String email;
	
	private CircularList<Cancion> lstCancionesGuardadas;
	
	private CircularList<Cancion> lstCancionesFavoritas;

	public Usuario(@NonNull String username, @NonNull String contrasenia, @NonNull String email) {
		super(username, contrasenia);
		this.email = email;
		lstCancionesGuardadas=new CircularList<Cancion>();
	}

	public Usuario() {
		super();
		lstCancionesGuardadas=new CircularList<Cancion>();
	}
	
	public void agregarCancionGuardada(Cancion cancion) {
		lstCancionesGuardadas.add(cancion);
	}
	
	public void eliminarCancionGuardada(Cancion cancion) {
		lstCancionesGuardadas.remove(cancion);
	}
	
	public void agregarCancionFavorita(Cancion cancion) {
		lstCancionesFavoritas.add(cancion);
	}
	
	public void eliminarCancionFavorita(Cancion cancion) {
		lstCancionesFavoritas.remove(cancion);
	}

}
