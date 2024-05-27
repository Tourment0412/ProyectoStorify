package co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack;

public class SimpleNode<E> {

	private E value;
	private String action;
	private SimpleNode<E> next;

	public SimpleNode(E valor, String action) {
		this.value = valor;
		this.next = null;
		this.action = action;
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
	 * @param value Nuevo valor del nodo
	 */
	public void setValue(E value) {
		this.value = value;
	}
	
	/**
	 * Obtiene el nodo
	 * @return Nodo
	 */
	public SimpleNode<E> getNext() {
		return next;
	}
	
	/**
	 * Cambia el nodo
	 * @param next Nuevo nodo
	 */
	public void setNext(SimpleNode<E> next) {
		this.next = next;
	}
	
	/**
	 * Obtiene la accion del nodo
	 * @return Accion del nodo
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Cambia la accion del nodo
	 * @param action Nueva accion del nodo
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * Obtiene la clase en forma de cadena
	 */
	@Override
	public String toString() {
		return next != null ? String.format("%s, %s", value, next) : value.toString();
	}

}
