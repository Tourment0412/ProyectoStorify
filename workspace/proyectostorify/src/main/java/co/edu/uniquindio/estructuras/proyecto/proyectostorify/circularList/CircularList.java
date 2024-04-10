package co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList;

import java.util.Iterator;

public class CircularList<E> implements Iterable<E>{
	
	private Node<E> head; 
	private Node<E> endNode;
	private int size;
	
	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}

	public Node<E> getEndNode() {
		return endNode;
	}

	public void setEndNode(Node<E> endNode) {
		this.endNode = endNode;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void add(E value) {
		if (head==null) {
			head=endNode=new Node<E>(value);
			head.setNext(head);
			endNode.setNext(head);
		} else {
			Node<E> node=new Node<E>(value);
			endNode.setNext(node);
			node.setNext(head);
			endNode=node;
		}
		size++;
	}
	
	
	public void remove(E value) {
		Node<E> previusNode=endNode;
		Node<E> actualNode=head;
		for (E actualValue : this) {
			if (value.equals(actualValue)) {
				previusNode.setNext(actualNode.getNext());
				break;
			}
			previusNode=actualNode;
			actualNode=actualNode.getNext();
		}
	}
	
	public void removeHead() {
		endNode.setNext(head.getNext());
		head=head.getNext();
	}
	
	public void remove(int index) {
		if (index<0 || index>=size) {
			throw new IndexOutOfBoundsException();
		}
		int cont=0;
		Node<E> previusNode=endNode;
		Node<E> actualNode=head;
		while (true) {
			if (cont==index) {
				previusNode.setNext(actualNode.getNext());
				break;
			}
			previusNode=actualNode;
			actualNode=actualNode.getNext();
			cont++;
		}
		size--;
	}
	
	public int indexOf(E value) {
		int index=-1;
		int cont=0;
		for (E actualValue : this) {
			if (actualValue==value || value.equals(actualValue)) {
				index=cont;
				break;
			}
			cont++;
		}
		return index;
	}
	
	public int size() {
		return size;
	}
	
	public void set(int index,E value) {
		if (index<0 || index>=size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> node=head;
		int cont;
		while (node!=null) {
			
			node=node.getNext();
		}
	}
	
	public boolean contains(E value) {
		for (E actualValue : this) {
			if (actualValue==value || value.equals(actualValue)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public void clear() {
		head=endNode=null;
	}
	
	@Override
	public String toString() {
		String msj="[";
		for (E element : this) {
			if (head.getValue()==element) {
				msj+=element;
			} else {
				msj+=","+element;
			}
		}
		msj+="]";
		return msj;
	}
	
	public CircularList<E> filter(Comparation<E> comparation) {
		CircularList<E> listObtained=new CircularList<E>();
		for (E actualValue : this) {
			if (comparation.comparation(actualValue)) {
				listObtained.add(actualValue);
			}
		}
		return listObtained;
	}
	
	public void setUnion(CircularList<E> list) {
		for (E element : list) {
			if (!this.contains(element)) {
				list.add(element);
			}
		}
	}
	
	public void setIntersection(CircularList<E> list) {
		CircularList<E> obtaindedList=new CircularList<E>();
		for (E element : this) {
			if (list.contains(element)) {
				obtaindedList.add(element);
			}
		}
		head=obtaindedList.getHead();
		endNode=obtaindedList.getEndNode();
	}
	
	public CircularList<E> getUnion(CircularList<E> list) {
		CircularList<E> obtaindedList=new CircularList<E>();
		for (E element : this) {
			obtaindedList.add(element);
		}
		for (E element : list) {
			if (!obtaindedList.contains(element)) {
				obtaindedList.add(element);
			}
		}
		return obtaindedList;
	}
	
	public CircularList<E> getIntersection(CircularList<E> list) {
		CircularList<E> obtaindedList=new CircularList<E>();
		for (E element : this) {
			if (list.contains(element)) {
				obtaindedList.add(element);
			}
		}
		return obtaindedList;
	}
	
	public CircularList<E> clone() {
		CircularList<E> obtaindedList=new CircularList<E>();
		for (E element : this) {
			obtaindedList.add(element);
		}
		return obtaindedList;
	}

	@Override
	public Iterator<E> iterator() {
		CircularListIterator<E> iterator=new CircularListIterator<E>(head);
		return iterator;
	}
	
	public class CircularListIterator<E> implements Iterator {

		Node<E> node;
		Node<E> firtsElement;
		boolean firtsElementOut=false;
		
		public CircularListIterator(Node<E> node) {
			this.node=firtsElement=node;
		}
		
		@Override
		public boolean hasNext() {
			boolean hasNext=true;
			if ((firtsElement==node && firtsElementOut) || node==null) {
				hasNext=false;
			}
			return hasNext;
		}

		@Override
		public Object next() {
			E value=null;
			if (hasNext()) {
				value=node.getValue();
				node=node.getNext();
				firtsElementOut=true;
			}
			return value;
		}
	}
	
	@FunctionalInterface
	public interface Comparation<E> {
		boolean comparation(E value);
	}

}