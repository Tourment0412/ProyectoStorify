package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum Genero implements Serializable {
	
	ROCK ("Rock"), POP ("Pop"), PUNK ("Punk"), REGGAETON ("Reggaeton"), ELECTRONICA ("Electronica");
	
	@Getter
	private String genero;

	
	/**
	 * Obtiene los valores de los generos en forma de cadena
	 * @return Cadenas de los generos
	 */
	public static String[] stringValues() {
		Genero[] values = values();
		String[] stringValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			stringValues[i] = values[i].getGenero();
		return stringValues;
	}
	
	/**
	 * Obtiene el genero a partir de una cadena
	 * @param estadoString Cadena
	 * @return Genero obtenido a apartir d ela cadena
	 */
	public static Genero getEstadoByString(String estadoString) {
		Genero[] values = values();
		for (Genero estado : values)
			if (estado.getGenero().equals(estadoString))
				return estado;
		return null;
	}
	
	/**
	 * Convierte el genero en forma de cadena
	 */
	@Override
	public String toString() {
		return genero;
	}

}
