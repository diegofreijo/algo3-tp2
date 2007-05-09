package boobleart;

import interval_tree.IntervalTree;
import java.util.List;
import java.util.ArrayList;
import utilidades.Diferencias;
import utilidades.Estadisticas;
import utilidades.Imagen;
import utilidades.Instancia;
import utilidades.Intervalo;
import utilidades.Punto;
import utilidades.ResultadoConsulta;

public abstract class BoobleArt
{
	private static Estadisticas es;
	private static int cant_diferencias = 0;
	
	public static void Ejecutar(boolean comparar)
	{	
		// Leo los datos de entrada
		System.out.println("Leyendo entradas...");
		List<Instancia> instancias = Parser.LeerInstancias();
		List<Punto> consultas = Parser.LeerConsultas();

		// Verifico la validez de los parametros
		if(instancias.size() == 0 || consultas.size() == 0)
		{
			System.out.println("Error en los parametros de entrada");
			return;
		}
		
		// Elementos de cada instancia
		IntervalTree arbol_x, arbol_y;
		List<Intervalo> intervalos_x, intervalos_y;
		List<ResultadoConsulta> resultados, resultados_fb;

		// Limpio los archivos de salida
		Parser.LimpiarArchivos();
		
		
		// Proceso cada instancia
		System.out.println("Ejecutando...");
		for (Instancia instancia : instancias)
		{
			es = new Estadisticas(instancia.imagenes.size(), consultas.size());
			intervalos_x = new ArrayList<Intervalo>(); ++es.armado;
			intervalos_y = new ArrayList<Intervalo>(); ++es.armado;
			
			// Cargo los intervalos
			for (Imagen img : instancia.imagenes)
			{
				intervalos_x.add(img.x); ++es.armado;
				intervalos_y.add(img.y); ++es.armado;
			}

			// Creo los arboles
			arbol_x = new IntervalTree(intervalos_x, es); ++es.armado;
			arbol_y = new IntervalTree(intervalos_y, es); ++es.armado;
			
			// Realizo las consultas
			resultados = new ArrayList<ResultadoConsulta>();
			for (Punto p : consultas)
			{
				resultados.add(BuscarInterseccion(p, arbol_x, arbol_y, instancia.imagenes));
				es.Almacenar();
			}
			
			// Escribo los resultados para esta instancia
			Parser.EscribirResultados(resultados);
			
			// Si se me pidio, ejecuto el algoritmo por fuerza bruta y comparo
			if(comparar)
			{
				resultados_fb = new ArrayList<ResultadoConsulta>();
				resultados_fb = BuscarInterseccionFB(instancia, consultas);
				es.AlmacenarFB();
				Diferencias diferencias = new Diferencias(instancia);
				for(int i = 0; i < resultados.size(); ++i)
				{
					if (!Comparar(resultados.get(i), resultados_fb.get(i)))
					{
						diferencias.consultas.add(consultas.get(i));
						diferencias.resultados.add(resultados.get(i));
						diferencias.resultados_fb.add(resultados_fb.get(i));
						++cant_diferencias;
					}
				}
				Parser.EscribirDiferencias(diferencias);
			}
			
			// Escribo las estadisticas para esta instancia
			Parser.EscribirEstadisticas(es);
		}
		System.out.println("Instancias procesadas");
		if(comparar) System.out.println("Diferencias encontradas: " + cant_diferencias);
	}
	
	private static ResultadoConsulta BuscarInterseccion(Punto p, IntervalTree arbol_x, IntervalTree arbol_y, List<Imagen> imagenes)
	{
		List<Intervalo> intersecciones_x = arbol_x.BuscarInterseccion(p.x); ++es.fusion;
		List<Intervalo> intersecciones_y = arbol_y.BuscarInterseccion(p.y); ++es.fusion;
		ResultadoConsulta resultado = new ResultadoConsulta(); ++es.fusion;

		// Guardo la cantidad de intervalos encontrados para las estadisticas
		es.intersecciones_fusion = intersecciones_x.size() * intersecciones_y.size();
		
		// Busco ids iguales en ambas intersecciones
		for (int i = 0; i < intersecciones_x.size(); ++i)
		{
			++es.fusion;
			for (int j = 0; j < intersecciones_y.size(); ++j)
			{
				++es.fusion;
				++es.fusion;
				if (intersecciones_x.get(i).id == intersecciones_y.get(j).id)
				{
					// Tengo un resultado, agrego la imagen correspondiente al id encontrado
					resultado.imagenes.add(imagenes.get(intersecciones_x.get(i).id)); es.fusion+=3;
					break;
				}
			}
		}

		return resultado;
	}
	
	private static List<ResultadoConsulta> BuscarInterseccionFB(Instancia instancia, List<Punto> consultas)
	{
		List<ResultadoConsulta> resultados = new ArrayList<ResultadoConsulta>(); ++es.fb;
		ResultadoConsulta actual;

		for (Punto p : consultas)
		{
			++es.fb;
			actual = new ResultadoConsulta(); ++es.fb;
			for (Imagen img : instancia.imagenes)
			{
				++es.fb;
				++es.fb;
				if (img.Pertenece(p))
				{
					actual.imagenes.add(img); ++es.fb;
				}
			}
			resultados.add(actual); ++es.fb;
		}

		return resultados;
	}

	private static boolean Comparar(ResultadoConsulta resultados, ResultadoConsulta resultados_fb)
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
