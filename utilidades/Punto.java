package utilidades;

public class Punto
{
	public int x,y;
	
	public Punto(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
	}
}
