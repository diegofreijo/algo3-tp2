package boobleart;

public class Punto
{
	public long x,y;
	
	public Punto(long x, long y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
	}
}
