package intervalTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import boobleart.Intervalo;

public class IntervalTree
{

	public Map<Integer,Nodo> arbol = new TreeMap<Integer,Nodo>();
	
	public IntervalTree(List<Intervalo> intervalos)
	{
		Rango rango = BuscoRango(intervalos);
		
		Integer pivot = rango.fin - rango.inicio;
		pivot = pivot/2;
		
		Listas listas = ArmoListas(pivot,intervalos);
		
		Nodo nodo = new Nodo(pivot,listas.centro);
		
		arbol.put(pivot, nodo);
		
		
		
	}

	private Listas ArmoListas(long pivot, List<Intervalo> intervalos)
    {
	    int i = 1;
	    
	    List<Intervalo> izquierda = new ArrayList<Intervalo>();
	    List<Intervalo> derecha= new ArrayList<Intervalo>();
	    List<Intervalo> centro= new ArrayList<Intervalo>();
	    
	    while(i < intervalos.size()){
	    	
	    	Intervalo actual = intervalos.get(i);
	    	
	    	if(Contiene(pivot,actual)){
	    		centro.add(actual);
	    	}
	    	
	    	if(Anterior(pivot,actual)){
	    		izquierda.add(actual);
	    	}
	    	
	    	if(Posterior(pivot,actual)){
	    		derecha.add(actual);
	    	}
	    	
	    }
	    
	    Listas ret = new Listas(izquierda,derecha,centro);
	    
	    return ret;
	    
    }
	
	private class Listas
    {
		public List<Intervalo> izquierda;
		public List<Intervalo> derecha;
		public List<Intervalo> centro;
		
		public Listas(List<Intervalo> izq,List<Intervalo> der,List<Intervalo> cen)
		{
			izquierda = izq;
			derecha = der;
			centro = cen;
		}
    }

	private boolean Posterior(long pivot, Intervalo actual)
    {
	    // TODO Auto-generated method stub
	    return false;
    }

	private boolean Anterior(long pivot, Intervalo actual)
    {
	    // TODO Auto-generated method stub
	    return false;
    }

	private boolean Contiene(long pivot, Intervalo intervalo)
    {
	    // TODO Auto-generated method stub
	    return false;
    }
	
	private Rango BuscoRango(List<Intervalo>intervalos){
		
		Rango ret = new Rango();
		
		int menorInicio = intervalos.get(0).inicio;
		int mayorFin = intervalos.get(0).fin;
		
		int i = 1;
		
		while(i < intervalos.size()){
			
			Intervalo actual = intervalos.get(i);
			
			if(actual.inicio < menorInicio){
				menorInicio = actual.inicio;
			}
			
			if(actual.fin > mayorFin){
				mayorFin = actual.fin;
			}
			
			i++;
		}
		
		ret.inicio = menorInicio;
		ret.fin = mayorFin;
		
		return ret;
	}
	
	private class Rango
	{
		private int inicio,fin;
		
		public Rango(int inicio, int fin){
			this.inicio = inicio;
			this.fin = fin;
		}
		
		public Rango(){}
	}
}
