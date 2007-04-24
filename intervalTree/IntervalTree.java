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
		List<Intervalo> intOrdenados = OrdenarX(intervalos);
		
		int medio = intOrdenados.size();
		medio = medio/2;
		
		int pivot = intervalos.get(medio).inicio;
		
		
		
		
	}

	private List<Intervalo> OrdenarX(List<Intervalo> intervalos)
    {
		List<Intervalo> ret = new ArrayList<Intervalo>();
		
		if(intervalos.size() > 0)
		{
			Intervalo pivot = new Intervalo();
			pivot = intervalos.get(0);
			
			List<Intervalo> mayores = new ArrayList<Intervalo>();
			List<Intervalo> menores = new ArrayList<Intervalo>();
			
			int i = 1;
			
			while(i < intervalos.size())
			{
				Intervalo actual = intervalos.get(i); 
				if(actual.inicio < pivot.inicio)
				{
					menores.add(actual);					
				} else {
					mayores.add(actual);					
				}
				i++;
			}
			
			if(mayores.size() > 0)
			{
				ret.addAll(OrdenarX(mayores));
			}
			
			ret.add(pivot);
			
			if(menores.size() > 0)
			{
				ret.addAll(OrdenarX(menores));
			}
		} else {
			ret = intervalos;
		}
	    return ret;
    }
}
