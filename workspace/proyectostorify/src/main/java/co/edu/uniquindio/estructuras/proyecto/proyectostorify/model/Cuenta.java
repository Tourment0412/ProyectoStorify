package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

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
public class Cuenta {
	@EqualsAndHashCode.Include
	@NonNull
	private String username;

	@NonNull
	private String contrasenia;

	public Cuenta(@NonNull String username, @NonNull String contrasenia) {
		this.username = username;
		this.contrasenia = contrasenia;
	}

	public Cuenta() {

	}

}
