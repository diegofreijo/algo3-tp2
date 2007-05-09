package redblack_tree;

import interval_tree.*;

public class Nodo2
{
	public Nodo2 izq;
	public Nodo2 der;
	public Nodo2 padre;
	public int color;
	public int key;
	public Nodo valor;

	public void construir(int k, Nodo val)
	{
		this.key = k;
		this.valor = val;
		this.color = 0;
		izq = null;
		der = null;
		padre = null;
	}

	public void copia(Nodo2 n)
	{
		this.key = n.key;
		this.valor = n.valor;
		this.color = n.color;
	}

	public Nodo2 izq()
	{
		return this.izq;
	}

	public Nodo2 der()
	{
		return this.der;
	}

	public Nodo2 padre()
	{
		return this.padre;
	}

	public Nodo2 abuelo()
	{
		return this.padre.padre;
	}

	public Nodo2 tio()
	{
		if (this.padre == this.padre.padre.izq) return this.padre.padre.der;
		else return this.padre.padre.izq;
	}
}
