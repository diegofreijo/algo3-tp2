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
				BuscarInterseccion(p, arbol_x, arbol_y);
			}
		}
	}

	private static List<Imagen> BuscarInterseccion(Punto p, IntervalTree arbol_x, IntervalTree arbol_y)
    {
		return null;
    }
}
