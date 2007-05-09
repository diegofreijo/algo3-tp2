package interval_tree;

import java.util.ArrayList;
import java.util.List;

import utilidades.Intervalo;

public class Nodo
{
	public int pivot;
	public List<Intervalo> inicio;
	public List<Intervalo> fin;
	
	public Nodo(int pivot, List<Intervalo> intervalos)
	{
		this.pivot = pivot;
		
		this.inicio = OrdenarX(intervalos);
		
		this.fin = OrdenarY(intervalos);
		
	}
	
	private List<Intervalo> OrdenarX(List<Intervalo> intervalos)
    {
		List<Intervalo> ret = new ArrayList<Intervalo>();
		
		if(intervalos.size() > 0)
		{
			Intervalo pivot = intervalos.get(0);
			
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
	
	private List<Intervalo> OrdenarY(List<Intervalo> intervalos)
    {
		List<Intervalo> ret = new ArrayList<Intervalo>();
		
		if(intervalos.size() > 0)
		{
			Intervalo pivot = intervalos.get(0);
			
			List<Intervalo> mayores = new ArrayList<Intervalo>();
			List<Intervalo> menores = new ArrayList<Intervalo>();
			
			int i = 1;
			
			while(i < intervalos.size())
			{
				Intervalo actual = intervalos.get(i); 
				if(actual.fin < pivot.fin)
				{
					menores.add(actual);					
				} else {
					mayores.add(actual);					
				}
				i++;
			}
			
			if(mayores.size() > 0)
			{
				ret.addAll(OrdenarY(mayores));
			}
			
			ret.add(pivot);
			
			if(menores.size() > 0)
			{
				ret.addAll(OrdenarY(menores));
			}
		} else {
			ret = intervalos;
		}
	    return ret;
    }
}
