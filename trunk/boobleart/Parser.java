package boobleart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Parser
{
	static String fs = System.getProperty("file.separator");
	static String ruta_proyecto = System.getProperty("java.class.path");
	
	public static ArrayList<Instancia> LeerInstancias()
	{
		String ruta = ruta_proyecto + fs + "in" + fs + "Tp2Ej1.in";
		ArrayList<Instancia> ret = new ArrayList<Instancia>();
		ArrayList<Integer> linea;
				
		Instancia instancia;
		Imagen imagen;
		long m, n;
		
	    try
	    {
	        BufferedReader in = new BufferedReader(new FileReader(ruta));
	        
	        for(m = Long.valueOf(in.readLine().trim()); m > 0; --m)
	        {
	        	n = Long.valueOf(in.readLine().trim());
	        	instancia = new Instancia();
		        for(Integer i = 0; i < n; ++i)
		        {
		        	linea = LeerLinea(in.readLine().trim());
		        	imagen = new Imagen(
		        				new Intervalo(linea.get(0),linea.get(1),i), 
		        				new Intervalo(linea.get(2),linea.get(3),i));
		        	instancia.imagenes.add(imagen);
		        }
		        ret.add(instancia);
	        }
	        
	        in.close();
	    } 
	    catch (IOException e)
	    {
	    	System.out.println("Error leyendo el archivo de imagenes: ");
	    	e.printStackTrace();
	    }
	    
	    return ret;
	}
	
	public static ArrayList<Punto> LeerConsultas()
	{
		String ruta = ruta_proyecto + fs + "in" + fs + "Tp2Ej4.in";
		ArrayList<Punto> ret = new ArrayList<Punto>();
		ArrayList<Integer> linea;
		
		long k;
		
	    try
	    {
	        BufferedReader in = new BufferedReader(new FileReader(ruta));
	        
	        for(k = Long.valueOf(in.readLine().trim()); k > 0; --k)
	        {
	        	linea = LeerLinea(in.readLine().trim());
		        ret.add(new Punto(linea.get(0),linea.get(1)));
	        }
	        
	        in.close();
	    } 
	    catch (IOException e)
	    {
	    	System.out.println("Error leyendo el archivo de consultas: ");
	    	e.printStackTrace();
	    }
	    
	    return ret;
	}

	private static ArrayList<Integer> LeerLinea(String linea)
	{
		ArrayList<Integer> valores = new ArrayList<Integer>();
		char[] ca = linea.toCharArray();
		String actual = "";
		
		for(int i = 0; i < ca.length; ++i)
		{
			if(ca[i] != ' ')
			{
				do
				{
					actual += ca[i];
					++i;
				}
				while((i < ca.length) && (ca[i] != ' '));
				valores.add(Integer.valueOf(actual));
				actual = "";
			}
		}
		
		return valores;
	}
	
	public static void EscribirInstancia(Instancia instancia, Boolean agregar)
	{
		String ruta = ruta_proyecto + fs + "out" + fs + "Tp2Ej4.out";
		
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta, agregar));
			out.write(instancia.toString() + "0");
	        out.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo el archivo de salida: ");
	    	e.printStackTrace();
		}
	}
	
	
	public static void EscribirEstadisticas(String ruta, Long x, Long y)
    {
		ruta = ruta_proyecto + fs + "dat" + fs + ruta;
		
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta, true));
	        
	        out.write(x.toString() + " " + y.toString() + "\n");
	        out.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo las estadisticas: ");
	    	e.printStackTrace();
		}
    }
}