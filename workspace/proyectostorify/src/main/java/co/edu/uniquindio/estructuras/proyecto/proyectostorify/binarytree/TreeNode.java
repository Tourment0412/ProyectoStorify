package co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class TreeNode <E extends Comparable<E>> {
	
	private E value;
	private TreeNode <E>left;
	private TreeNode<E> right;
	
	/**
	 * 
	 * @param value
	 */
	public TreeNode (E value) {
		this.value= value;
		this.left=null;
		this.right=null;
	}	
	
}
