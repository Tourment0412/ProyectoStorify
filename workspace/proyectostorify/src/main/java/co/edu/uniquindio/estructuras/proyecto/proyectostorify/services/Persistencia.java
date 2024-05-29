package co.edu.uniquindio.estructuras.proyecto.proyectostorify.services;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Storify;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.ArchivoUtil;

public class Persistencia {
	
	public static final String RUTA_ARCHIVO_MODELO_TIENDA="";

	
    public static Storify cargarRecursoSubastaBinario() {

    	Storify subasta = null;

        try {
            subasta = (Storify)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subasta;
    }

    public static void guardarRecursoSubastaBinario(Storify subasta) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA, subasta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
