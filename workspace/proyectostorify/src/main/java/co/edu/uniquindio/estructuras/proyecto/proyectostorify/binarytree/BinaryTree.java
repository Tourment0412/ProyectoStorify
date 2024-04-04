package co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree;

import java.util.NoSuchElementException;

public class BinaryTree <E extends Comparable<E>>{
	
	private TreeNode<E> root;
	//Talvez llegar a poner el size o algo asi
	
	public BinaryTree () {
		this.root=null;
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	public void setRoot(TreeNode<E> root) {
		this.root = root;
	}
	
	public E peek() {
		if(root==null) {
			throw new NoSuchElementException("");
		}else {
			return root.getValue();
		}
	}
	
	//Para los metodos de añadir es obligatoriamente necesario que las clases a guardar implementen un comparable
	
	
	public void add(E value) {
		root= addRecursive(this.root,value);
	}

	private TreeNode<E> addRecursive(TreeNode<E> current, E value) {
		if(current==null) {
			return new TreeNode<E>(value);
		}
		if(value.compareTo(current.getValue())<0) {
			current.setLeft(addRecursive(current.getLeft(), value));
		}else if(value.compareTo(current.getValue())>0) {
			current.setRight(addRecursive(current.getRight(), value));
		}else {
			return current;
		}
		//Para cuando ya salga de las recursividades poder enviarlo a que se establezca el root denuevo
		return current;
	}
	
	//Los recorridos son Inorder, Preoder, y Postorder
	
	
}
