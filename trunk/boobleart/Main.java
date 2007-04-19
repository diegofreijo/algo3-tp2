package boobleart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main
{
	public static void main(String[] args)
	{
		Map<Integer,List<Integer>> t = new TreeMap<Integer,List<Integer>>();

		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1); arr.add(2);
		
		t.put(0,arr);
		
		System.out.println(t.get(0));
		System.out.println(t.size());
		System.out.println(t.toString());
	}

}
