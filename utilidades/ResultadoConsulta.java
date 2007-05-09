package utilidades;

import java.util.LinkedList;
import java.util.List;

public class ResultadoConsulta
{
	public List<Imagen> imagenes;
	
	public ResultadoConsulta()
	{
		imagenes = new LinkedList<Imagen>();
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
