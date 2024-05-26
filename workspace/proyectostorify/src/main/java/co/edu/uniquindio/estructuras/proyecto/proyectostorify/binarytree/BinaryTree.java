package co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree;

import java.util.NoSuchElementException;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;

public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;
	// Talvez llegar a poner el size o algo asi
	
	/**
	 * 
	 */
	public BinaryTree() {
		this.root = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public TreeNode<E> getRoot() {
		return root;
	}
	
	/**
	 * 
	 * @param root
	 */
	public void setRoot(TreeNode<E> root) {
		this.root = root;
	}
	
	/**
	 * 
	 * @return
	 */
	public E peek() {
		if (root == null) {
			throw new NoSuchElementException("");
		} else {
			return root.getValue();
		}
	}

	// Para los metodos de añadir es obligatoriamente necesario que las clases a
	// guardar implementen un comparable

	
	/**
	 * 
	 * @param value
	 */
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
	
	/**
	 * 
	 */
	public void preorder() {
		preorderAux(this.root);
	}

	/*
	 * Aca se imprimira la raiz, luego todos los de la izquierda primero y luego
	 * todos los de la derecha Incluso las subramas izquierdas se imprimeran y luego
	 * sus derechas y ya lueego pasara a la parte derecha y de la parte derecha,
	 * todas las izquierdas y luego las derechas
	 */
	
	/**
	 * 
	 * @param current
	 */
	private void preorderAux(TreeNode<E> current) {
		if (current == null) {
			return;
		}
		System.out.print(current.getValue()+", ");
		preorderAux(current.getLeft());
		preorderAux(current.getRight());
	}

	
	/**
	 * 
	 */
	public void inorder() {
		inorderAux(this.root);
	}
	
	/**
	 * 
	 * @param current
	 */
	private void inorderAux(TreeNode<E> current) {
		if (current == null) {
			return;

		}
		inorderAux(current.getLeft());
		System.out.print(current.getValue()+", ");
		inorderAux(current.getRight());

	}
	
	/**
	 * 
	 */
	public void postorder() {
		postorderAux(this.root);
	}

	
	/**
	 * 
	 * @param current
	 */
	private void postorderAux(TreeNode<E> current) {
		if (current == null) {
			return;

		}
		postorderAux(current.getLeft());
		postorderAux(current.getRight());
		System.out.print(current.getValue()+", ");

	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public E search(E value) {
	    return searchRecursive(root, value);
	}

	
	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	private E searchRecursive(TreeNode<E> current, E value) {
	    if (current == null) {
	        return null;
	    }
	    if (current.getValue().equals(value)) {
	        return current.getValue();
	    }
	    E leftResult = searchRecursive(current.getLeft(), value);
	    if (leftResult != null) {
	        return leftResult;
	    }
	    return searchRecursive(current.getRight(), value);
	}
	
	/**
	 * 
	 */
	public void clear() {
		root=null;
	}
	
	/**
	 * 
	 * @return
	 */
	public CircularList<E> toCircularList() {
		CircularList<E> eLementsList=new CircularList<E>();
		if (root!=null) {
			toCircularList(eLementsList,root);
		}
		return eLementsList;
	}
	
	/**
	 * 
	 * @param elements
	 * @param node
	 */
	public void toCircularList(CircularList<E> elements,TreeNode<E> node) {
		if (node.getLeft()!=null) {
			toCircularList(elements,node.getLeft());
		}
		if (node.getRight()!=null) {
			toCircularList(elements,node.getRight());
		}
		elements.add(node.getValue());
	}
	
	
	/**
	 * 
	 * @param value
	 */
	public void remove(E value) {
		if (root.getValue().compareTo(value)==0) {
			if (root.getLeft()==null && root.getRight()==null) {
				root=null;
			} else if (root.getLeft()==null || root.getRight()==null) {
				if (root.getLeft()==null) {
					root=root.getRight();
				} else {
					root=root.getLeft();
				}
			} else {
				TreeNode<E> originalRoot=root;
				root=root.getLeft();
				addToArbol(this,originalRoot.getRight());
			}
		} else {
			removeSearch(value,root,null);
		}
	}
	
	/**
	 * 
	 * @param node
	 * @param previusNode
	 */
	public void removeLeaf(TreeNode<E> node,TreeNode<E> previusNode) {
		if (previusNode.getLeft()==node) {
			previusNode.setLeft(null);
		} if (previusNode.getRight()==node) {
			previusNode.setRight(null);
		}
	}
	
	/**
	 * 
	 * @param node
	 * @param previusNode
	 */
	public void removeFather1(TreeNode<E> node,TreeNode<E> previusNode) {
		if (previusNode.getLeft()==node) {
			if (node.getLeft()==null) {
				previusNode.setLeft(node.getRight());
			} else if (node.getRight()==null) {
				previusNode.setLeft(node.getLeft());
			}
		}
		if (previusNode.getRight()==node) {
			if (node.getLeft()==null) {
				previusNode.setRight(node.getRight());
			} else if (node.getRight()==null) {
				previusNode.setRight(node.getLeft());
			}
		}
	}
	
	/**
	 * 
	 * @param node
	 * @param previusNode
	 */
	public void removeFather2(TreeNode<E> node,TreeNode<E> previusNode) {
		if (previusNode.getLeft()==node) {
			previusNode.setLeft(null);
		} if (previusNode.getRight()==node) {
			previusNode.setRight(null);
		}
		addToArbol(this,node.getLeft());
		addToArbol(this,node.getRight());
	}
	
	/**
	 * 
	 * @param tree
	 * @param inicialNode
	 */
	public void addToArbol(BinaryTree<E> tree,TreeNode<E> inicialNode) {
		if (inicialNode!=null) {
			addToArbol(tree,inicialNode.getLeft());
			tree.add(inicialNode.getValue());
			addToArbol(tree,inicialNode.getRight());
		}
	}
	
	/**
	 * 
	 * @param value
	 * @param node
	 * @param previusNode
	 */
	public void removeSearch(E value,TreeNode<E> node,TreeNode<E> previusNode) {
		if (node!=null) {
			if (value.compareTo(node.getValue())<0) {
				removeSearch(value,node.getLeft(),node);
			} else if (value.compareTo(node.getValue())>0) {
				removeSearch(value,node.getRight(),node);
			} else {
				if (node.getLeft()==null && node.getRight()==null) {
					removeLeaf(node,previusNode);
				} else if (node.getLeft()==null || node.getRight()==null) {
					removeFather1(node,previusNode);
				} else {
					removeFather2(node,previusNode);
				}
			}
		}
	}

}
