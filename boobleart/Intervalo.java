package boobleart;

public class Intervalo
{
	public Integer inicio,fin,id;
		
	public Intervalo(Integer inicio, Integer fin, Integer id)
	{
		this.inicio = inicio;
		this.fin = fin;
		this.id = id;
	}
	
	public Intervalo()
    {
    }

	public String toString()
	{
		return String.valueOf(inicio) + " " + String.valueOf(fin);
	}
}
