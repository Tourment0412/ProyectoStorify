package co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList;

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

public class ListaDoble<E> implements Iterable<E> {

	private NodoDoble<E> nodoPrimero;
	private NodoDoble<E> nodoUltimo;
	private int tamanio;

	public ListaDoble() {
		nodoPrimero = null;
		nodoPrimero = null;
		tamanio = 0;
	}

	// Metodos basicos

	// Agregar al inicio de la lista
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

	// Agregar al final de la lista
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
	
	public void agregar(E valor) {
		agregarfinal(valor);
	}

	/**
	 * Agrega un valor en la lista en una posici�n espec�fica
	 * 
	 * @param indice �ndice donde se va a guardar el dato
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

	// Obtener Nodo el valor de un Nodo
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

	// Verificar si indice es valido
	private boolean indiceValido(int indice) {
		if (indice >= 0 && indice < tamanio) {
			return true;
		}
		throw new RuntimeException("�ndice no v�lido");
	}

	// Verificar si la lista esta vacia
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

	public void imprimirAtras() {

		for (NodoDoble<E> aux = nodoUltimo; aux != null; aux = aux.getAnteriorNodo()) {
			System.out.print(aux.getValorNodo() + "\t");
		}
		System.out.println();

	}

	// Eliminar dado el valor de un nodo
	/**
	 * Elimina un elemento de la lista
	 * 
	 * @param dato dato a eliminar
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

	// Elimina el primer nodo de la lista
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

		throw new RuntimeException("Lista vac�a");
	}

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

		throw new RuntimeException("Lista vac�a");
	}

	/**
	 * Devuelve el Nodo de una lista dada su posici�n
	 * 
	 * @param indice �ndice para obtener el Nodo
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
	 * 
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
	 * Cambia el valor de un nodo dada su posici�n en la lista
	 * 
	 * @param indice posici�n donde se va a cambiar el dato
	 * @param nuevo  nuevo valor por el que se actualizar� el nodo
	 */
	public void modificarNodo(int indice, E nuevo) {

		if (indiceValido(indice)) {
			NodoDoble<E> nodo = obtenerNodo(indice);
			nodo.setValorNodo(nuevo);
		}

	}

	/**
	 * Retorna la primera posici�n donde est� guardado el dato
	 * 
	 * @param dato valor a buscar dentro de la lista
	 * @return primera posici�n del dato
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
	 * Devuelve un elemento de la lista dado su �ndice
	 * 
	 * @param indice �ndice de la lista
	 * @return dato guardado en el �ndice especificado
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
	@Override
	public Iterator<E> iterator() {

		return new IteradorListaDoble(nodoPrimero);
	}

	protected class IteradorListaDoble implements Iterator<E> {

		private NodoDoble<E> nodo;
		private int posicion;

		/**
		 * Constructor de la clase Iterador
		 * 
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
		 * Posici�n actual de la lista
		 * 
		 * @return posici�n
		 */
		public int getPosicion() {
			return posicion;
		}

	}

	// Punto 6
	public void imprimirHaciaAtras() {

		for (NodoDoble<E> aux = nodoUltimo; aux != null; aux = aux.getAnteriorNodo()) {
			System.out.print(aux.getValorNodo() + "\t");
		}
		System.out.println();

	}

	// Metodos get y set de la clase ListaSimple

	public NodoDoble<E> getNodoPrimero() {
		return nodoPrimero;
	}

	public void setNodoPrimero(NodoDoble<E> nodoPrimero) {
		this.nodoPrimero = nodoPrimero;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public NodoDoble<E> getNodoUltimo() {
		return nodoUltimo;
	}

	public void setNodoUltimo(NodoDoble<E> nodoUltimo) {
		this.nodoUltimo = nodoUltimo;
	}
	
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

	public CircularList<E> toCircularList() {
		CircularList<E> obtainedList=new CircularList<E>();
		NodoDoble<E> nodo=nodoPrimero;
		while(nodo!=null) {
			obtainedList.add(nodo.getValorNodo());
			nodo=nodo.getSiguienteNodo();
		}
		return obtainedList;
	}
	

	

}
