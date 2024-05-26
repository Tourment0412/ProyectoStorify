package co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList;

import java.util.Iterator;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.TreeNode;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Artista;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CircularList<E> implements Iterable<E>{
	
	private Node<E> head; 
	private Node<E> endNode;
	private int size;
	
	/**
	 * 
	 * @param value
	 */
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
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public E get(int index) {
		if (index>=size || 0>index) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		Node<E> node=head;
		int cont=0;
		while (cont!=size) {
			if (cont==index) {
				break;
			}
			cont++;
			node=node.getNext();
		}
		return node.getValue();
	}
	
	/**
	 * 
	 * @param valueToRemove
	 * @return
	 */
	public boolean remove(E valueToRemove) {
	    if (head == null || !contains(valueToRemove)) {
	        return false; // La lista está vacía o no contiene el elemento, no hay nada que eliminar
	    }
	    
	    if (head.getValue().equals(valueToRemove) && size==1) {
			setHead(null);
			setEndNode(null);
			size--;
			return true;
		}
	    
	    Node<E> prevNode = null;
	    Node<E> currentNode = head;
	    
	    while (currentNode != null ) {
	        if (currentNode.getValue().equals(valueToRemove)) {
	            if (prevNode == null) {
	                // El valor a eliminar está en el primer nodo
	                head = currentNode.getNext();
	                endNode.setNext(head);
	            } else {
	                // El valor a eliminar está en algún lugar intermedio o en el último nodo
	                prevNode.setNext(currentNode.getNext());
	                if (currentNode.getNext() == null) {
	                    endNode = prevNode; // Actualizar el endNode si se eliminó el último nodo
	                }
	            }
	            size--;
	            return true; // Se encontró y eliminó el valor
	        }
	        prevNode = currentNode;
	        currentNode = currentNode.getNext();
	    }
	    
	    return false; // No se encontró el valor en la lista
	}
	
	/**
	 * 
	 */
	public void removeHead() {
		endNode.setNext(head.getNext());
		head=head.getNext();
	}
	
	
	/**
	 * 
	 * @param index
	 */
	public void remove(int index) {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
	    
	    if (index == 0) {
	        head = head.getNext();
	        if (head == null) {
	            endNode = null;
	        }
	    } else {
	        Node<E> previusNode = getNode(index - 1);
	        Node<E> actualNode = previusNode.getNext();
	        previusNode.setNext(actualNode.getNext());
	        if (previusNode.getNext() == null) {
	            endNode = previusNode;
	        }
	    }
	    
	    size--;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	private Node<E> getNode(int index) {
	    Node<E> node = head;
	    for (int i = 0; i < index; i++) {
	        node = node.getNext();
	    }
	    return node;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 
	 * @param index
	 * @param value
	 */
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
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean contains(E value) {
		for (E actualValue : this) {
			if (actualValue==value || value.equals(actualValue)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * 
	 */
	public void clear() {
		head=endNode=null;
		size=0;
	}
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 * @param comparation
	 * @return
	 */
	public CircularList<E> filter(Comparation<E> comparation) {
		CircularList<E> listObtained=new CircularList<E>();
		for (E actualValue : this) {
			if (comparation.comparation(actualValue)) {
				listObtained.add(actualValue);
			}
		}
		return listObtained;
	}
	
	/**
	 * 
	 * @param list
	 */
	public void setUnion(CircularList<E> list) {
		
		for (E element : list) {
			if (!this.contains(element)) {
				this.add(element);
			}
		}
	}
	
	/**
	 * 
	 * @param list
	 */
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
	
	/**
	 * 
	 * @param list
	 * @return
	 */
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
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public CircularList<E> getIntersection(CircularList<E> list) {
		CircularList<E> obtaindedList=new CircularList<E>();
		for (E element : this) {
			if (list.contains(element)) {
				obtaindedList.add(element);
			}
		}
		return obtaindedList;
	}
	
	/**
	 * 
	 */
	public CircularList<E> clone() {
		CircularList<E> obtaindedList=new CircularList<E>();
		for (E element : this) {
			obtaindedList.add(element);
		}
		return obtaindedList;
	}
	
	/**
	 * 
	 */
	@Override
	public Iterator<E> iterator() {
		CircularListIterator<E> iterator=new CircularListIterator<E>(head);
		return iterator;
	}
	
	public class CircularListIterator<E> implements Iterator {

		Node<E> node;
		Node<E> firtsElement;
		boolean firtsElementOut=false;
		
		
		/**
		 * 
		 * @param node
		 */
		public CircularListIterator(Node<E> node) {
			this.node=firtsElement=node;
		}
		
		/**
		 * 
		 */
		@Override
		public boolean hasNext() {
			boolean hasNext=true;
			if ((firtsElement==node && firtsElementOut) || node==null) {
				hasNext=false;
			}
			return hasNext;
		}
		
		/**
		 * 
		 */
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