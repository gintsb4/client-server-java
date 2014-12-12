import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class Main {

	public static void main(String[] args) {
		
		
		try {
			  UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
		//Database Frame
		JFrame frame = new JFrame("Gintsb4 Invoice");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400,200);	

		final Inventory inventory = new Inventory();
		
		//Database Panels
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		//Keypad Frame
		JFrame keypad = new JFrame("Gintsb4 Stock");
		keypad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		keypad.setLocation(150,200);
		
		//Keypad Panel
		JPanel panel5 = new JPanel();
		
		/**********************************************/
		
		
		//Panel 1 Company Details
		panel1.setLayout(new MigLayout("", "[]", "[][][][][][][][][]"));
		panel1.setBorder(BorderFactory.createTitledBorder("Company"));
		final JTextArea stockArea = new JTextArea(12,20);
		JScrollPane scroll = new JScrollPane (stockArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JLabel blankLabel = new JLabel("hi");
		JLabel blankLabel2 = new JLabel("hi");
		JLabel blankLabel3 = new JLabel("hi");
		JLabel blankLabel4 = new JLabel("hi");
		JLabel blankLabel5 = new JLabel("hi");
		JLabel blankLabel6 = new JLabel("Current Time");

		blankLabel.setVisible(false);
		blankLabel2.setVisible(false);
		blankLabel3.setVisible(false);
		blankLabel4.setVisible(false);
		blankLabel5.setVisible(false);
//		blankLabel6.setVisible(false);

		panel1.add(new JLabel("Gintsb4 Hardware"),"cell 0 0,alignx center");
		panel1.add(new JLabel("Shop Street,"),"cell 0 1,alignx center");
		panel1.add(new JLabel("Galway"),"cell 0 2,alignx center");
		panel1.add(new JLabel("091 - 123456"),"cell 0 3,alignx center");
		panel1.add(blankLabel,"cell 0 4, wrap");
		panel1.add(new JLabel("Stock:"),"flowx, cell 0 5,alignx left");
		panel1.add(scroll, "cell 0 7");
	
		
		//Panel 2 Costumer Details
		panel2.setLayout(new MigLayout());
		panel2.setBorder(BorderFactory.createTitledBorder("Customer Details"));
		JLabel firstnameLabel = new JLabel("First Name: ");
		JLabel surnameLabel = new JLabel("Surname: ");
		JLabel addressLabel = new JLabel("Address:");
		JLabel detailsLabel = new JLabel("Invoice:");
		
		final JTextField firstname = new JTextField(10);
		final JTextField surname = new JTextField(10);
		final JTextField address = new JTextField(10);
		final JTextArea detailsArea = new JTextArea(200,200); //y x
		

		try {
			FileReader reader = new FileReader( "./res/invoice.txt" );
			BufferedReader br = new BufferedReader(reader);
			detailsArea.read(br, null);
			detailsArea.requestFocus();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			inventory.parseXML(new File("./res/inventory.txt"));
			stockArea.setText(inventory.generateList());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane detailScroll = new JScrollPane (detailsArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//detailsArea.setBorder(BorderFactory.createEtchedBorder());
		panel2.add(firstnameLabel, "left, sg 1, split");	//Align left, ????, split cell
		panel2.add(firstname, "pushx, growx, wrap");		//????, can grow, finish row
		panel2.add(surnameLabel, "left, sg 1, split");
		panel2.add(surname, "pushx, growx, wrap");
		panel2.add(addressLabel, "left, sg 1, split");
		panel2.add(address, "pushx, growx, wrap");
		panel2.add(blankLabel2,"wrap");
		panel2.add(detailsLabel,"left, wrap");
		panel2.add(detailScroll, "push, grow, wrap");
		
		
		//Panel 3 Item Details
		String[] taxRates = {"23%","21%","NO VAT"};
		
		panel3.setLayout(new MigLayout(""));
		panel3.setBorder(BorderFactory.createTitledBorder("Purchases"));
		JLabel itemLabel = new JLabel("Item: ");
		final JTextField item = new JTextField(8);
		JLabel amountLabel = new JLabel("Amount: ");
		JTextField amount = new JTextField(8);
		JLabel taxLabel = new JLabel("Tax: ");
		final JComboBox tax = new JComboBox(taxRates);
		JLabel priceLabel = new JLabel("Price: €");
		JTextField price = new JTextField(8);
		JButton submitBtn = new JButton("Submit");
		JButton printBtn = new JButton("Print");
		JButton clearBtn = new JButton("Clear");
		JLabel totalLabel = new JLabel("Total: €");
		JTextField total = new JTextField(8);
		
		//Date Label
		SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd/MM/yyyy");
		Date d = new Date();
		JLabel date = new JLabel("Date: " + dateFormat.format(d).toString());
		
		
		
		panel3.add(itemLabel,"left, sg 1, split");
		panel3.add(item,"wrap");
		panel3.add(amountLabel,"left, sg 1, split");
		panel3.add(amount,"wrap");
		panel3.add(taxLabel,"left, sg 1, split");
		panel3.add(tax,"wrap");
		panel3.add(priceLabel,"left, sg 1, split");
		panel3.add(price,"wrap");
		panel3.add(blankLabel3,"wrap");
		panel3.add(totalLabel,"left, sg 1, split");
		panel3.add(total, "wrap");
		panel3.add(submitBtn,"left, split");
		panel3.add(printBtn);
		panel3.add(clearBtn,"center, wrap");
		panel3.add(blankLabel5, "wrap");
		panel3.add(blankLabel6, "wrap");
		
		
		
		panel4.setLayout(new MigLayout("", "[]", "[][][][]"));
		panel4.setBorder(BorderFactory.createEtchedBorder());
		panel4.setBackground(Color.WHITE);
		panel4.add(new MainClock());
		panel3.add(panel4,"wrap");
		panel3.add(date);
		
		//Panel 4 Add Item
		
		String[] addRemove = {"Add Item", "Remove Item"};
		
		panel5.setLayout(new MigLayout("","40[]40[]"));
		panel5.setBorder(BorderFactory.createTitledBorder("Add/Remove Item"));
		final JComboBox addRemoveCombo = new JComboBox(addRemove);
		JLabel itemNameLabel = new JLabel("Name:");
		final JTextField itemName = new JTextField(8);
		JLabel itemPriceLabel = new JLabel("Price:");
		final JTextField itemPrice = new JTextField(8);
		JLabel stockAmountLabel = new JLabel("Amount:");
		final JTextField stockAmount = new JTextField(8);
		JButton addItemBtn = new JButton("Submit");
		
		
		panel5.add(addRemoveCombo,"wrap");
		panel5.add(itemNameLabel,"growx, split, sg 1");
		panel5.add(itemName,"wrap");
		panel5.add(itemPriceLabel,"split, sg 1");
		panel5.add(itemPrice,"wrap");
		panel5.add(stockAmountLabel,"split, sg 1");
		panel5.add(stockAmount,"wrap");
		panel5.add(blankLabel4,"wrap");
		panel5.add(addItemBtn,"center");
		
		
		
		/*********************************************/
		
		
		frame.getContentPane().add(panel1, BorderLayout.WEST);
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		frame.getContentPane().add(panel3, BorderLayout.EAST);
		frame.pack();
		frame.setSize(750,500);
		frame.setVisible(true);
		
		
		keypad.getContentPane().add(panel5, BorderLayout.CENTER);
		keypad.setSize(250,240);
		//keypad.pack();
		keypad.setVisible(true);
		
		
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				detailsArea.setText("");
				
			}
			
		});
		
		submitBtn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tax.toString());
				Invoice invoice = new Invoice(firstname.getText()+ " " + surname.getText(),
											  address.getText(),tax.toString());
				detailsArea.setText(invoice.returnInvoice());
				detailsArea.append("\n" + item.getText());
				try{
					FileWriter out = new FileWriter("./res/invoice.txt");
					PrintWriter print = new PrintWriter(out);
					
					print.print(invoice.returnInvoice());
					print.flush();
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
				
			}	
			
	});		addItemBtn.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try{
			if(addRemoveCombo.toString().contains("Add Item"))
			{
				inventory.addItem(itemName.getText(), Integer.parseInt(stockAmount.getText()),
								(float)Double.parseDouble(itemPrice.getText()));
			}
			if(addRemoveCombo.toString().contains("Remove Item"))
			{
				inventory.removeItem(itemName.getText(), Integer.parseInt((stockAmount.getText())));
			}
			System.out.println("submit item" + itemName.getText() +stockAmount.getText() + itemPrice.getText());
			
			stockArea.setText(inventory.generateList());
			}catch(Exception e)
			{
				System.out.println("submit item" + itemName.getText() +stockAmount.getText() + itemPrice.getText());
				
				e.printStackTrace();
			}
		}	
		
});
	}

}
