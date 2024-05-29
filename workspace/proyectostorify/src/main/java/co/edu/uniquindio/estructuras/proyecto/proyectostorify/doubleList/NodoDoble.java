package co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList;

import java.io.Serializable;

/**
 * Clase nodo aplicando Generics
 * 
 * 
 * 
 * **/


public class NodoDoble<T> implements Serializable {

	private NodoDoble<T> siguienteNodo;
	private NodoDoble<T> anteriorNodo;
	private T valorNodo;
	
	
	/**
	 * Constructor de la clase Nodo
	 * @param dato Elemento que se guarda en el Nodo
	 */
	public NodoDoble(T valorNodo) {
		this.valorNodo = valorNodo;
	}
	
	
	/**
	 * Constructor de la clase Nodo
	 * @param dato Elemento que se guarda en el Nodo
	 * @param siguiente Enlace al siguiente Nodo
	 */
	public NodoDoble(T dato, NodoDoble<T> siguiente,NodoDoble<T> anterior) {
		super();
		this.valorNodo = dato;
		this.siguienteNodo = siguiente;
		this.anteriorNodo = anterior;
	}
	

	//Metodos get y set de la clase Nodo
	
	/**
	 * Obtiene el siguiente nodo
	 * @return Siguiente nodo
	 */
	public NodoDoble<T> getSiguienteNodo() {
		return siguienteNodo;
	}

	/**
	 * Cambia el siguiente nodo
	 * @param siguienteNodo Nuuevo siguiente nodo
	 */
	public void setSiguienteNodo(NodoDoble<T> siguienteNodo) {
		this.siguienteNodo = siguienteNodo;
	}

	/**
	 * Obtiene el valor del nodo
	 * @return Valor del nodo
	 */
	public T getValorNodo() {
		return valorNodo;
	}

	/**
	 * Cambia el valor del nodo
	 * @param valorNodo Nuevo valor del nodo
	 */
	public void setValorNodo(T valorNodo) {
		this.valorNodo = valorNodo;
	}

	/**
	 * Obtiene el anterior nodo
	 * @return Anterior nodo
	 */
	public NodoDoble<T> getAnteriorNodo() {
		return anteriorNodo;
	}

	/**
	 * Cambia el anterior nodo
	 * @param anteriorNodo Nuevo nodo anterior
	 */
	public void setAnteriorNodo(NodoDoble<T> anteriorNodo) {
		this.anteriorNodo = anteriorNodo;
	}
	
	
	
}
