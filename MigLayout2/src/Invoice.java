import java.text.DecimalFormat;
import java.util.ArrayList;


public class Invoice {
	
	private String name;
	private String address;
	private int amount;
	private String items;
	private float tax;
	private float total;
	private ArrayList<Item> item;
	
	public Invoice(String pname,String paddress,String ptax){
		name = pname;
		address=paddress;
		float value=0;
		if(ptax.contains("23%"))
			value = 23;
		else if (ptax.contains("21%"))
			value = 21;
		else 
			value = 0;
		
		tax = value;
		
		items = "";
	}
	
	public void updateInvoice(String item, String cost, String amount)
	{
		if(items.equals(""))
		items = item;
		else
		items = item;
		float cost2 = Float.parseFloat(cost);
		this.amount = Integer.parseInt(amount);
		total +=cost2 * this.amount;
	}
	
	public String returnInvoice()
	{
		String returnString;
		DecimalFormat df = new DecimalFormat("#.00");
		returnString = name + "\n" + address + "\nItems purchased:\n";
		
		returnString += "\nItem: " + items + "\n";
		returnString += "Amount: " + amount + "\n";
		returnString += "Cost = " + total + "\nTax = " + tax;
		returnString += "\nAmount due: "+String.format("%.2f",(total+((total/100)*tax)));
		
				
		return returnString;
	}

}
