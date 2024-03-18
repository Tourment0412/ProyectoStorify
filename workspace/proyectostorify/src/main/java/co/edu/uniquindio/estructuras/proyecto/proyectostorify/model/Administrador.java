package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Setter
@Getter
@Builder
@ToString
public class Administrador extends Cuenta{
	private String idAdministrador;
	
}
