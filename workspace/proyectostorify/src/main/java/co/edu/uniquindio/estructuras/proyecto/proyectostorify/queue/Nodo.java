package co.edu.uniquindio.estructuras.proyecto.proyectostorify.queue;

public class Nodo<E> {

	Nodo<E> nextNode;
	E value;

	/**
	 * Metodo constructor
	 * @param valorNodo Valor del nodo
	 */
	public Nodo(E valorNodo) {
		this.value = valorNodo;
		nextNode = null;
	}

	/**
	 * Obtiene el siguiente nodo
	 * @return Siguiente nodo
	 */
	public Nodo getNextNode() {
		return nextNode;
	}

	
	/**
	 * Cambia el siguiente nodo
	 * @param siguienteNodo Nuevo siguiente nodo
	 */
	public void setNextNode(Nodo siguienteNodo) {
		this.nextNode = siguienteNodo;
	}

	/**
	 * Obtiene el valor del nodo
	 * @return Valor del nodo
	 */
	public E getValue() {
		return value;
	}

	/**
	 * Cambia el valor del nodo
	 * @param valorNodo Nuevo valor del nodo
	 */
	public void setValue(E valorNodo) {
		this.value = valorNodo;
	}
	
	

}
