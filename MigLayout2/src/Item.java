
public class Item {
	private String name;
	private int quantity;
	private float cost;
	
	public Item(String n, int q, float c)
	{
		name = n;
		quantity = q;
		cost = c;
	}
	
	public boolean Purchase(int amount)
	{
		if(quantity - amount >= 0){
			quantity -= amount;
			return true;
		}
		else 
			return false;
	}
	
	public boolean checkItem(String item)
	{
		if(name.equalsIgnoreCase(item))
		{
			return true;
		}
		else return false;
	}
	
	public float getCost(int amount)
	{
		if(amount >0)
			return cost*amount;
		else 
			return 0;
	}
	
	public int updateStock(int amount)
	{
		if(amount >0){
			quantity =quantity+amount;
			return quantity;
		}
		else 
			return 0;
	}
	
	public String inventory()
	{
		return name + ": E" + cost + " \tX" + quantity + "\n"; 
	}
	
	public String values()
	{
		return name + "\t" + quantity + "\t" + cost;
	}

}