package co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.TreeNode;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Artista;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CircularList<E> implements Iterable<E>,Collection<E>{
	
	private Node<E> head; 
	private Node<E> endNode;
	private int size;
	
	/**
	 * Agrega un elemento a la lista
	 * @param value Valor a agregar
	 */
	public boolean add(E value) {
		try {
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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Obtiene un valor indicando su posicion
	 * @param index Posicion
	 * @return Valor de la posicion dada
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
	 * Elimina un elemento de la lista
	 * @param value Elemento a remover
	 * @return Si elimino el elemento o no
	 */
	public boolean remove(Object value) {
		E valueToRemove=(E)value;
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
	 * Elimina la cabeza
	 */
	public void removeHead() {
		endNode.setNext(head.getNext());
		head=head.getNext();
	}
	
	
	/**
	 * Elimina un elemento en una posicion indicada
	 * @param index Posicion
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
	 * Obtiene el nodo de la posicion indicada
	 * @param index Poscion indicada
	 * @return Nodo obtenido
	 */
	private Node<E> getNode(int index) {
	    Node<E> node = head;
	    for (int i = 0; i < index; i++) {
	        node = node.getNext();
	    }
	    return node;
	}
	
	/**
	 * Obtiene la posicion de un valor
	 * @param value Valor
	 * @return POsicion del valor
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
	 * Obtiene el tamanio de la lista
	 * @return Tamanio de la lista
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Cambia el valor de una posicion indicada
	 * @param index Posicion
	 * @param value Nuevo valor
	 */
	public void set(int index, E value) {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
	    Node<E> node = head;
	    // Recorre la lista hasta el índice deseado
	    for (int cont = 0; cont < index; cont++) {
	        node = node.getNext();
	    }
	    // Establece el nuevo valor en el nodo encontrado
	    node.setValue(value);
	}
	
	/**
	 * Verifica si contiene un valor indicado la lista
	 * @param element Valor indicado
	 * @return Si continene el valor o no
	 */
	public boolean contains(Object element) {
		E value=(E)element;
		for (E actualValue : this) {
			if (actualValue==value || value.equals(actualValue)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si la lista esta vacia o no
	 * @return Si esta vacia o no
	 */
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * Elimina los elementos de la lista
	 */
	public void clear() {
		head=endNode=null;
		size=0;
	}
	
	/**
	 * Convierte la lista en una cadena
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
	 * Filtra los elementos de la lista
	 * @param comparation Metodo de filtracion
	 * @return Elementos filtrados
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
	 * Agrega los elementos que se le indiquen
	 * @param list Elementos a agregar
	 */
	public void setUnion(CircularList<E> list) {
		
		for (E element : list) {
			if (!this.contains(element)) {
				this.add(element);
			}
		}
	}
	
	/**
	 * Reliazala  insterseccion de esta lista y otra
	 * @param list Lista con la cual se va a realizar la insterseccion
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
	 * Obtiene la union de esta lista y otra
	 * @param list Lista con la cual se va a realizar la union
	 * @return Union de las listas
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
	 * Obtiene la interseccion de esta lista y otra
	 * @param list Lista con la cual se va a realizar la interseccion
	 * @return Interseccion de las listas
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
	 * Crea una copia de la lista
	 */
	public CircularList<E> clone() {
		CircularList<E> obtaindedList=new CircularList<E>();
		for (E element : this) {
			obtaindedList.add(element);
		}
		return obtaindedList;
	}
	
	/**
	 * Ordena la lista
	 * @param comparador Comparacion por la cual se va a ordenar
	 */
	public void ordenar(Comparator<E> comparador) {
	    if (comparador == null) {
	        throw new IllegalArgumentException("El comparador no puede ser nulo");
	    }
	    

	    int n = size();
	    // Recorre todos los elementos excepto el último
	    for (int i = 0; i < n - 1; i++) {
	        int minIndex = i; // Índice del elemento mínimo
	        // Encuentra el menor elemento en la sublista no ordenada
	        for (int j = i + 1; j < n; j++) {
	            if (comparador.compare(get(j), get(minIndex)) < 0) {
	                minIndex = j;
	            }
	        }
	        // Intercambia el elemento mínimo con el primer elemento no ordenado
	        if (minIndex != i) {
	            E temp = get(i);
	            set(i, get(minIndex));
	            set(minIndex, temp);
	        }
	    }
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

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E e : c) {
			if (!this.contains(e)) {
				add(e);
			}
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

}