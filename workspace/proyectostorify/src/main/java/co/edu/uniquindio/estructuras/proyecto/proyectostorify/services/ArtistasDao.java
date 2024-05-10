package co.edu.uniquindio.estructuras.proyecto.proyectostorify.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Artista;
import lombok.Getter;

public class ArtistasDao {
	/**
	 * variable for singleton of the class
	 */
	private static ArtistasDao instance;
	/**
	 * Constant indicating the path of the persistence file
	 */
	@Getter
	private static final String RUTA = "src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/data/artistas.dat";

	/**
	 * Method to return the instance of the class in memory, if it does not exist.
	 * 
	 * @return instance
	 */
	public ArtistasDao getInstance() {
		if (instance == null) {
			instance = new ArtistasDao();
		}
		return instance;
	}

	/**
	 * Method for saving the list {@link BinaryTree} of artists in the serialization
	 * file
	 * 
	 * @param lstArtistas
	 */
	public void saveData(BinaryTree<Artista> lstArtistas) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(lstArtistas);
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for loading the list {@link BinaryTree} of artists from the
	 * serialization file
	 * 
	 * @return {@link BinaryTree}
	 */
	@SuppressWarnings("unchecked")
	public BinaryTree<Artista> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			Object object = ois.readObject();
			ois.close();
			return (BinaryTree<Artista>) object;

		} catch (IOException | ClassNotFoundException e) {
			BinaryTree<Artista> artistas = new BinaryTree<Artista>();
			saveData(artistas);
			return artistas;
		}

	}

}
