package co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils;

import java.io.File;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;

public class TiendaUtil {
	
	public static char obtenerCaracter(int numero) {
		char caracter='*';
		switch(numero) {
			case 0: caracter = '0'; break;
		    case 1: caracter = '1'; break;
		    case 2: caracter = '2'; break;
		    case 3: caracter = '3'; break;
		    case 4: caracter = '4'; break;
		    case 5: caracter = '5'; break;
		    case 6: caracter = '6'; break;
		    case 7: caracter = '7'; break;
		    case 8: caracter = '8'; break;
		    case 9: caracter = '9'; break;
		    case 10: caracter = 'A'; break;
		    case 11: caracter = 'B'; break;
		    case 12: caracter = 'C'; break;
		    case 13: caracter = 'D'; break;
		    case 14: caracter = 'E'; break;
		    case 15: caracter = 'F'; break;
		    case 16: caracter = 'G'; break;
		    case 17: caracter = 'H'; break;
		    case 18: caracter = 'I'; break;
		    case 19: caracter = 'J'; break;
		    case 20: caracter = 'K'; break;
		    case 21: caracter = 'L'; break;
		    case 22: caracter = 'M'; break;
		    case 23: caracter = 'N'; break;
		    case 24: caracter = 'O'; break;
		    case 25: caracter = 'P'; break;
		    case 26: caracter = 'Q'; break;
		    case 27: caracter = 'R'; break;
		    case 28: caracter = 'S'; break;
		    case 29: caracter = 'T'; break;
		    case 30: caracter = 'U'; break;
		    case 31: caracter = 'V'; break;
		    case 32: caracter = 'W'; break;
		    case 33: caracter = 'X'; break;
		    case 34: caracter = 'Y'; break;
		    case 35: caracter = 'Z'; break;
		    case 36: caracter = 'a'; break;
		    case 37: caracter = 'b'; break;
		    case 38: caracter = 'c'; break;
		    case 39: caracter = 'd'; break;
		    case 40: caracter = 'e'; break;
		    case 41: caracter = 'f'; break;
		    case 42: caracter = 'g'; break;
		    case 43: caracter = 'h'; break;
		    case 44: caracter = 'i'; break;
		    case 45: caracter = 'j'; break;
		    case 46: caracter = 'k'; break;
		    case 47: caracter = 'l'; break;
		    case 48: caracter = 'm'; break;
		    case 49: caracter = 'n'; break;
		    case 50: caracter = 'o'; break;
		    case 51: caracter = 'p'; break;
		    case 52: caracter = 'q'; break;
		    case 53: caracter = 'r'; break;
		    case 54: caracter = 's'; break;
		    case 55: caracter = 't'; break;
		    case 56: caracter = 'u'; break;
		    case 57: caracter = 'v'; break;
		    case 58: caracter = 'w'; break;
		    case 59: caracter = 'x'; break;
		    case 60: caracter = 'y'; break;
		    case 61: caracter = 'z'; break;
		    case 62: caracter = '_'; break;
		    case 63: caracter = '-'; break;
		}
		return caracter;
	}

	public static String generarCadenaAleatoria() {
		int tamanio=5;
		String cadena;
		cadena=generarCadenaAleatoria(0,tamanio);
		return cadena;
	}
	
	public static String generarCadenaAleatoria(int i,int limite) {
		if (i>=limite) {
			return "";
		} else {
			return obtenerCaracter((int)(Math.random()*64))+generarCadenaAleatoria(i+1,limite);
		}
	}
	
	public static String obtenerRutaCopiaOrganizada(String archivoNombre) {
		String nombre;
		nombre=generarCadenaAleatoria();
		int posicion=archivoNombre.lastIndexOf('.');{
		}
		for (int i=posicion;i<archivoNombre.length();i++) {
			nombre+=archivoNombre.charAt(i);
		}
		return nombre;
	}

	public static boolean existeArchivoCaratula(String nombre) {
		if (nombre.equals("")) {
			return false;
		}
		String ruta = "src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/caratulasCanciones";
		File directorio = new File(ruta);
		for (File elemento : directorio.listFiles()) {
			if(!elemento.isHidden() && elemento.getName().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean existeArchivoAudio(String nombre) {
		if (nombre.equals("")) {
			return false;
		}
		String ruta = "src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/cancionesAudios";
		File directorio = new File(ruta);
		for (File elemento : directorio.listFiles()) {
			if(!elemento.isHidden() && elemento.getName().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	public static File obtenerArchivoAudio(Cancion cancion) {
		String ruta = "src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/cancionesAudios";
		File directorio = new File(ruta);
		if (cancion.getUrl().equals("")) {
			return null;
		}
		for (File elemento : directorio.listFiles()) {
			if(!elemento.isHidden() && elemento.getName().equals(cancion.getUrl())) {
				return elemento;
			}
		}
		return null;
	}

}
