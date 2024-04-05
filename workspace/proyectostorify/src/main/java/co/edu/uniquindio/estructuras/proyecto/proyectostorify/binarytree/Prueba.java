package co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree<Integer> arbol = new BinaryTree<Integer>();
		arbol.add(43);
		arbol.add(10);
		arbol.add(8);
		arbol.add(54);
		arbol.add(15);
		arbol.add(50);
		arbol.add(53);
		System.out.println("Inorder: ");
		arbol.inorder();
		System.out.println();
		System.out.println("Postorder: ");
		arbol.postorder();
		System.out.println();
		System.out.println("Preorder: ");
		arbol.preorder();
		
		
	}

}
