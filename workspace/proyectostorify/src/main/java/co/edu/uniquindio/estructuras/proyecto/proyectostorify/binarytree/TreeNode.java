package co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class TreeNode <E extends Comparable<E>> implements Serializable {
	
	private E value;
	private TreeNode <E>left;
	private TreeNode<E> right;
	
	/**
	 * Metodo constructor
	 * @param value Valor del nodo
	 */
	public TreeNode (E value) {
		this.value= value;
		this.left=null;
		this.right=null;
	}	
	
}
