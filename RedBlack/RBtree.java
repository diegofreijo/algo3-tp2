package RedBlack;

import intervalTree.*;

public class RBtree{

	private int tamanio;
	private Nodo2 root;

 	private static final int ROJO = 0;
 	private static final int NEGRO = 1;

	public RBtree(){
		tamanio = 0;
		this.root = null;
	}

	public boolean vacio(){
		return ((this.tamanio == 0)? true : false);
	}

	public Nodo2 raiz(){
		return (this.root);
	}

	public Nodo2 izquierda(){
		return (this.root.izq());
	}

	public Nodo2 derecha(){
		return (this.root.der());
	}
/*
	public Nodo2 padre(){
		return this.root.padre();
	}

	*/

	public void insertar(int k, Nodo valor){
	Nodo2 elem = new Nodo2();
	elem.construir(k,valor);

	Nodo2 y = null;
	Nodo2 x = this.root;
	//RBtree x = new RBtree();
	//x.copia(this);

		while (x != null) {
			y = x;
			if (k < x.key){
				x = x.izq;
			}
			else{
				x = x.der;
			}
		}

	elem.padre = y;

	if ( y == null) {
		this.root = elem;
	}
	else if (k < y.key) {
			y.izq = elem;
		}
		else {
			y.der = elem;
		}
	this.tamanio++;
	arreglarInv(elem);
	}
/*
	public void copia(RBtree rb){

		this.padre = rb.padre;
		if (rb.right != null)
			this.right.copia(rb.right);
		else
			this.right = null;

		if (rb.left != null)
			this.left.copia(rb.left);
		else
			this.left= null;

		this.nodo.copia(rb.nodo);
		this.tamanio = rb.tamanio;
	}
*/
	private void arreglarInv(Nodo2 elem){
		insert_case1(elem);
	}

	private void insert_case1(Nodo2 elem) {
	    if (elem.padre == null){
	        elem.color = NEGRO;
	        System.out.println("Case1");
		}
	    else{
	        insert_case2(elem);
		}
	}

	private void insert_case2(Nodo2 elem) {
	    if (elem.padre().color == NEGRO){
	    System.out.println("Case2");
	        return; // aca NO se rompe el invariante.
		}
	    else{
	        insert_case3(elem);
		}
	}

	private void insert_case3(Nodo2 elem) {
		if (elem.tio() != null){
		    if (elem.tio().color == ROJO) {
		        elem.padre().color = NEGRO;
		        elem.tio().color = NEGRO;
		        elem.abuelo().color = ROJO;
		        insert_case1(elem.abuelo());
		        System.out.println("Case3");
		    }
		    else {
				insert_case4(elem);
			}
		}
		else{
		     insert_case4(elem);
		}
	}

	private void insert_case4(Nodo2 elem) {
	    if (elem == elem.padre.der && elem.padre == elem.abuelo().izq) {
	        this.rotarIzq(elem.padre);
	        elem = elem.izq;
	        System.out.println("Case4");
	    } else if (elem == elem.padre.izq && elem.padre == elem.abuelo().der) {
	        this.rotarDer(elem.padre);
	        elem = elem.der;
	        System.out.println("Case4");
	    }
	    insert_case5(elem);
	}

	private void insert_case5(Nodo2 elem) {
	    elem.padre.color = NEGRO;
	    elem.abuelo().color = ROJO;
	    System.out.println("Case5");
	    if (elem == elem.padre.izq && elem.padre == elem.abuelo().izq) {
			System.out.println("rotarDer");
	        this.rotarDer(elem.abuelo());
	    } else {
	        // Aca, elem es hijo derecho && el padre de elem tambien es hijo derecho. */
	        System.out.println("rotarIzq");
	        this.rotarIzq(elem.abuelo());
	    }
	}


// Rotaciones a la izquierda y a la derecha

	private void rotarIzq(Nodo2 elem){
		Nodo2 y = elem.der;
		elem.der = y.izq;
		if (y.izq !=null)
		y.izq.padre = elem;
		y.padre = elem.padre;
		if (elem.padre != null){
			if (elem == elem.padre.izq)
				elem.padre.izq = y;
			else
				elem.padre.der = y;
		}
		else{
			this.root = y;
		}
		y.izq = elem;
		elem.padre = y;
		System.out.println("FIn rotarIzq");
	}


	private void rotarDer(Nodo2 elem){
		Nodo2 x = elem.izq;
		elem.izq = x.der;
		if (x.der != null)
		x.der.padre = elem;
		x.padre = elem.padre;
		if (elem.padre != null){
			if (elem == elem.padre.izq)
				elem.padre.izq = x;
			else
				elem.padre.der = x;
		}
		else{
			this.root = x;
		}
		x.der = elem;
		elem.padre = x;
		System.out.println("rotarDer");
	}

public static void main(String[] args)
	{

		// Leo los datos de entrada
		/*
		ArrayList<int> instancias = Parser.LeerInstancias();
		ArrayList<Punto> consultas = Parser.LeerConsultas();
		*/
		// Genero los arboles


		RBtree rb = new RBtree();
			/*
		Integer valor = new Integer(33);
    	Integer valor1 = new Integer(44);
		Integer valor2 = new Integer(55);
		Integer valor3 = new Integer(66);
		int c1 = 33;
		int c2 = 44;
		int c3 = 55;
		int c4 = 66;
		rb.insertar(c1,valor);
		rb.insertar(c2,valor1);
		rb.insertar(c3,valor2);
		rb.insertar(c4,valor3);
*/

			//System.out.println("===============");
			System.out.println(rb.raiz().izq.key);
			System.out.println(rb.raiz().izq.valor);
			System.out.println(rb.raiz().izq.color);
			System.out.println(rb.raiz().key);
			System.out.println(rb.raiz().valor);
			System.out.println(rb.raiz().color);
			System.out.println(rb.derecha().key);
			System.out.println(rb.derecha().valor);
			System.out.println(rb.derecha().color);
			System.out.println(rb.derecha().der().key);
			System.out.println(rb.derecha().der().valor);
			System.out.println(rb.derecha().der().color);
			/*
			System.out.println(rb.derecha().der().der().key);
			System.out.println(rb.derecha().der().der().valor);
			System.out.println(rb.derecha().der().der().color);
			*/
			//arbol_y = new IntervalTree(intervalos_y);

	}

}