package boobleart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilidades.Diferencias;
import utilidades.Estadisticas;
import utilidades.Imagen;
import utilidades.Instancia;
import utilidades.Intervalo;
import utilidades.Punto;
import utilidades.ResultadoConsulta;

public abstract class Parser
{
	static String fs = System.getProperty("file.separator");
	static String ruta_proyecto = System.getProperty("java.class.path");
	
	static String ruta_instancias = ruta_proyecto + fs + "in" + fs + "Tp2Ej1.in";
	static String ruta_consultas = ruta_proyecto + fs + "in" + fs + "Tp2Ej4.in";
	static String ruta_resultados = ruta_proyecto + fs + "out" + fs + "Tp2Ej4.out";
	static String ruta_diferencias = ruta_proyecto + fs + "dat" + fs + "diferencias.txt";
	static String ruta_armado = ruta_proyecto + fs + "dat" + fs + "Tp2(armado).dat";
	static String ruta_consulta = ruta_proyecto + fs + "dat" + fs + "Tp2(consulta).dat";
	static String ruta_fusion = ruta_proyecto + fs + "dat" + fs + "Tp2(fusion).dat";
	static String ruta_consulta_mas_fusion = ruta_proyecto + fs + "dat" + fs + "Tp2(consulta_mas_fusion).dat";
	static String ruta_fb = ruta_proyecto + fs + "dat" + fs + "Tp2(fb).dat";
	
	// Levanta las instancias (imagenes) del archivo de entrada
	public static List<Instancia> LeerInstancias()
	{
		List<Instancia> ret = new ArrayList<Instancia>();
		List<Integer> linea;
				
		Instancia instancia;
		Imagen imagen;
		long m, n;
		
	    try
	    {
	        BufferedReader in = new BufferedReader(new FileReader(ruta_instancias));
	        
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

	// Levanta las consutas (puntos) del archivo de entrada
	public static List<Punto> LeerConsultas()
	{
		List<Punto> ret = new ArrayList<Punto>();
		List<Integer> linea;
		
		long k;
		
	    try
	    {
	        BufferedReader in = new BufferedReader(new FileReader(ruta_consultas));
	        
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

	// Lee toda una linea del archivo de entrada
	private static List<Integer> LeerLinea(String linea)
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
	
	// Escribe el resultado de una instancia.
	public static void EscribirResultados(List<ResultadoConsulta> resultados)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta_resultados, true));
			if(resultados.size() == 1)
			{
				out.write(resultados.get(0).toString() + "0");
			}
			else
			{
				out.write(resultados.get(0).toString());
				for(int i = 1; i < resultados.size(); ++i)
				{
					out.write("1\n" + resultados.get(i).toString());
				}
				out.write("0\n");
			}
	        out.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo los resultados: ");
	    	e.printStackTrace();
		}
	}
	
	// Escribe las diferencias encontradas entre el algo eficiente y el de FB.
	public static void EscribirDiferencias(Diferencias diferencias)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta_diferencias, true));
			out.write(diferencias.toString());
	        out.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo las diferencias: ");
	    	e.printStackTrace();
		}
	}
	
	// Escribe la cantidad de instrucciones ejecutadas para una instancia
	public static void EscribirEstadisticas(Estadisticas es)
    {
		try
		{
			// Cantidad de operaciones de armado en funcion de la cantidad de imagenes
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta_armado, true));
	        out.write(es.cant_img + " " + es.armado + "\n");
	        out.close();
	        
	        // Cantidad de operaciones de consulta en funcion de la cantidad de imagenes
	        out = new BufferedWriter(new FileWriter(ruta_consulta, true));
	        for(Long op: es.consultas)
	        {
	        	out.write(es.cant_img + " " + op + "\n");
	        }
	        out.close();
	        
	        // Cantidad de operaciones de fusion en funcion del producto de la cantidad de intervalos de ambos ejes 
	        out = new BufferedWriter(new FileWriter(ruta_fusion, true));
	        for(List<Long> f: es.fusiones)
	        {
	        	out.write(f.get(0) + " " + f.get(1) + "\n");
	        }
	        out.close();
	        
	        // Suma de las 2 anteriores en funcion de la cantidad de imagenes
	        out = new BufferedWriter(new FileWriter(ruta_consulta_mas_fusion, true));
	        for(int i = 0; i < es.consultas.size(); ++i)
	        {
	        	out.write(es.cant_img + " " + (es.consultas.get(i) + es.fusiones.get(i).get(1)) + "\n");
	        }
	        out.close();
	        
	        // Cantidad de operaciones del algoritmo de fuerza bruta en funcion de la cantidad de imagenes
	        out = new BufferedWriter(new FileWriter(ruta_fb, true));
	        for(Long op: es.fbs)
	        {
	        	out.write(es.cant_img + " " + op + "\n");
	        }
	        out.close();
	        
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo las estadisticas: ");
	    	e.printStackTrace();
		}
    }
	
	// Borra todos los archivos de salida de datos
	public static void LimpiarArchivos()
	{
		File f = new File(ruta_resultados); f.delete();
		f = new File(ruta_diferencias);	f.delete();
		f = new File(ruta_armado); f.delete();
		f = new File(ruta_consulta); f.delete();
		f = new File(ruta_fusion); f.delete();
		f = new File(ruta_consulta_mas_fusion); f.delete();
		f = new File(ruta_fb); f.delete();
	}
}