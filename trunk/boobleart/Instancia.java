package boobleart;

import java.util.ArrayList;

public class Instancia
{
	public ArrayList<Imagen> imagenes;
	
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
