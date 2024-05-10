package co.edu.uniquindio.estructuras.proyecto.proyectostorify.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cuenta;
import lombok.Getter;

public class CuentasDao {
	/**
	 * variable for singleton of the class
	 */
	private static CuentasDao instance;

	/**
	 * Constant indicating the path of the persistence file
	 */
	@Getter
	private static final String RUTA = "src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/data/cuentas.dat";

	/**
	 * Method to return the instance of the class in memory, if it does not exist.
	 * 
	 * @return instance
	 */
	public static CuentasDao getInstance() {
		if (instance == null) {
			instance = new CuentasDao();
		}
		return instance;
	}

	/**
	 * Method for saving the list {@link HashMap} of accounts (users) in the
	 * serialization file
	 * 
	 * @param lstCuentas
	 */
	public void saveData(HashMap<String, Cuenta> lstCuentas) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(lstCuentas);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for loading the list (map) of accounts from the serialization file
	 * 
	 * @return {@link HashMap}
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Cuenta> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			Object objeto = ois.readObject();
			ois.close();
			return (HashMap<String, Cuenta>) objeto;

		} catch (IOException | ClassNotFoundException e) {
			HashMap<String, Cuenta> hashmap = new HashMap<String, Cuenta>();
			saveData(hashmap);
			return hashmap;
		}

	}
}
