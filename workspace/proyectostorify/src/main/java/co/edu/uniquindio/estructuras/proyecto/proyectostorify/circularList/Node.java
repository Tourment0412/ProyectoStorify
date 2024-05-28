package co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node<E> {
	
	private E value;
	private Node<E> next;
	
	/**
	 * Metodo constructor
	 * @param value Valor del nodo
	 */
	public Node(E value) {
		super();
		this.value = value;
	}

}
