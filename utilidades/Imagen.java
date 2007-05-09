package utilidades;

public class Imagen
{
	public Intervalo x, y;
	public boolean seleccionado_x, seleccionado_y;
	
	public Imagen(Intervalo x, Intervalo y)
	{
		this.x = x;
		this.y = y;
		this.seleccionado_x = false;
		this.seleccionado_y = false;
	}
	
	public String toString()
	{
		return x.toString() + " " + y.toString(); 
	}

	public boolean Pertenece(Punto p)
    {
	    return 	(this.x.inicio <= p.x && p.x <= this.x.fin) && 
	    		(this.y.inicio <= p.y && p.y <= this.y.fin);
    }
}
