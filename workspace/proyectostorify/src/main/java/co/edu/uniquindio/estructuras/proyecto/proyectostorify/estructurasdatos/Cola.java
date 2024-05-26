package co.edu.uniquindio.estructuras.proyecto.proyectostorify.estructurasdatos;

import java.util.Comparator;

public class Cola<E> {
	
	private Nodo<E> primerNodo;
	private Nodo<E> ultimoNodo;
	private int tam;

	/**
	 * Constructor de la clase
	 */
	public Cola() {
		this.primerNodo = null;
		tam = 0;
	}
	
	/**
	 * eObtiene el primer nodo
	 * @return Primer nodo
	 */
	public Nodo getPrimerNodo() {
		return primerNodo;
	}


	/**
	 * Obtiene el tamanio 
	 * @return Tamanio
	 */
	public int getTam() {
		return tam;
	}

	/**
	 * Cambia el tamanio
	 * @param tamano Nuevo tamanio
	 */
	public void setTam(int tamano) {
		this.tam = tamano;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Nodo<E> getUltimoNodo() {
		return ultimoNodo;
	}

	
	/**
	 * 
	 * @param ultimoNodo
	 */
	public void setUltimoNodo(Nodo<E> ultimoNodo) {
		this.ultimoNodo = ultimoNodo;
	}
	
	/**
	 * 
	 * @param primerNodo
	 */
	public void setPrimerNodo(Nodo<E> primerNodo) {
		this.primerNodo = primerNodo;
	}

	/**
	 * Determina si la lista esta vacia
	 * @return Si esta vacia o no
	 */
	public boolean estaVacia () {
		return primerNodo==null;
	}
	
	/**
	 * 
	 * @param data
	 */
	public void encolar(E data) {
		Nodo newNode = new Nodo(data);
		if (estaVacia()) {
			primerNodo = ultimoNodo = newNode;
		} else {
			ultimoNodo.setNextNode(newNode);
			ultimoNodo = newNode;
		}
		tam++;
	}
	
	/**
	 * 
	 * @return
	 */
	public E desencolar() {
		E elemento=(E)this.getPrimerNodo().getValue();
		quitar();
		return elemento;
	}
	
    /**
    * Elimina el primer elemento
    */
    public void quitar() {
    	if (primerNodo!=null) {
        	Nodo<E> node=primerNodo.getNextNode();
        	primerNodo=node;
    	} 
    }
	
	/**
	 * 
	 * @return
	 */
    public E obtenerFrente() {
    	return (E)this.getPrimerNodo().getValue();
    }
	
	/**
	 * 
	 * @return
	 */
    public Cola<E> clonarCola() {
    	Cola<E> cola = new Cola<E>();
    	Nodo<E> nodo=primerNodo;
    	while (primerNodo!=null) {
    		cola.encolar(primerNodo.getValue());
    		nodo=nodo.getNextNode();
    	}
    	return cola;
    }
	
	/**
	 * 
	 * @param cola
	 * @return
	 */
    public boolean equals(Cola cola) {
    	if (this==cola) {
    		return true;
    	}
    	Cola colaAuxiliar=this.clonarCola();
    	Cola colaAuxiliar2=cola.clonarCola();
    	while (!colaAuxiliar.estaVacia()) {
    		if (!colaAuxiliar.desencolar().equals(colaAuxiliar2.desencolar())) {
    			return false;
    		}
    	}
    	return true;
    }
	
	/**
	 * 
	 * @param posicion
	 * @param valor
	 */
    public void introducirElemento(int posicion,E valor) {
    	if (posicion<0 || posicion>=tam) {
    		throw new IndexOutOfBoundsException();
    	}
    	int cont=0;
    	Nodo<E> nodoActual=primerNodo;
    	Nodo<E> nodoAuxiliar=primerNodo;
    	if (posicion!=1) {
    		while (nodoActual!=null) {
        		if (cont==posicion) {
        			nodoAuxiliar.setNextNode(new Nodo(valor));
        			nodoAuxiliar.getNextNode().setNextNode(nodoActual);
        		}
        		nodoAuxiliar=nodoActual;
        		nodoActual=nodoActual.getNextNode();
        	}
    	} else {
    		nodoAuxiliar=new Nodo(valor);
    		nodoAuxiliar.setNextNode(primerNodo);
    		primerNodo=nodoAuxiliar;
    	}
    	
    	cont++;
    	nodoActual=nodoActual.getNextNode();
    }
    
    /**
     * Verifica si la cola contiene el elemento indicado
     * @param elemento Elemento indicado
     * @return Si la cola lo contiene o no
     */
    public boolean contieneElemento(E elemento) {
    	boolean respuesta=false;
    	Nodo<E> nodo=primerNodo;
    	while (nodo!=null) {
    		if (elemento.equals(nodo.getValue())) {
    			respuesta=true;
    			break;
    		}
    		nodo=nodo.getNextNode();
    	}
    	return respuesta;
    }
	
	/**
	 * 
	 */
    @Override
    public String toString() {
    	String msj="[";
    	Nodo<E> nodo=primerNodo;
    	if (nodo!=null) {
    		msj+=nodo.getValue();
    	}
    	nodo=nodo.getNextNode();
    	while (nodo!=null) {
    		msj+=","+nodo.getValue();
    		nodo=nodo.getNextNode();
    	}
    	msj+="]";
    	return msj;
    }
    
}
