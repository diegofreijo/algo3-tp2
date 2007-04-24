package boobleart;

public class Intervalo
{
	public long inicio,fin;
		
	public Intervalo(long inicio, long fin)
	{
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public String toString()
	{
		return String.valueOf(inicio) + " " + String.valueOf(fin);
	}
}
