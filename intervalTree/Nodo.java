package intervalTree;

import java.util.List;

public class Nodo
{
	public int pivot;
	public List<List<Integer>> inicio;
	public List<List<Integer>> fin;
	public IntervalTree menores;
	public IntervalTree mayores;
	
	public Nodo(int pivot, List<List<Integer>> inicio, List<List<Integer>> fin, IntervalTree menores, IntervalTree mayores)
	{
		this.pivot = pivot;
		this.inicio = inicio;
		this.fin = fin;
		this.mayores = mayores;
		this.menores = menores;	
	}
}
