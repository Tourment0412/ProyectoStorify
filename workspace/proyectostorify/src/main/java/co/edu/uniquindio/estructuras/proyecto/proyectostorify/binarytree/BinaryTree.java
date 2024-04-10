package co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree;

import java.util.NoSuchElementException;

public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;
	// Talvez llegar a poner el size o algo asi

	public BinaryTree() {
		this.root = null;
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	public void setRoot(TreeNode<E> root) {
		this.root = root;
	}

	public E peek() {
		if (root == null) {
			throw new NoSuchElementException("");
		} else {
			return root.getValue();
		}
	}

	// Para los metodos de añadir es obligatoriamente necesario que las clases a
	// guardar implementen un comparable

	public void add(E value) {
		root = addRecursive(this.root, value);
	}

	private TreeNode<E> addRecursive(TreeNode<E> current, E value) {
		if (current == null) {
			return new TreeNode<E>(value);
		}
		if (value.compareTo(current.getValue()) < 0) {
			current.setLeft(addRecursive(current.getLeft(), value));
		} else if (value.compareTo(current.getValue()) > 0) {
			current.setRight(addRecursive(current.getRight(), value));
		} else {
			return current;
		}
		// Para cuando ya salga de las recursividades poder enviarlo a que se establezca
		// el root denuevo
		return current;
	}

	// Los recorridos son Inorder, Preoder, y Postorder

	// Recorrdio Inorder => hijoizquierdo, raiz, hijo derecho

	// Recorrdio Postorder => hijoizquierdo, hijo derecho, raiz

	// Recorrdio Preorder => raiz, hijo izquierdo, hijo derecho
	public void preorder() {
		preorderAux(this.root);
	}

	/*
	 * Aca se imprimira la raiz, luego todos los de la izquierda primero y luego
	 * todos los de la derecha Incluso las subramas izquierdas se imprimeran y luego
	 * sus derechas y ya lueego pasara a la parte derecha y de la parte derecha,
	 * todas las izquierdas y luego las derechas
	 */
	private void preorderAux(TreeNode<E> current) {
		if (current == null) {
			return;
		}
		System.out.print(current.getValue()+", ");
		preorderAux(current.getLeft());
		preorderAux(current.getRight());
	}

	public void inorder() {
		inorderAux(this.root);
	}

	private void inorderAux(TreeNode<E> current) {
		if (current == null) {
			return;

		}
		inorderAux(current.getLeft());
		System.out.print(current.getValue()+", ");
		inorderAux(current.getRight());

	}
	
	public void postorder() {
		postorderAux(this.root);
	}

	private void postorderAux(TreeNode<E> current) {
		if (current == null) {
			return;

		}
		postorderAux(current.getLeft());
		postorderAux(current.getRight());
		System.out.print(current.getValue()+", ");

	}

}