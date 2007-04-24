package boobleart;

import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Instancia> instancias = Parser.LeerInstancias();
		ArrayList<Punto> consultas = Parser.LeerConsultas();
		
		System.out.println("Instancias:");
		for(Instancia i: instancias)
		{
			System.out.println(i.toString());
		}
	
		System.out.println("Consultas:");
		for(Punto p: consultas)
		{
			System.out.println(p.toString());
		}
	}
	
	public ArrayList<Imagen> BuscarInterseccion(Punto p)
	{
		return null;
	}
}
