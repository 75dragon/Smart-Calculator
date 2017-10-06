package HW;

import java.util.HashMap;

public class caculatorData
{
	HashMap<String, String> allEqu;
	HashMap<String, Double> allVar;

	public caculatorData()
	{
		allEqu = new HashMap<String, String>();
		allVar = new HashMap<String, Double>();
	}

	public boolean addEqu(String ref, String equ)
	{
		if (!allEqu.containsKey(ref))
		{
			allEqu.put(ref, equ);
			return true;
		}
		return false;
	}
	
	public boolean addVar(String ref, Double equ)
	{
		if (!allVar.containsKey(ref))
		{
			allVar.put(ref, equ);
			return true;
		}
		return false;
	}
	
	public void clearAll()
	{
		allEqu = new HashMap<String, String>();
		allVar = new HashMap<String, Double>();
	}
}
