package co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack;

import java.util.NoSuchElementException;

public class Stack<E> {

	public SimpleNode<E> head;
	public int size;

	public Stack() {
		this.head = null;
		this.size = 0;
	}

	public SimpleNode<E> getHead() {
		return head;
	}

	public void setHead(SimpleNode<E> head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

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

	public E pop() {
		if (isEmpty())
			throw new NoSuchElementException("");
		E value = head.getValue();
		head = head.getNext();
		return value;
	}

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

	public boolean isEmpty() {
		return head == null;
	}

	public E peek() {
		E value;
		if (head == null) {
			throw new NoSuchElementException("");
		} else {
			value = head.getValue();
		}
		return value;
	}
	
	public String headAction() {
		return head.getAction();
	}
	
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
	
	public boolean contains(E value) {
		SimpleNode<E> node=head;
		while (node!=null) {
			if (value.equals(node.getValue())) {
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		head=null;
	}

}
