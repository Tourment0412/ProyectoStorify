package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
@ToString
public class Administrador extends Cuenta {
	@EqualsAndHashCode.Include
	@NonNull
	private String idAdministrador;

	public Administrador(@NonNull String username, @NonNull String contrasenia, @NonNull String idAdministrador) {
		super(username, contrasenia);
		this.idAdministrador = idAdministrador;
	}

	public Administrador() {
		super();
	}

}
