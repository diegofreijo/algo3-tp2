package utilidades;

import java.util.LinkedList;
import java.util.List;

// Clase utilizada para informar al Parser las diferencias encontradas entre nuestro
// algoritmo y el de fuerza bruta para cierta instancia
public class Diferencias
{
	public Instancia instancia;
	public List<Punto> consultas;
	public List<ResultadoConsulta> resultados, resultados_fb;
	
	public Diferencias(Instancia instancia)
	{
		this.instancia = instancia;
		this.consultas = new LinkedList<Punto>();
		this.resultados = new LinkedList<ResultadoConsulta>();
		this.resultados_fb = new LinkedList<ResultadoConsulta>(); 
	}
	
	public String toString()
	{
		String ret = "";
		if(consultas.size() > 0)
		{
			ret += "Imagenes:	" + instancia.imagenes.toString() + "\n";
			for(int i = 0; i < consultas.size(); ++i)
			{
				ret += "-----------------\n";
				ret += "  Consulta:	" + consultas.get(i).toString() + "\n";
				ret += "  Eficiente:	" + resultados.get(i).imagenes.toString() + "\n";
				ret += "  Fuerza Bruta:	" + resultados_fb.get(i).imagenes.toString() + "\n";
			}
			ret += "======================================================" + "\n";
		}
		
		return ret;
	}
}
