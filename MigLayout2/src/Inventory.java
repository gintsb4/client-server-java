import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory()
	{
		items = new ArrayList();
		
	}
	
	public void addItem(String name, int quantity, float cost)
	{
		for(int i=0; i<items.size(); i++)
		{
			if(items.get(i).checkItem(name))
			{
				items.get(i).updateStock(quantity);
				return;
			}
		}
		items.add(new Item(name,quantity,cost));
	}
	
	public void removeItem(String name, int quantity)
	{
		for(int i=0; i<items.size(); i++)
		{
			if(items.get(i).checkItem(name))
			{
				items.get(i).Purchase(quantity);
				return;
			}
		}
	}
	
	public String generateList()
	{
		String itemString = new String();

		System.out.println(items.size());
		if(items.size() > 0)
			for(int i=0; i<items.size(); i++)
			{
				itemString +=items.get(i).inventory();
			}
		
		return itemString;
	}
	
	public void parseXML(File XML)
	{
		try {
			FileReader reader = new FileReader(XML.getAbsolutePath());
			BufferedReader buf = new BufferedReader(reader);
			
			while(buf.ready())
			{
				String line = buf.readLine();
				String[] elements = line.split("\t");
				if(elements.length == 3){
						
					System.out.println(elements[1] + "\t" + elements[2]+ "\t" + elements.length );
					int amount = Integer.parseInt(elements[2]);
					double cost = Double.parseDouble(elements[1]);
					
					items.add(new Item(elements[0],amount,(float)cost));
				}
			}
			buf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void outputXML(File XML)
	{
		try {
			PrintWriter buf = new PrintWriter(XML.getAbsolutePath());
			
			for(int i=0;i<items.size();i++)
			{
				buf.println(items.get(i).values());
				buf.flush();
			}
			buf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
