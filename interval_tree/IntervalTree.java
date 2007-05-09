package interval_tree;

import java.util.ArrayList;
import java.util.List;
import RedBlack.*;
import utilidades.Estadisticas;
import utilidades.Intervalo;

public class IntervalTree
{
	private RBtree arbol = new RBtree();
	private Estadisticas es;

	public IntervalTree(List<Intervalo> intervalos, Estadisticas es)
	{
		this.es = es;
		Nodos nodos = ArmarNodos(intervalos); ++es.armado;
		for (int i = 0; i < nodos.nodos.size(); ++i)
		{
			++es.armado;
			arbol.insertar(nodos.nodos.get(i).pivot, nodos.nodos.get(i)); ++es.armado;
		}
	}

	private Nodos ArmarNodos(List<Intervalo> intervalos)
	{
		List<Intervalo> izquierda = new ArrayList<Intervalo>(); ++es.armado;
		List<Intervalo> derecha = new ArrayList<Intervalo>(); ++es.armado;
		List<Intervalo> centro = new ArrayList<Intervalo>(); ++es.armado;
		
		Rango rango = BuscoRango(intervalos); ++es.armado;
		Integer pivot = rango.fin - rango.inicio; ++es.armado;
		pivot = rango.inicio + pivot / 2; ++es.armado;

		for(int i = 0; i < intervalos.size(); ++i)
		{
			++es.armado;
			Intervalo actual = intervalos.get(i); ++es.armado;
			++es.armado;
			if (Contiene(pivot, actual))
			{
				centro.add(actual); ++es.armado;
			}
			++es.armado;
			if (Anterior(pivot, actual))
			{
				izquierda.add(actual); ++es.armado;
			}
			++es.armado;
			if (Posterior(pivot, actual))
			{
				derecha.add(actual); ++es.armado;
			}
		}
		
		Listas listas = new Listas(izquierda, derecha, centro); ++es.armado;
		Nodos ret = new Nodos(); ++es.armado;
		
		++es.armado;
		if (listas.centro.size() > 0)
		{
			Nodo nodo = new Nodo(pivot, listas.centro); ++es.armado;
			ret.nodos.add(nodo); ++es.armado;
		}
		
		++es.armado;
		if (listas.izquierda.size() > 0)
		{
			ret.nodos.addAll(ArmarNodos(listas.izquierda).nodos); ++es.armado;
		}
		++es.armado;
		if (listas.derecha.size() > 0)
		{
			ret.nodos.addAll(ArmarNodos(listas.derecha).nodos); ++es.armado;
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
		Rango ret = new Rango(); ++es.armado;
		int menorInicio = intervalos.get(0).inicio; ++es.armado;
		int mayorFin = intervalos.get(0).fin; ++es.armado;

		for (int i = 1; i < intervalos.size(); ++i)
		{
			++es.armado;
			Intervalo actual = intervalos.get(i); ++es.armado;
			++es.armado;
			if (actual.inicio < menorInicio)
			{
				menorInicio = actual.inicio; ++es.armado;
			}
			++es.armado;
			if (actual.fin > mayorFin)
			{
				mayorFin = actual.fin; ++es.armado;
			}
		}
		
		ret.inicio = menorInicio; ++es.armado;
		ret.fin = mayorFin; ++es.armado;
		
		return ret;
	}

	public List<Intervalo> BuscarInterseccion(int valorBusq)
	{
		boolean salir = false; ++es.consulta;
		List<Intervalo> interv = new ArrayList<Intervalo>(); ++es.consulta;

		Nodo2 nodoAux = new Nodo2(); ++es.consulta;
		nodoAux = arbol.raiz(); ++es.consulta;
		while (!salir)
		{
			++es.consulta;
			if (nodoAux.key < valorBusq)
			{
				++es.consulta;
				// si la clave es menor agrego a la solucion los intervalos que terminan
				// despues que 'valorBusq' y voy para la derecha.
				List<Intervalo> dameTermDespues = dameTermDespues(nodoAux.valor, valorBusq);
				interv.addAll(dameTermDespues); es.consulta+=dameTermDespues.size();
				++es.consulta;
				if (nodoAux.der() != null)
				{
					nodoAux = nodoAux.der(); ++es.consulta;
				}
				else
				{
					salir = true; ++es.consulta;
				}
			}
			else if (nodoAux.key > valorBusq)
			{
				++es.consulta;
				// si la clave es mayor agrego a la solucion los intervalos que empiezan
				// antes que 'valorBusq' y voy para la izquierda.
				List<Intervalo> dameEmpAntes = dameEmpAntes(nodoAux.valor, valorBusq);
				interv.addAll(dameEmpAntes); es.consulta+=dameEmpAntes.size();
				++es.consulta;
				if (nodoAux.izq() != null)
				{
					nodoAux = nodoAux.izq(); ++es.consulta;
				}
				else
				{
					salir = true; ++es.consulta;
				}
			}
			else
			{
				++es.consulta;
				// si es igual al valor de la raiz agrego todos los intervalos a la solucion
				interv.addAll(nodoAux.valor.inicio); es.consulta+=nodoAux.valor.inicio.size();
				++es.consulta;
				if (nodoAux.der() != null)
				{
					nodoAux = nodoAux.der(); ++es.consulta;
				}
				else
				{
					salir = true; ++es.consulta;
				}
			}
		}
		
		return interv;
	}

	private List<Intervalo> dameTermDespues(Nodo nodo, int valor)
	{
		List<Intervalo> intervSol = new ArrayList<Intervalo>(); ++es.consulta;
		List<Intervalo> intervalos = new ArrayList<Intervalo>(); ++es.consulta;
		
		intervalos = nodo.fin; ++es.consulta;

		for(int i = 0; i < intervalos.size() && (intervalos.get(i).fin) >= valor; ++i)
		{
			++es.consulta;
			intervSol.add(intervalos.get(i)); ++es.consulta;
		}
		
		return intervSol;
	}

	private List<Intervalo> dameEmpAntes(Nodo nodo, int valor)
	{
		List<Intervalo> intervSol = new ArrayList<Intervalo>(); ++es.consulta;
		List<Intervalo> intervalos = new ArrayList<Intervalo>(); ++es.consulta;
		
		intervalos = nodo.inicio; ++es.consulta;
		for(int i = intervalos.size() - 1; i >= 0 && (intervalos.get(i).inicio) <= valor; --i)
		{
			++es.consulta;
			intervSol.add(intervalos.get(i)); ++es.consulta; ++es.consulta;
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

		public Listas(List<Intervalo> izq, List<Intervalo> der, List<Intervalo> cen)
		{
			izquierda = izq; ++es.armado;
			derecha = der; ++es.armado;
			centro = cen; ++es.armado;
		}
	}

	private class Nodos
	{
		public List<Nodo> nodos = new ArrayList<Nodo>();
	}
}
