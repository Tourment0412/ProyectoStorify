package co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils;

import java.io.IOException;

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
     *
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws Exception 
     */
    public static CircularList<Artista> cargarArtistas(Storify tienda,String ruta) throws Exception
    {
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
        }
        
        CircularList<Cancion> cancionesCargadas=new CircularList<Cancion>();
        
        for (int i = 1; posicionInicioCanciones+1 < contenido.size(); i++) {
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
        	} else {
        		artistaObtenido.agregarCancion(obtenerCancion(cancionesCargadas,linea.split(";")[1]));
        	}
        }
        
        return artistas;
    }
    
    
    public static Artista buscarArtistaNombre(CircularList<Artista> artistas,String nombre) {
    	for (Artista artista : artistas) {
			if (artista.getNombre().equals(nombre)) {
				return artista;
			}
		}
    	return null;
    }
    
    public static boolean estaCancionCargada(CircularList<Cancion> cancionesCargadas,String nombreCancion) {
    	for (Cancion cancion : cancionesCargadas) {
			if (cancion.getNombreCancion().equals(nombreCancion)) {
				return true;
			}
		}
    	return false;
    }
    
    public static Cancion obtenerCancion(CircularList<Cancion> cancionesCargadas,String nombreCancion) {
    	for (Cancion cancion : cancionesCargadas) {
			if (cancion.getNombreCancion().equals(nombreCancion)) {
				return cancion;
			}
		}
    	return null;
    }

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }



//	----------------------SAVES------------------------

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param ruta
     * @throws IOException
     */

    public static void guardarObjetos(Storify tienda, String ruta) throws IOException  {
    	Cancion cancion;//NombreArtista1;NombreCancion;NombreAlbum;Anio;Duracion;Genero;URLCancionYoutube
    	CircularList<Artista> lstArtistas=tienda.getLstArtistas().toCircularList();
    	ListaDoble<Cancion> lstCanciones;
        String contenidoArtistas = "#Artistas\n";//CodigoArtista1;NombreArtista1;Nacionalidad1;true/false1
        String contenidoCanciones = "";
        contenidoArtistas+="\n#Canciones\n";
        for(Artista artista:lstArtistas) {
        	contenidoArtistas+=artista.getCodigo()+";"+artista.getNombre()+";"+artista.getNacionalidad().toString()+";"+
        			artista.isEsGrupo()+"\n";
        	lstCanciones=artista.getLstCanciones();
        	for (int i = 0; i < lstCanciones.getTamanio(); i++) {
        		cancion=lstCanciones.obtener(i);
        		contenidoCanciones+=artista.getNombre()+","+cancion.getNombreCancion()+";"+cancion.getNombreAlbum()+";"+
        				cancion.getAnio()+";"+cancion.getDuracion()+cancion.getGenero().toString()+"\n";
			}
        }
        contenidoArtistas+=contenidoCanciones;
        
        ArchivoUtil.guardarArchivo(ruta, contenidoArtistas, true);
    }



    //------------------------------------SERIALIZACIÓN COMO DATDEL PROYECTO COMPLETO


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

    public static void guardarRecursoBancoBinario(Storify tienda) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA_BINARIO, tienda);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }






}
