package co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack;

import java.util.NoSuchElementException;

public class Pila<E> {

	public SimpleNode<E> head;
	public int size;

	public Pila() {
		this.head = null;
		this.size = 0;
	}

	public void push(E value) {
		SimpleNode<E> node = new SimpleNode<E>(value);
		if (head == null) {
			head = node;
			size++;
			return;
		}
		SimpleNode<E> auxiliar = head;
		head = node;
		head.setNext(auxiliar);
		size++;
	}

	public E pop() {
		if (estaVacia())
			throw new NoSuchElementException("");
		E value = head.getValue();
		head = head.getNext();
		return value;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Pila<E> aux = new Pila<E>();
		sb.append('[');
		while (!this.estaVacia()) {
			E value = this.pop();
			sb.append(value);
			sb.append(',');
			aux.push(value);
		}
		sb.append(']');
		while (!aux.estaVacia()) {
			this.push(aux.pop());
		}
		return sb.toString();
	}

	public boolean estaVacia() {
		return head == null;
	}

	public E cima() {
		E value;
		if (head == null) {
			throw new NoSuchElementException("");
		} else {
			value = head.getValue();
		}
		return value;
	}

}
