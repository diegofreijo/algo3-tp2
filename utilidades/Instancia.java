package utilidades;

import java.util.ArrayList;
import java.util.List;

public class Instancia
{
	public List<Imagen> imagenes;
	
	public Instancia()
	{
		imagenes = new ArrayList<Imagen>();
	}
	
	public String toString()
	{
		String ret = "";
		for(Imagen img : imagenes)
		{
			ret += img.toString() + "\n"; 
		}
		return ret; 
	}	
}
