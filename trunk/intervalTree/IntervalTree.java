package intervalTree;

import java.util.ArrayList;
import java.util.List;
import boobleart.Instancia;
import boobleart.Intervalo;
import RedBlack.*;

public class IntervalTree
{
	private RBtree arbol = new RBtree();

	//public RBtree arbol = new RBtree();
	public IntervalTree(List<Intervalo> intervalos)
	{
		Nodos nodos = ArmarNodos(intervalos);
		int i = 0;
		while (i < nodos.nodos.size())
		{
			//System.out.println(i);
			arbol.insertar(nodos.nodos.get(i).pivot, nodos.nodos.get(i));
			i++;
		}
	}

	private Nodos ArmarNodos(List<Intervalo> intervalos)
	{
		List<Intervalo> izquierda = new ArrayList<Intervalo>();
		List<Intervalo> derecha = new ArrayList<Intervalo>();
		List<Intervalo> centro = new ArrayList<Intervalo>();
		Rango rango = BuscoRango(intervalos);
		Integer pivot = rango.fin - rango.inicio;
		pivot = rango.inicio + pivot / 2;
		//System.out.println(pivot);
		int i = 0;
		while (i < intervalos.size())
		{
			Intervalo actual = intervalos.get(i);
			if (Contiene(pivot, actual))
			{
				centro.add(actual);
			}
			if (Anterior(pivot, actual))
			{
				izquierda.add(actual);
			}
			if (Posterior(pivot, actual))
			{
				derecha.add(actual);
			}
			i++;
		}
		Listas listas = new Listas(izquierda, derecha, centro);
		Nodos ret = new Nodos();
		if (listas.centro.size() > 0)
		{
			Nodo nodo = new Nodo(pivot, listas.centro);
			ret.nodos.add(nodo);
		}
		if (listas.izquierda.size() > 0)
		{
			ret.nodos.addAll(ArmarNodos(listas.izquierda).nodos);
		}
		if (listas.derecha.size() > 0)
		{
			ret.nodos.addAll(ArmarNodos(listas.derecha).nodos);
		}
		return ret;
	}

	private boolean Posterior(long pivot, Intervalo actual)
	{
		if (actual.inicio > pivot)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean Anterior(long pivot, Intervalo actual)
	{
		if (actual.fin < pivot)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean Contiene(long pivot, Intervalo actual)
	{
		if (pivot <= actual.fin && pivot >= actual.inicio)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private Rango BuscoRango(List<Intervalo> intervalos)
	{
		Rango ret = new Rango();
		int menorInicio = intervalos.get(0).inicio;
		int mayorFin = intervalos.get(0).fin;
		int i = 1;
		while (i < intervalos.size())
		{
			Intervalo actual = intervalos.get(i);
			if (actual.inicio < menorInicio)
			{
				menorInicio = actual.inicio;
			}
			if (actual.fin > mayorFin)
			{
				mayorFin = actual.fin;
			}
			i++;
		}
		ret.inicio = menorInicio;
		ret.fin = mayorFin;
		return ret;
	}

	public List<Intervalo> BuscarInterseccion(int valorBusq)
	{
		boolean salir = false;
		List<Intervalo> interv = new ArrayList<Intervalo>();
		
		//RBtree arbolAux = new RBtree();
		Nodo2 nodoAux = new Nodo2();
		nodoAux = arbol.raiz();
		while (!salir)
		{
			if (nodoAux.key < valorBusq)
			{
				//si la clave es menor agrego a la solucion los intervalos que terminan 
				//despues que 'valorBusq' y voy para la derecha.
				System.out.println("1: " + nodoAux.key);
				interv.addAll(dameTermDespues(nodoAux.valor, valorBusq));
				if (nodoAux.der() != null)
				{
					nodoAux = nodoAux.der();
				}
				else
				{
					salir = true;
				}
			}
			else if (nodoAux.key > valorBusq)
			{
				//si la clave es mayor agrego a la solucion los intervalos que empiezan 
				//antes que 'valorBusq' y voy para la izquierda.
				System.out.println("2: " + nodoAux.key);
				interv.addAll(dameEmpAntes(nodoAux.valor, valorBusq));
				if (nodoAux.izq() != null)
				{
					System.out.println("2a");
					nodoAux = nodoAux.izq();
				}
				else
				{
					System.out.println("2b");
					salir = true;
				}
			}
			else
			{
				//si es igual al valor de la raiz agrego todos los intervalos a la solucion
				interv.addAll(nodoAux.valor.inicio);
				System.out.println("3: " + nodoAux.key);
				//salir=true; //nose si esta bien esto
				if (nodoAux.der() != null)
				{
					nodoAux = nodoAux.der();
				}
				else
				{
					salir = true;
				}
			}
		}
		return interv;
	}

	private List<Intervalo> dameTermDespues(Nodo nodo, int valor)
	{
		List<Intervalo> intervSol = new ArrayList<Intervalo>();
		List<Intervalo> intervalos = new ArrayList<Intervalo>();
		intervalos = nodo.fin;
		int i = 0;
		while (i < intervalos.size() && (intervalos.get(i).fin) >= valor)
		{
			intervSol.add(intervalos.get(i));
			i++;
		}
		return intervSol;
	}

	private List<Intervalo> dameEmpAntes(Nodo nodo, int valor)
	{
		List<Intervalo> intervSol = new ArrayList<Intervalo>();
		List<Intervalo> intervalos = new ArrayList<Intervalo>();
		intervalos = nodo.inicio;
		//int i = 0;
		int i = intervalos.size() - 1;
		//while(i < intervalos.size() && (intervalos.get(i).inicio) <= valor){
		while (i >= 0 && (intervalos.get(i).inicio) <= valor)
		{
			intervSol.add(intervalos.get(i));
			i--;
		}
		return intervSol;
	}

	private class Rango
	{
		private int inicio, fin;

		public Rango(int inicio, int fin)
		{
			this.inicio = inicio;
			this.fin = fin;
		}

		public Rango()
		{
		}
	}

	private class Listas
	{
		public List<Intervalo> izquierda;

		public List<Intervalo> derecha;

		public List<Intervalo> centro;

		public Listas(List<Intervalo> izq, List<Intervalo> der,
		        List<Intervalo> cen)
		{
			izquierda = izq;
			derecha = der;
			centro = cen;
		}
	}

	private class Nodos
	{
		public List<Nodo> nodos = new ArrayList<Nodo>();
	}

	//public static void main(String[] args)
	public static void main()
	{
		List<Intervalo> lista = new ArrayList<Intervalo>();
		List<Intervalo> lista2 = new ArrayList<Intervalo>();
		
		IntervalTree RBinterv = new IntervalTree(lista);
		System.out.println("key: " + RBinterv.arbol.raiz().key);
		System.out.println("val ini: " + RBinterv.arbol.raiz().valor.inicio);
		System.out.println("val fin: " + RBinterv.arbol.raiz().valor.fin);
		lista2 = RBinterv.BuscarInterseccion(24);
		System.out.println(lista2.toString());
		System.out.println(RBinterv.toString());
		for (Intervalo interv : lista2)
		{
			System.out.println(interv.toString());
		}
	}
}
