/*
La idea es tener tres "punteros"(en Java creo que son referencias): uno al padre, izquierda y derecha del arbol,
un nodo que contenga el color, la clave y el valor asociado a la misma.
Y ademas tenemos la variable tamanio para saber el tamaño del mismo.

Todos los nodos poseen informacion, las hojas del arbol son nodos con informacion
tal que sus dos hijos son null y por lo tanto pueden ser tanto rojos como negros.

La funcion insertar toma como parametros la clave y el valor asociado a ls misma.
*/

package RedBlack;

public class RBtree{

 private int tamanio;
 private Nodo2 nodo;
 private RBtree left;
 private RBtree right;
 private RBtree padre;

private static final boolean ROJO = true;
private static final boolean NEGRO = false;

	public RBtree(){
		tamanio = 0;
		left = right = padre = null;
		Nodo2 nodo = new Nodo2();
		this.nodo = nodo;
	}

	public boolean vacio(){
		return ((this.tamanio == 0)? true : false);
	}

	public Nodo2 raiz(){
		return (this.nodo);
	}
	
	public RBtree izquierda(){
		return (this.left);
	}
	
	public RBtree derecha(){
		return (this.right);
	}
	
	public RBtree padre(){
		return this.padre;
	}
	
	public RBtree abuelo(){
    		return this.padre.padre;
	}
	
	public RBtree tio(){
		if (this.padre == this.abuelo().left)
		        return this.abuelo().right;
		else
       			return this.abuelo().left;
	}

	public void insertar(Integer k, Object valor){
	RBtree elem = new RBtree();
	elem.nodo.construir(k,valor); 

	RBtree y = null;
	RBtree x = new RBtree();
	x.copia(this);
	
		while (x.tamanio != 0){ 
			y = x;
			if (k < x.nodo.key){
				x = x.left;
			}
			else{
				x = x.right;
			}
		}
	elem.padre = y;

	if ( y == null) {		
		this.nodo = elem.nodo;
	}
	else if (k < y.nodo.key) {
			y.left = elem;
		}
		else {
			y.right = elem;
		}
	this.tamanio++;
	arreglarInv(elem);
	}

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
	
private  static void arreglarInv(RBtree elem){
	insert_case1(elem);
}

private static void insert_case1(RBtree elem) {
    if (elem.padre == null)
        elem.nodo.color = NEGRO;
    else
        insert_case2(elem);
}

private static void insert_case2(RBtree elem) {
    if (elem.padre.nodo.color == NEGRO)
        return; // aca NO se rompe el invariante.
    else
        insert_case3(elem);
}

private static void insert_case3(RBtree elem) {
if (elem.tio() != null){
    if (elem.tio().nodo.color == ROJO) {
        elem.padre.nodo.color = NEGRO;
        elem.tio().nodo.color = NEGRO;
        elem.abuelo().nodo.color = ROJO;
        insert_case1(elem.abuelo());
    }
    else
        insert_case4(elem);
    }
}
	
private static void insert_case4(RBtree elem) {
    if (elem == elem.padre.right && elem.padre == elem.abuelo().left) {
        rotarIzq(elem.padre);
        elem = elem.left;
    } else if (elem == elem.padre.left && elem.padre == elem.abuelo().right) {
        rotarDer(elem.padre);
        elem = elem.right;
    }
    insert_case5(elem);
}

private static void insert_case5(RBtree elem) {
    elem.padre.nodo.color = NEGRO;
    elem.abuelo().nodo.color = ROJO;
    if (elem == elem.padre.left && elem.padre == elem.abuelo().left) {
        rotarDer(elem.abuelo());
    } else {
        // Aca, elem es hijo derecho && el padre de elem tambien es hijo derecho. */
        rotarIzq(elem.abuelo());
    }
}


// Rotaciones a la izquierda y a la derecha

private static void rotarIzq(RBtree elem){
	RBtree y = elem.right;
	elem.right = y.left;
	y.left.padre = elem;
	y.padre = elem.padre;
	if (elem.padre != null){
		if (elem == elem.padre.left)
			elem.padre.left = y;
		else
			elem.padre.right = y;
	}
	y.left = elem;
	elem.padre = y;
}


private static void rotarDer(RBtree elem){
	RBtree x = elem.left;
	elem.left = x.right;
	x.right.padre = elem;
	x.padre = elem.padre;
	if (elem.padre != null){
		if (elem == elem.padre.left)
			elem.padre.left = x;
		else
			elem.padre.right = x;
	}
	x.right = elem;
	elem.padre = x;
}

public class Nodo2{
	private Boolean color;
	public Integer key;
	public Object valor; // algunos usan Comparable en lugar de Object.
			
	public void construir(Integer  k, Object val){
		this.key = k;
		this.valor = val;
		this.color = true;
	}
	public void copia(Nodo2 n){
		this.key = n.key;
		this.valor = n.valor;
		this.color = n.color;
	}
	
}
}