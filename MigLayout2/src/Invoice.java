import java.util.ArrayList;


public class Invoice {
	
	private String name;
	private String address;
	private String items;
	private double tax;
	private float total;
	private ArrayList<Item> item;
	
	public Invoice(String pname,String paddress,String ptax){
		name = pname;
		address=paddress;
		double value=0;
		if(ptax.contains("23%"))
			value = 23;
		else if (ptax.contains("21%"))
			value = 21;
		else 
			value = 0;
		
		tax = value;
		
		items = "";
	}
	
	public void updateInvoice(String item, float cost)
	{
		if(items.equals(""))
		items = items+ ",\n" + item + ": " +cost;
		else
		items = item+ ": " +cost;
		
		total +=cost;
	}
	
	public String returnInvoice()
	{
		String returnString;
		
		returnString = name + "\n" + address + "\nItems purchased:\n";
		returnString += items + "\n";
		returnString += "\nCost \t= " + total + "\nTax \t= " + tax;
		returnString += "\nAmount due: "+(total+((total/100)*tax));
				
		return returnString;
	}

}
