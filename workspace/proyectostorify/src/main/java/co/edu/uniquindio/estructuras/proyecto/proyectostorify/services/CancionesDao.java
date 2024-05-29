package co.edu.uniquindio.estructuras.proyecto.proyectostorify.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import lombok.Getter;

public class CancionesDao {
	/**
	 * variable for singleton of the class
	 */
	private static CancionesDao instance;
	/**
	 * Constant indicating the path of the persistence file
	 */
	@Getter
	private static final String RUTA = "src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/data/canciones.dat";

	/**
	 * Method to return the instance of the class in memory, if it does not exist.
	 * 
	 * @return instance
	 */
	public static CancionesDao getInstance() {
		if (instance == null) {
			instance = new CancionesDao();
		}
		return instance;
	}

	/**
	 * Method for saving the list {@link CircularList} of songs in the serialization
	 * file
	 * 
	 * @param lstCanciones
	 */
	public void saveData(CircularList<Cancion> listaCanciones) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(listaCanciones);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method for loading the list {@link CircularList} of songs from the
	 * serialization file
	 * 
	 * @return {@link CircularList}
	 */
	@SuppressWarnings("unchecked")
	public CircularList<Cancion> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			Object object = ois.readObject();
			ois.close();
			return (CircularList<Cancion>) object;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("El archivo estaba vacio");
			CircularList<Cancion> listaCanciones = new CircularList<Cancion>();
			saveData(listaCanciones);
			return listaCanciones;
		}
	}

}
