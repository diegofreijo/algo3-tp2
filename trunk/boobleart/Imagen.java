package boobleart;

public class Imagen
{
	public Intervalo x, y;
	
	public Imagen(Intervalo x, Intervalo y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return x.toString() + " " + y.toString(); 
	}
}
