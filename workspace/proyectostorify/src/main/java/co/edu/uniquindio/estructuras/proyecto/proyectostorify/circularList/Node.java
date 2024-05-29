package co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node<E> implements Serializable {
	
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
