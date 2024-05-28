package proyectostorify;

import static org.junit.Assert.*;

import org.junit.Test;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;

public class Test2 {

	/**
	 * Prueba el iterador de CircularList.
	 */
	@Test
	public void pruebaIteradorCircularList() {
		CircularList<String> lista = new CircularList<String>();
		lista.add("a");
		lista.add("b");
		lista.add("c");
		lista.add("d");
		lista.add("e");
		lista.add("f");
		String msj = "";
		for (String elemento : lista) {
			msj += elemento;
		}
		assertEquals(msj, "abcdef");
	}

	/**
	 * Prueba el método de eliminación de CircularList.
	 */
	@Test
	public void pruebaEliminarCircularList() {
		CircularList<Integer> lista = new CircularList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.add(6);
		lista.add(7);
		lista.add(8);
		lista.add(9);
		lista.add(10);
		lista.remove((Integer) 6);
		lista.remove((Integer) 1);
		lista.remove((Integer) 6);
		assertEquals(lista.size(), 8);
	}

	/**
	 * Prueba BinaryTree.
	 */
	@Test
	public void pruebaArbol() {
		BinaryTree<Integer> arbol = new BinaryTree<Integer>();
		arbol.add(10);
		arbol.add(20);
		arbol.add(30);
		arbol.add(12);
		arbol.add(17);
		arbol.add(34);
		arbol.add(54);
		arbol.remove(20);
		arbol.remove(17);
		arbol.remove(54);
		assertEquals(arbol.toCircularList().size(), 4);
	}

	/**
	 * Prueba BinaryTree con recorrido en postorden.
	 */
	@Test
	public void pruebaArbolCadenaPostOrden() {
		BinaryTree<Integer> arbol = new BinaryTree<Integer>();
		arbol.add(10);
		arbol.add(20);
		arbol.add(30);
		arbol.add(12);
		arbol.add(17);
		arbol.add(34);
		arbol.add(54);
		arbol.remove(20);
		arbol.remove(17);
		arbol.remove(54);
		assertEquals(arbol.toCircularList().toString(), "[34,30,12,10]");
	}

	/**
	 * Prueba ListaDoble.
	 */
	@Test
	public void pruebaListaDoble() {
		ListaDoble<Double> lista = new ListaDoble<Double>();
		lista.agregar(2.0);
		lista.agregar(5.0);
		lista.agregar(2.4);
		lista.agregar(8.2);
		lista.eliminar(2.0);
		assertEquals(lista.getTamanio(), 3);
	}

	/**
	 * Prueba ListaDoble con representación en cadena.
	 */
	@Test
	public void pruebaListaDobleString() {
		ListaDoble<Double> lista = new ListaDoble<Double>();
		lista.agregar(2.0);
		lista.agregar(5.0);
		lista.agregar(2.4);
		lista.agregar(8.2);
		lista.eliminar(2.0);
		assertEquals(lista.toString(), "[5.0,2.4,8.2]");
	}

	/**
	 * Prueba la unión de dos CircularList.
	 */
	@Test
	public void pruebaUnionCircularList() {
		ListaDoble<Double> lista = new ListaDoble<Double>();
		lista.agregar(2.0);
		lista.agregar(5.0);
		lista.agregar(2.4);
		lista.agregar(8.2);
		CircularList<Double> listaCircular = new CircularList<Double>();
		listaCircular.add(5.0);
		listaCircular.add(4.5);
		listaCircular.setUnion(lista.toCircularList());
		assertEquals(listaCircular.toString(), "[5.0,4.5,2.0,2.4,8.2]");
	}

}
