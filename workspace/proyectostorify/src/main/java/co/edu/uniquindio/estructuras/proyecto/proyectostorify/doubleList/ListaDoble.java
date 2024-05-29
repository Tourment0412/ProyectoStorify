package co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;

/**
 *
 * Definici�n de la clase lista Simple de tipo Generics
 * 
 * @param <E>
 *
 **/

public class ListaDoble<E> implements Iterable<E>,Collection<E>,Serializable {

	private NodoDoble<E> nodoPrimero;
	private NodoDoble<E> nodoUltimo;
	private int tamanio;

	public ListaDoble() {
		nodoPrimero = null;
		nodoPrimero = null;
		tamanio = 0;
	}

	/**
	 * Agrega al inicio de la lista
	 * @param valorNodo Valor a agregar
	 */
	public void agregarInicio(E valorNodo) {

		NodoDoble<E> nuevoNodo = new NodoDoble<>(valorNodo);

		if (estaVacia()) {
			nodoPrimero = nodoUltimo = nuevoNodo;
		} else {
			nuevoNodo.setSiguienteNodo(nodoPrimero);
			nodoPrimero.setAnteriorNodo(nuevoNodo);
			nodoPrimero = nuevoNodo;
		}
		tamanio++;
	}

	/**
	 * Agrega al final de la lista
	 * @param valorNodo Valor a agregar
	 */
	public void agregarfinal(E valorNodo) {

		NodoDoble<E> nuevoNodo = new NodoDoble<>(valorNodo);

		if (estaVacia()) {
			nodoPrimero = nodoUltimo = nuevoNodo;
		} else {
			nuevoNodo.setAnteriorNodo(nodoUltimo);
			nodoUltimo.setSiguienteNodo(nuevoNodo);
			nodoUltimo = nuevoNodo;
		}
		tamanio++;
	}
	
	/**
	 * Agrega un valor a la lista
	 * @param valor Valor a agregar
	 */
	public void agregar(E valor) {
		agregarfinal(valor);
	}

	/**
	 * Agrega un valor en la lista en una posicion especifica
	 * @param indice Indice donde se va a guardar el dato
	 * @param nuevo  El valor a guardar
	 */
	public void agregar(E dato, int indice) {

		if (indiceValido(indice)) {

			if (indice == 0) {
				agregarInicio(dato);
			} else {
				NodoDoble<E> nuevo = new NodoDoble<>(dato);
				NodoDoble<E> actual = obtenerNodo(indice);

				nuevo.setSiguienteNodo(actual);
				nuevo.setAnteriorNodo(actual.getAnteriorNodo());
				actual.getAnteriorNodo().setSiguienteNodo(nuevo);
				actual.setAnteriorNodo(nuevo);

				tamanio++;
			}
		}
	}

	/**
	 * Borra completamente la Lista
	 */
	public void borrarLista() {
		nodoPrimero = nodoUltimo = null;
		tamanio = 0;
	}

	/**
	 * Obtiene el valor de un Nodo con a posicion indicada
	 * @param indice Indice del nodo
	 * @return Valor del nodo
	 */
	public E obtenerValorNodo(int indice) {

		NodoDoble<E> nodoTemporal = null;
		int contador = 0;

		if (indiceValido(indice)) {
			nodoTemporal = nodoPrimero;

			while (contador < indice) {

				nodoTemporal = nodoTemporal.getSiguienteNodo();
				contador++;
			}
		}

		if (nodoTemporal != null)
			return nodoTemporal.getValorNodo();
		else
			return null;
	}

	/**
	 *  Verifica si indice es valido
	 * @param indice Indice
	 * @return Si es valido o no
	 */
	private boolean indiceValido(int indice) {
		if (indice >= 0 && indice < tamanio) {
			return true;
		}
		throw new RuntimeException("Indice no valido");
	}

	/**
	 * Verifica si la lista esta vacia
	 * @return Si la lista esta vacia o no
	 */
	public boolean estaVacia() {
		// return(nodoPrimero == null)?true:false;
		return nodoPrimero == null && nodoUltimo == null;
	}

	/**
	 * Imprime en consola la lista enlazada
	 */
	public void imprimirLista() {

		NodoDoble<E> aux = nodoPrimero;

		while (aux != null) {
			System.out.print(aux.getValorNodo() + "\t");
			aux = aux.getSiguienteNodo();
		}

		System.out.println();
	}

	/**
	 * Imprime en consola la lista enlazada hacia atras
	 */
	public void imprimirAtras() {

		for (NodoDoble<E> aux = nodoUltimo; aux != null; aux = aux.getAnteriorNodo()) {
			System.out.print(aux.getValorNodo() + "\t");
		}
		System.out.println();

	}

	
	/**
	 * Elimina un elemento de la lista
	 * @param dato Dato a eliminar
	 * @return El dato que se elimina
	 */
	public E eliminar(E dato) {

		NodoDoble<E> nodo = buscarNodo(dato);

		if (nodo != null) {

			NodoDoble<E> previo = nodo.getAnteriorNodo();
			NodoDoble<E> siguiente = nodo.getSiguienteNodo();

			if (previo == null) {
				nodoPrimero = siguiente;
			} else {
				previo.setSiguienteNodo(siguiente);
			}

			if (siguiente == null) {
				nodoUltimo = previo;
			} else {
				siguiente.setAnteriorNodo(previo);
			}

			nodo = null;
			tamanio--;

			return dato;
		}

		throw new RuntimeException("El valor no existe");
	}

	/**
	 * Elimina el primer nodo de la lista
	 * @return Valor eliminado
	 */
	public E eliminarPrimero() {

		if (!estaVacia()) {
			NodoDoble<E> nodoAux = nodoPrimero;
			E valor = nodoAux.getValorNodo();
			nodoPrimero = nodoAux.getSiguienteNodo();

			if (nodoPrimero == null) {
				nodoUltimo = null;
			} else {
				nodoPrimero.setAnteriorNodo(null);
			}

			tamanio--;
			return valor;
		}

		throw new RuntimeException("Lista vacia");
	}

	/**
	 * Elimina el ultimo valor del nodo
	 * @return Valor eliminado
	 */
	public E eliminarUltimo() {

		if (!estaVacia()) {
			E valor = nodoUltimo.getValorNodo();
			NodoDoble<E> prev = obtenerNodo(tamanio - 2);
			nodoUltimo = prev;

			if (nodoUltimo == null) {
				nodoPrimero = null;
			} else {
				prev.setSiguienteNodo(null);
			}

			tamanio--;
			return valor;
		}

		throw new RuntimeException("Lista vacia");
	}

	/**
	 * Devuelve el Nodo de una lista dada su posicion
	 * @param indice Indice para obtener el Nodo
	 * @return Nodo objeto
	 */
	private NodoDoble<E> obtenerNodo(int indice) {

		if (indice >= 0 && indice < tamanio) {

			NodoDoble<E> nodo = nodoPrimero;

			for (int i = 0; i < indice; i++) {
				nodo = nodo.getSiguienteNodo();
			}

			return nodo;
		}

		return null;
	}

	/**
	 * Devuelve un nodo que contenga un dato espec�fico
	 * @param dato Dato a buscar
	 * @return Nodo
	 */
	private NodoDoble<E> buscarNodo(E dato) {

		NodoDoble<E> aux = nodoPrimero;

		while (aux != null) {
			if (aux.getValorNodo().equals(dato)) {
				return aux;
			}
			aux = aux.getSiguienteNodo();
		}

		return null;
	}

	/**
	 * Cambia el valor de un nodo dada su posicion en la lista
	 * @param indice Posicion donde se va a cambiar el dato
	 * @param nuevo Nuevo valor por el que se actualizara el nodo
	 */
	public void modificarNodo(int indice, E nuevo) {

		if (indiceValido(indice)) {
			NodoDoble<E> nodo = obtenerNodo(indice);
			nodo.setValorNodo(nuevo);
		}

	}

	/**
	 * Retorna la primera posicion donde esta guardado el dato
	 * @param dato Valor a buscar dentro de la lista
	 * @return Primera posicion del dato
	 */
	public int obtenerPosicionNodo(E dato) {

		int i = 0;

		for (NodoDoble<E> aux = nodoPrimero; aux != null; aux = aux.getSiguienteNodo()) {
			if (aux.getValorNodo().equals(dato)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	/**
	 * Devuelve un elemento de la lista dado su indice
	 * @param indice Indice de la lista
	 * @return Dato guardado en el indice especificado
	 */
	public E obtener(int indice) {

		if (indiceValido(indice)) {
			NodoDoble<E> n = obtenerNodo(indice);

			if (n != null) {
				return n.getValorNodo();
			}
		}

		return null;
	}

	// Punto 7
	/**
	 * Obtiene el iterador  de la lsita doble
	 */
	@Override
	public Iterator<E> iterator() {

		return new IteradorListaDoble(nodoPrimero);
	}

	protected class IteradorListaDoble implements Iterator<E> {

		private NodoDoble<E> nodo;
		private int posicion;

		/**
		 * Constructor de la clase Iterador
		 * @param aux Primer Nodo de la lista
		 */
		public IteradorListaDoble(NodoDoble<E> nodo) {
			this.nodo = nodo;
			this.posicion = 0;
		}

		@Override
		public boolean hasNext() {
			return nodo != null;
		}

		@Override
		public E next() {
			E valor = nodo.getValorNodo();
			nodo = nodo.getSiguienteNodo();
			posicion++;
			return valor;
		}

		public boolean hasPrevious() {
			return nodo != null;
		}

		public E previous() {
			E aux = nodo.getValorNodo();
			nodo = nodo.getAnteriorNodo();
			posicion--;
			return aux;
		}

		public int nextIndex() {
			return posicion;
		}

		public int previousIndex() {
			return posicion - 1;
		}

		public void remove() {
			if (nodo != null) {
				eliminar(nodo.getValorNodo());
			}
		}

		public void set(E e) {
			if (nodo != null) {
				nodo.setValorNodo(e);
			}
		}

		public void add(E e) {
			agregarfinal(e); 
		}

		/**
		 * Posicion actual de la lista
		 * @return posicion
		 */
		public int getPosicion() {
			return posicion;
		}

	}

	/**
	 * Obtiene el primer nodo
	 * @return Primer nodo
	 */
	public NodoDoble<E> getNodoPrimero() {
		return nodoPrimero;
	}

	/**
	 * Cambia el primer nodo
	 * @param nodoPrimero Nuevo primer nodo
	 */
	public void setNodoPrimero(NodoDoble<E> nodoPrimero) {
		this.nodoPrimero = nodoPrimero;
	}

	/**
	 * Obtiene el tamanio de la lista
	 * @return Tamanio de la lista
	 */
	public int getTamanio() {
		return tamanio;
	}

	/**
	 * Cambia el tamanio de la lista
	 * @param tamanio Nuevo tamanio de la lista
	 */
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	/**
	 * Obtiene el ultimo nodo
	 * @return Ultimo nodo
	 */
	public NodoDoble<E> getNodoUltimo() {
		return nodoUltimo;
	}

	/**
	 * Cambia el ultimo nodo
	 * @param nodoUltimo Nuevo ultimo nodo
	 */
	public void setNodoUltimo(NodoDoble<E> nodoUltimo) {
		this.nodoUltimo = nodoUltimo;
	}

	/**
	 * Obtiene la lista doble en forma de cadena
	 */
	public String toString() {
		String msj="[";
		NodoDoble<E> nodo=nodoPrimero;
		if (nodo!=null) {
			msj+=nodo.getValorNodo();
			nodo=nodo.getSiguienteNodo();
		}
		while(nodo!=null) {
			msj+=","+nodo.getValorNodo();
			nodo=nodo.getSiguienteNodo();
		}
		msj+="]";
		return msj;
	}

	/**
	 * Convierte la lista doble en una lista circular
	 * @return Lista circular resultante
	 */
	public CircularList<E> toCircularList() {
		CircularList<E> obtainedList=new CircularList<E>();
		NodoDoble<E> nodo=nodoPrimero;
		while(nodo!=null) {
			obtainedList.add(nodo.getValorNodo());
			nodo=nodo.getSiguienteNodo();
		}
		return obtainedList;
	}

	@Override
	public int size() {
		return getTamanio();
	}

	@Override
	public boolean isEmpty() {
		return estaVacia();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean add(E e) {
		try {
			agregar(e);
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean remove(Object o) {
		try {
			eliminar((E)o);
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public void clear() {
		borrarLista();
	}
	
}
