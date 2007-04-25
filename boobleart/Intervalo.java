package boobleart;

public class Intervalo
{
	public Integer inicio,fin;
		
	public Intervalo(Integer inicio, Integer fin)
	{
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public Intervalo()
    {
    }

	public String toString()
	{
		return String.valueOf(inicio) + " " + String.valueOf(fin);
	}
}
