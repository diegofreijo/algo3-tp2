package utilidades;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas
{
	public long armado, consulta, fusion, fb;
	public List<Long> consultas, fbs;
	public List<List<Long>> fusiones; 
	public int cant_img, cant_consultas;
	public long intersecciones_fusion;
	
	public Estadisticas(int cant_img, int cant_consultas)
	{
		Resetear();
		this.cant_img = cant_img;
		this.cant_consultas = cant_consultas;
		consultas = new ArrayList<Long>(cant_consultas);
		fusiones = new ArrayList<List<Long>>(cant_consultas);
		fbs = new ArrayList<Long>(cant_consultas);
	}

	public void Resetear()
    {
		armado = 0;
		consulta = 0;
		fusion = 0;
		intersecciones_fusion = 0;
		fb = 0;
    }

	// Guarda los valores de consulta y fusion y los resetea
	public void Almacenar()
    {
		consultas.add(consulta);
		
		List<Long> fusion_actual = new ArrayList<Long>(2);
		fusion_actual.add(intersecciones_fusion);
		fusion_actual.add(fusion);
		fusiones.add(fusion_actual);
		
		consulta = 0;
		fusion = 0;
		intersecciones_fusion = 0;
    }

	public void AlmacenarFB()
    {
	    fbs.add(fb);
	    fb = 0;
    }
}
