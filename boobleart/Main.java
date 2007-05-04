package boobleart;

import intervalTree.IntervalTree;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		// Leo los datos de entrada
		ArrayList<Instancia> instancias = Parser.LeerInstancias();
		ArrayList<Punto> consultas = Parser.LeerConsultas();
		
		// Genero los arboles
		IntervalTree arbol_x, arbol_y;
		ArrayList<Intervalo> intervalos_x, intervalos_y;
		
		for(Instancia instancia: instancias)
		{
			intervalos_x = new ArrayList<Intervalo>();
			intervalos_y = new ArrayList<Intervalo>();
			
			for(Imagen img: instancia.imagenes)
			{
				intervalos_x.add(img.x);
				intervalos_y.add(img.y);
			}
			
			//System.out.println("===============");
			//System.out.println(intervalos_x);
			arbol_x = new IntervalTree(intervalos_x);
			
			//arbol_y = new IntervalTree(intervalos_y);
			
			
		}
		
		// Realizo las consultas
		for(Punto p: consultas)
		{
			// TODO: realizo las consultas sobre los arboles
		}
		
	}
}
