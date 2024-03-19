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
public class Usuario extends Cuenta {

	@EqualsAndHashCode.Include
	@NonNull
	private String email;

	public Usuario(@NonNull String username, @NonNull String contrasenia, @NonNull String email) {
		super(username, contrasenia);
		this.email = email;
	}

	public Usuario() {
		super();
	}

}
