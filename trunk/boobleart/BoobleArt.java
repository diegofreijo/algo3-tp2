package boobleart;

import intervalTree.IntervalTree;
import java.util.List;
import java.util.ArrayList;

public abstract class BoobleArt
{
	public static void Ejecutar()
	{
		// Leo los datos de entrada
		//System.out.println("Leyendo entrada...");
		ArrayList<Instancia> instancias = Parser.LeerInstancias();
		ArrayList<Punto> consultas = Parser.LeerConsultas();

		// Elementos de cada instancia
		IntervalTree arbol_x, arbol_y;
		List<Intervalo> intervalos_x, intervalos_y;
		List<Consulta> resultados, resultados_fb;

		// Proceso cada instancia
		for (Instancia instancia : instancias)
		{
			//System.out.println("Construyendo instancia...");
			intervalos_x = new ArrayList<Intervalo>();
			intervalos_y = new ArrayList<Intervalo>();

			// Cargo los intervalos
			for (Imagen img : instancia.imagenes)
			{
				intervalos_x.add(img.x);
				intervalos_y.add(img.y);
			}

			// Creo los arboles
			arbol_x = new IntervalTree(intervalos_x);
			arbol_y = new IntervalTree(intervalos_y);

			// Realizo las consultas
			//System.out.println("Consultando...");
			resultados = new ArrayList<Consulta>();
			for (Punto p : consultas)
			{
				resultados.add(BuscarInterseccion(p, arbol_x, arbol_y, instancia.imagenes));
			}

			// Ejecuto el algoritmo por fuerza bruta y comparo
			//System.out.println("Ejecutando fuerza bruta...");
			resultados_fb = new ArrayList<Consulta>();
			resultados_fb = BuscarInterseccionFB(instancia, consultas);
			for(int i = 0; i < resultados.size(); ++i)
			{
				if (!Comparar(resultados.get(i), resultados_fb.get(i)))
				{
					System.out.println("Son diferentes... =(");
					System.out.println("Consulta:	" + consultas.get(i));
					System.out.println("Imagenes:	" + instancia.imagenes);
					System.out.println("eficiente:	" + resultados.get(i).toString());
					System.out.println("fuerza bruta:	" + resultados_fb.get(i).toString());
					System.out.println("=========================================");
				}
				/*else
				{
					System.out.println("¡Son iguales! =)");
					System.out.println("resultados:	" + resultados.get(i).toString());
					System.out.println("=========================================");
				}*/
			}
		}
	}

	private static Consulta BuscarInterseccion(Punto p, IntervalTree arbol_x, IntervalTree arbol_y, List<Imagen> imagenes)
	{
		List<Intervalo> intersecciones_x = arbol_x.BuscarInterseccion(p.x);
		List<Intervalo> intersecciones_y = arbol_y.BuscarInterseccion(p.y);
		Consulta consulta = new Consulta();

		// Busco ids iguales en ambas intersecciones
		for (int i = 0; i < intersecciones_x.size(); ++i)
		{
			for (int j = 0; j < intersecciones_y.size(); ++j)
			{
				if (intersecciones_x.get(i).id == intersecciones_y.get(j).id)
				{
					// Tengo un resultado, agrego la imagen correspondiente al
					// id encontrado
					consulta.imagenes.add(imagenes.get(intersecciones_x.get(i).id));
					break;
				}
			}
		}

		return consulta;
	}

	private static List<Consulta> BuscarInterseccionFB(Instancia instancia, ArrayList<Punto> consultas)
	{
		List<Consulta> resultados = new ArrayList<Consulta>();
		Consulta actual;

		for (Punto p : consultas)
		{
			actual = new Consulta();
			for (Imagen img : instancia.imagenes)
			{
				if (img.Pertenece(p))
				{
					actual.imagenes.add(img);
				}
			}
			resultados.add(actual);
		}

		return resultados;
	}

	private static boolean Comparar(Consulta resultados, Consulta resultados_fb)
    {
		for(Imagen img: resultados.imagenes)
		{
			if(!resultados_fb.imagenes.contains(img))
				return false;
		}
		
		for(Imagen img: resultados_fb.imagenes)
		{
			if(!resultados.imagenes.contains(img))
				return false;
		}
		
		return true;
    }
}
