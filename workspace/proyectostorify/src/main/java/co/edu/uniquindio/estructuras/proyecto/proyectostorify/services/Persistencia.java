package co.edu.uniquindio.estructuras.proyecto.proyectostorify.services;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Storify;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.ArchivoUtil;

public class Persistencia {
	
	public static final String RUTA_ARCHIVO_MODELO_TIENDA="";

	/**
	 * Carga el archivo con los datos de la tienda
	 * @return Tienda creada a partir de los datos
	 */
    public static Storify cargarRecursoTiendaBinario() {

    	Storify subasta = null;

        try {
            subasta = (Storify)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subasta;
    }

    /**
     * Guarda los datos de la tienda
     * @param tienda Tienda de la cual sus datos van a ser guardados
     */
    public static void guardarRecursoTiendaBinario(Storify tienda) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA, tienda);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
