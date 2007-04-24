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
		String ruta = ruta_proyecto + fs + "in" + fs + "Tp1Ej1.in";
		ArrayList<Instancia> ret = new ArrayList<Instancia>();
		ArrayList<Long> linea;
				
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
		        for(int i = 0; i < n; ++i)
		        {
		        	linea = LeerLinea(in.readLine().trim());
		        	imagen = new Imagen(
		        				new Intervalo(linea.get(0),linea.get(1)), 
		        				new Intervalo(linea.get(2),linea.get(3)));
		        	instancia.imagenes.add(imagen);
		        }
		        ret.add(instancia);
	        }
	        
	        in.close();
	    } 
	    catch (IOException e)
	    {
	    	System.out.println("Error leyendo el archivo de entrada: ");
	    	e.printStackTrace();
	    }
	    
	    return ret;
	}

	private static ArrayList<Long> LeerLinea(String linea)
	{
		ArrayList<Long> valores = new ArrayList<Long>();
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
				valores.add(Long.valueOf(actual));
				actual = "";
			}
		}
		
		return valores;
	}
	
	
	public static void AgregarValor(Integer valor, Boolean agregar)
	{
		String ruta = ruta_proyecto + fs + "out" + fs + "Tp2Ej4.out";
		
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta, agregar));
			out.append(valor.toString() + "\n");
	        out.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error agregando valor al archivo de salida: ");
	    	e.printStackTrace();
		}
		
	}
	

	public static void EscribirResultado(Instancia instancia, Boolean agregar)
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