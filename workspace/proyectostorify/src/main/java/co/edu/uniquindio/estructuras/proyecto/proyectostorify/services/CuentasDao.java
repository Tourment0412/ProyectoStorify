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
	private static CuentasDao instance;
	@Getter
	private static final String RUTA= "src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/data/cuentas.dat";
	
	
	public static CuentasDao getInstance() {
		if(instance==null) {
			instance= new CuentasDao();
		}
		return instance;
	}
	
	public void saveData(HashMap<String, Cuenta> lstCuentas) {
		try(ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(lstCuentas);
			oos.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Cuenta> loadData(){
		try(ObjectInputStream ois= new ObjectInputStream(new FileInputStream(getRUTA()))){
			Object objeto= ois.readObject();
			ois.close();
			return (HashMap<String, Cuenta>) objeto;
			
		}catch(IOException | ClassNotFoundException e) {
			HashMap<String, Cuenta> hashmap= new HashMap<String, Cuenta>();
			saveData(hashmap);
			e.printStackTrace();
		}
		return null;
	}
}
