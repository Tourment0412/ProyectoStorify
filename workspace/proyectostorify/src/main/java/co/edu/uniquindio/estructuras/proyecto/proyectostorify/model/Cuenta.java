package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Builder
@ToString
public class Cuenta {
	@EqualsAndHashCode.Include
	@NonNull
	private String username;
	
	/**
	 * 
	 */
	@NonNull
	private String contrasenia;
	
	/**
	 * Metodo constructor
	 * @param username Nombre de usuario
	 * @param contrasenia Contrasenia
	 */
	public Cuenta(@NonNull String username, @NonNull String contrasenia) {
		this.username = username;
		this.contrasenia = contrasenia;
	}
	
	/**
	 * Metodo constructor
	 */
	public Cuenta() {

	}
	
	/**
	 * Verifica si los datos indicados pertenecen a esa cuenta
	 * @param nombre Nombre a comparar
	 * @param contrasenia Contrasenia a comparar
	 * @return Respuesta de que si los datos coinciden
	 */
	public boolean verificarDatos(String nombre, String contrasenia) {
		boolean respuesta=false;
		if (this.username.equals(nombre) && this.contrasenia.equals(contrasenia)) {
			respuesta=true;
		}
		return respuesta;
	}

}
