package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Genero {
	
	ROCK ("Rock"), POP ("Pop"), PUNK ("Punk"), REGGAETON ("Reggaeton"), ELECTRONICA ("Electronica");
	
	@Getter
	private String genero;
	
	/**
	 * 
	 * @return
	 */
	public static String[] stringValues() {
		Genero[] values = values();
		String[] stringValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			stringValues[i] = values[i].getGenero();
		return stringValues;
	}
	
	/**
	 * 
	 * @param estadoString
	 * @return
	 */
	public static Genero getEstadoByString(String estadoString) {
		Genero[] values = values();
		for (Genero estado : values)
			if (estado.getGenero().equals(estadoString))
				return estado;
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return genero;
	}

}
