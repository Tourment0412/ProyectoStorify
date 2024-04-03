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

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public SimpleNode<E> getNext() {
		return next;
	}

	public void setNext(SimpleNode<E> next) {
		this.next = next;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return next != null ? String.format("%s, %s", value, next) : value.toString();
	}

}
