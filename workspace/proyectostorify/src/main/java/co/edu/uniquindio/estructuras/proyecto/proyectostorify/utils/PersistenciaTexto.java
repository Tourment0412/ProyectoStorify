package co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils;

import java.io.IOException;
import java.util.HashMap;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;

public class PersistenciaTexto {


    //bancoUq/src/main/resources/persistencia/archivoClientes.txt
    public static final String RUTA_ARCHIVO_MODELO_TIENDA_BINARIO = "";
    public static final String RUTA_ARCHIVO_LOG="";
//	C:\td\persistencia



//	----------------------LOADS------------------------

   /**
    * Carga los artistas apartir de un archivo txt
    * @param tienda Tienda donde se van a cargar los artistas
    * @param ruta Ruta del archivo del ue se van a acargar los artistas
    * @return Lista con los artistas cargados
    * @throws Exception Excepcion en caso de que el formato del archivo no sea valido
    */
    public static CircularList<Artista> cargarArtistas(Storify tienda,String ruta) throws Exception
    {
    	tienda.setLstArtistas(new BinaryTree<Artista>());
    	tienda.setLstCanciones(new CircularList<Cancion>());
        CircularList<Artista> artistas =new CircularList<Artista>();
        CircularList<String> contenido = ArchivoUtil.leerArchivo(ruta);
        String linea="";
        int posicionInicioCanciones=0;
        if (contenido.size()==0 || !contenido.get(0).equals("#Artistas")) {
        	throw new Exception("Formato incorrecto");
        }
        
        for (int i = 0; i < contenido.size(); i++) {
        	if (contenido.get(i).equals("#Canciones")) {
        		break;
        	}
        	posicionInicioCanciones++;
        }
        
        for (int i = 1; i < posicionInicioCanciones-1; i++)
        {
            linea = contenido.get(i);//CodigoArtista1;NombreArtista1;Nacionalidad1;true/false1
            Artista artista = new Artista();
            artista.setCodigo(linea.split(";")[0]);
            artista.setNombre(linea.split(";")[1]);
            artista.setNacionalidad(linea.split(";")[2]);
            artista.setEsGrupo(Boolean.parseBoolean(linea.split(";")[3]));
            artistas.add(artista);
            tienda.agregarArtista(artista);
        }
        
        CircularList<Cancion> cancionesCargadas=new CircularList<Cancion>();
        
        for (int i = posicionInicioCanciones+1; i < contenido.size(); i++) {
        	linea = contenido.get(i);//NombreArtista1;NombreCancion;NombreAlbum;Anio;Duracion;Genero;URLCancionYoutube
        	Artista artistaObtenido=buscarArtistaNombre(artistas,linea.split(";")[0]);
        	if (!estaCancionCargada(cancionesCargadas,linea.split(";")[1])) {
        		Cancion cancion=new Cancion();
            	cancion.getLstArtistas().add(null);
            	cancion.setNombreCancion(linea.split(";")[1]);
            	cancion.setNombreAlbum(linea.split(";")[2]);
            	cancion.setAnio(linea.split(";")[3]);
            	cancion.setDuracion(linea.split(";")[4]);
            	cancion.setGenero(Genero.getEstadoByString(linea.split(";")[5]));
            	cancion.setUrl(linea.split(";")[6]);
            	artistaObtenido.agregarCancion(cancion);
            	tienda.agregarCancion(cancion);
        	} else {
        		artistaObtenido.agregarCancion(obtenerCancion(cancionesCargadas,linea.split(";")[1]));
        	}
        }
        
        return artistas;
    }
	
	/**
	 * Busca un artista basado en su nombre
	 * @param artistas Lista de los artistas donde se va a realizar la busqueda
	 * @param nombre Nombre del artista a buscar
	 * @return Artista encontrado con el nombre indicado
	 */
    public static Artista buscarArtistaNombre(CircularList<Artista> artistas,String nombre) {
    	for (Artista artista : artistas) {
			if (artista.getNombre().equals(nombre)) {
				return artista;
			}
		}
    	return null;
    }
	
	/**
	 * Verifica si una cancion ya fue cargada del archivo
	 * @param cancionesCargadas Canciones cargadas hasta el momento
	 * @param nombreCancion Nombre de la cancion
	 * @return Respuesta de que si la cancion ya fue cargada
	 */
    public static boolean estaCancionCargada(CircularList<Cancion> cancionesCargadas,String nombreCancion) {
    	for (Cancion cancion : cancionesCargadas) {
			if (cancion.getNombreCancion().equals(nombreCancion)) {
				return true;
			}
		}
    	return false;
    }
	
	/**
	 * 
	 * @param cancionesCargadas
	 * @param nombreCancion
	 * @return
	 */
    public static Cancion obtenerCancion(CircularList<Cancion> cancionesCargadas,String nombreCancion) {
    	for (Cancion cancion : cancionesCargadas) {
			if (cancion.getNombreCancion().equals(nombreCancion)) {
				return cancion;
			}
		}
    	return null;
    }
	
	/**
	 * 
	 * @param mensajeLog
	 * @param nivel
	 * @param accion
	 */
    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }



//	----------------------SAVES------------------------


	
	/**
	 * 
	 * @param tienda
	 * @param ruta
	 * @throws IOException
	 */
    public static void guardarArtistas(Storify tienda, String ruta) throws IOException  {
    	Cancion cancion;//NombreArtista1;NombreCancion;NombreAlbum;Anio;Duracion;Genero;URLCancionYoutube
    	CircularList<Artista> lstArtistas=tienda.getLstArtistas().toCircularList();
    	ListaDoble<Cancion> lstCanciones;
        String contenidoArtistas = "#Artistas\n";//CodigoArtista1;NombreArtista1;Nacionalidad1;true/false1
        String contenidoCanciones = "";
        contenidoCanciones+="\n#Canciones\n";
        for(Artista artista:lstArtistas) {
        	contenidoArtistas+=artista.getCodigo()+";"+artista.getNombre()+";"+artista.getNacionalidad().toString()+";"+
        			artista.isEsGrupo()+"\n";
        	lstCanciones=artista.getLstCanciones();
        	for (int i = 0; i < lstCanciones.getTamanio(); i++) {
        		cancion=lstCanciones.obtener(i);
        		contenidoCanciones+=artista.getNombre()+";"+cancion.getNombreCancion()+";"+cancion.getNombreAlbum()+";"+
        				cancion.getAnio()+";"+cancion.getDuracion()+";"+cancion.getGenero().toString()
        				+";"+cancion.getUrl()+"\n";
			}
        }
        contenidoArtistas+=contenidoCanciones;
        
        ArchivoUtil.guardarArchivo(ruta, contenidoArtistas, false);
    }



    //------------------------------------SERIALIZACIÓN COMO DATDEL PROYECTO COMPLETO

	
	/**
	 * 
	 * @return
	 */
    public static Storify cargarRecursoBancoBinario() {

    	Storify tienda = null;

        try {
        	tienda = (Storify)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tienda;
    }
	
	/**
	 * 
	 * @param tienda
	 */
    public static void guardarRecursoBancoBinario(Storify tienda) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA_BINARIO, tienda);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }






}
