package co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree;

public class TreeNode <E extends Comparable<E>> {
	
	private E value;
	private TreeNode <E>left;
	private TreeNode<E> right;
	
	public TreeNode (E value) {
		this.value= value;
		this.left=null;
		this.right=null;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public TreeNode<E> getLeft() {
		return left;
	}


	public TreeNode<E> getRight() {
		return right;
	}

	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}

	public void setRight(TreeNode<E> right) {
		this.right = right;
	}

	
	
	
	
	
}
