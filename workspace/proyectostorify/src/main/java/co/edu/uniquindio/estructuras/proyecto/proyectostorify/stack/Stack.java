package co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack;

import java.util.NoSuchElementException;

public class Stack<E> {

	public SimpleNode<E> head;
	public int size;

	public Stack() {
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Obtienede el nodo de la cima
	 * @return Nodo de la cima
	 */
	public SimpleNode<E> getHead() {
		return head;
	}
	
	/**
	 * Cambia el nodo de la cima
	 * @param head Nuevo nodo de la cima
	 */
	public void setHead(SimpleNode<E> head) {
		this.head = head;
	}
	
	/**
	 * Obtiene el tamanio de la pila
	 * @return Tamanio de la pila
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Cambia el tamanio de la pila
	 * @param size Nuevo tamanio de la pila
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Agrega unn valor en la cima de la pila
	 * @param value Valor a agregar
	 * @param action Accion relacionada al valor
	 */
	public void push(E value, String action) {
		SimpleNode<E> node = new SimpleNode<E>(value,action);
		if (head == null) {
			head = node;
			size++;
		} else {
			node.setNext(head);
			head=node;
			size++;
		}
	}
	
	/**
	 * Saca el valor de la cima
	 * @return Valor de la cima
	 */
	public E pop() {
		if (isEmpty())
			throw new NoSuchElementException("");
		E value = head.getValue();
		head = head.getNext();
		return value;
	}
	
	/**
	 * Pasa la pila en forma de cadena
	 * @return Cadena resultante
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Stack<E> aux = new Stack<E>();
		sb.append('[');
		while (!this.isEmpty()) {
			E value = this.pop();
			sb.append(value);
			sb.append(',');
			aux.push(value,"");
		}
		sb.append(']');
		while (!aux.isEmpty()) {
			this.push(aux.pop(),"");
		}
		return sb.toString();
	}
	
	/**
	 * Pasa las acciones en una cadena
	 * @return Cadena resultante
	 */
	public String toStringActions() {
		String msj="[";
		SimpleNode<E> node=head;
		if (node!=null) {
			msj+=node.getAction();
			node=node.getNext();
			while (node!=null) {
				msj+=","+node.getAction();
				node=node.getNext();
			}
		}
		msj+="]";
		return msj;
	}
	
	/**
	 * Verifica si la pila esta vacio
	 * @return La pila esta vacio o no
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * Obtiene el valor de la cima de la pila sin sacarlo
	 * @return Valor de la cima
	 */
	public E peek() {
		E value;
		if (head == null) {
			throw new NoSuchElementException("");
		} else {
			value = head.getValue();
		}
		return value;
	}
	
	/**
	 * Obtiene la accion del valor de la cima de la pila
	 * @return Acciond e la cima de a pila
	 */
	public String headAction() {
		return head.getAction();
	}
	
	/**
	 * Clona la pila
	 * @return Clon o copia de la pila
	 */
	public Stack<E> clone() {
		Stack<E> pila=new Stack<E>();
		SimpleNode<E> node=head;
		SimpleNode<E> auxiliaryHead=null;
		SimpleNode<E> previusNode=null;
		SimpleNode<E> auxiliaryNode=null;
		if (head!=null) {
			auxiliaryHead=new SimpleNode(node.getValue(),node.getAction());
			auxiliaryNode=auxiliaryHead;
			node=node.getNext();
		}
		while (node!=null) {
			previusNode=auxiliaryNode;
			auxiliaryNode=new SimpleNode(node.getValue(),node.getAction());
			previusNode.setNext(auxiliaryNode);
			node=node.getNext();
		}
		pila.setHead(auxiliaryHead);
		return pila;
	}
	
	/**
	 * Verifica si la pila contiene un valor
	 * @param value Valor a verificar
	 * @return Si la pila tiene el valor o no
	 */
	public boolean contains(E value) {
		SimpleNode<E> node=head;
		while (node!=null) {
			if (value.equals(node.getValue())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimmina los elementos de la pila
	 */
	public void clear() {
		head=null;
	}

}
