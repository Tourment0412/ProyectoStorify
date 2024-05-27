package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
@ToString
public class Administrador extends Cuenta {
	@EqualsAndHashCode.Include
	@NonNull
	private String idAdministrador;
	
	/**
	 * Metodod constructor
	 * @param username Nombre del usuario
	 * @param contrasenia Contrasenia del usuario
	 * @param idAdministrador Id del administrador
	 */
	public Administrador(@NonNull String username, @NonNull String contrasenia, @NonNull String idAdministrador) {
		super(username, contrasenia);
		this.idAdministrador = idAdministrador;
	}
	
	/**
	 * Metodo constructor
	 */
	public Administrador() {
		super();
	}

}
