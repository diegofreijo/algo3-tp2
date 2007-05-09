package utilidades;

public class Intervalo
{
	public Integer inicio,fin;
	public Imagen padre;
	
	public Intervalo(Integer inicio, Integer fin, Imagen padre)
	{
		this.inicio = inicio;
		this.fin = fin;
	}

	public String toString()
	{
		return String.valueOf(inicio) + " " + String.valueOf(fin);
	}
}
