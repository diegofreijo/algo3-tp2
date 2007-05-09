package boobleart;

import intervalTree.IntervalTree;
import java.util.List;
import java.util.ArrayList;

public abstract class BoobleArt
{
	public static void Ejecutar()
	{
		// Leo los datos de entrada
		ArrayList<Instancia> instancias = Parser.LeerInstancias();
		ArrayList<Punto> consultas = Parser.LeerConsultas();
		
		// Elementos de cada instancia
		IntervalTree arbol_x, arbol_y;
		ArrayList<Intervalo> intervalos_x, intervalos_y;
		
		// Proceso cada instancia
		for(Instancia instancia: instancias)
		{
			intervalos_x = new ArrayList<Intervalo>();
			intervalos_y = new ArrayList<Intervalo>();
			
			// Cargo los intervalos
			for(Imagen img: instancia.imagenes)
			{
				intervalos_x.add(img.x);
				intervalos_y.add(img.y);
			}
			
			// Creo los arboles
			arbol_x = new IntervalTree(intervalos_x);
			arbol_y = new IntervalTree(intervalos_y);
			
			// Realizo las consultas
			for(Punto p: consultas)
			{
				BuscarInterseccion(p, arbol_x, arbol_y,instancia.imagenes);
			}
		}
	}

	private static List<Imagen> BuscarInterseccion(Punto p, IntervalTree arbol_x, IntervalTree arbol_y, List<Imagen> imagenes)
    {
		List<Intervalo> listaX = arbol_x.BuscarInterseccion(p.x);
		List<Intervalo> listaY = arbol_y.BuscarInterseccion(p.y);
		
		List<Integer> resultado = new ArrayList<Integer>();
		
		int i = 0;
		
		while(i < listaX.size())
		{
			int k = 0;
			while(k < listaY.size())
			{
				if(listaX.get(i).id == listaY.get(k).id)
				{
					resultado.add(listaX.get(i).id);
					break;
				}else{
					k++;
				}
			}
			i++;
		}
		
		//EN RESULTADOS TENES LA LISTA DE LOS ID DE LAS FOTOS QUE SE MUESTRAN		
		
		return null;
    }
}
