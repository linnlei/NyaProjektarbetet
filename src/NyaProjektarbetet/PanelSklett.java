package NyaProjektarbetet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelSklett {
	private GameEngine engine;
	private JPanel panelClickable;
	private UserInterface ui;
	private ShopController shopControl;
	private ArrayList<JButton> itemButtons = new ArrayList<JButton>();
	
	//public Room center;
	//public Shop shop;
	//public Room garden;
	//public Room miniGame1;
	
	public PanelSklett(GameEngine e, UserInterface ui)
	{
		engine = e;
		this.ui = ui;
		/*center = new Center();		//har flyttat dessa till gameengine istället
		garden = new Garden();		
		miniGame1 = new MiniGame();	*/
		shopControl = new ShopController(engine, this);
	}
	
	private JPanel getInventoryPanel()
	{
		panelClickable = createInventoryPanel();
		return panelClickable;
		
	}
	
	 private void reSize(int windowchanges)
	 {
		 
	 }
	
	public JPanel getPanel(String current)
	{
		if(current.equals("center")) panelClickable = createCenterPanel();
		else if(current.equals("shop")) panelClickable = createShopPanel();
		else if(current.equals("garden")) panelClickable = createGardenPanel();
		else panelClickable = createMiniGamePanel();
		
		return panelClickable;
	}
	
	private JPanel createCenterPanel()
	{
	   	JPanel panel = new JPanel();    
	    panel.setOpaque(false);
	    //panel.setLayout(new GridLayout(4,4));
	    panel.setLayout(null);
	    

	    //Tar in skyltbilden, skalar om den, sätter som ikon till JButton
    	URL imageURL = this.getClass().getClassLoader().getResource("skylt.png");
    	ImageIcon icon = new ImageIcon(imageURL);
	    Image img = icon.getImage();
	    Image newimg = img.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH);
	    ImageIcon newIcon = new ImageIcon(newimg);
	    
	    JButton clickButton = new JButton ("Affär", newIcon);
	    clickButton.setHorizontalTextPosition(JButton.CENTER);
	    clickButton.setVerticalTextPosition(JButton.CENTER);
	    
	    clickButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//ui.changeRoom("shop");					//strängar ist. för rumsreferenser
				//engine.changeCurrentRoom(engine.shop);	//rumsreferenser ist. för strängar
				engine.changeRoom("shop");				//flyttat till engine
				
			}
		});
	    clickButton.setBounds(200,100,200,100); //x,y,width,height
	    clickButton.setContentAreaFilled(false);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton);
	    
	    JButton clickButton2 = new JButton ("Trädgård");
	    clickButton2.setBounds(300,400,200,200);
	    clickButton2.setContentAreaFilled(false);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton2);
	    
	    clickButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//ui.changeRoom("garden");					//strängar ist. för rumsreferenser
				//engine.changeCurrentRoom(engine.garden);	//rumsreferenser ist. för strängar
				engine.changeRoom("garden");				//flyttat till engine
				
			}
		});
	    
	   	    
	    return panel;
    
    
	}
	
//*************************************************************************************************
//*************************************SHOP-PANEL START*********************************************
//*************************************************************************************************
	private JPanel createShopPanel()
	{
		HashMap<Item, Boolean> shopItems = engine.shop.getShopItems();
		
		//**************************Skapa paneler**************************
		JPanel panel = new JPanel();    
		JPanel gridPanel = new JPanel(); 
		JPanel downPanel = new JPanel(); 
		
	    panel.setOpaque(false);
	    gridPanel.setOpaque(false);
	    downPanel.setOpaque(false);
	    
	    panel.setLayout(new BorderLayout());
	    downPanel.setLayout(new GridLayout(1,10));
	    gridPanel.setLayout(new GridLayout(5,5,40,40));
	    
	    downPanel.setPreferredSize(new Dimension(100, 100)); 
	    
	    
	    //**************************Skapa knappar**************************
	    //Ska lägga till funktion som gör oköpbara föremål typ grå eller liknande
	    
	    for(Item item : shopItems.keySet() ){ 
	    	URL imageURL = this.getClass().getClassLoader().getResource(item.getItemPicture());
	    	ImageIcon icon = new ImageIcon(imageURL);
	    	JButton tempButton = new JButton (item.getItemName(), icon);
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);

	    	
	    	tempButton.addActionListener(new ActionListener()  {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String clickedItem = new String();			//Sträng för att spara namnet på det man klickat på
					for(JButton button : itemButtons){			//Letar igenom vilken knapp man klickat på
						if(arg0.getSource().equals(button)){	//När knappen hittats...
							clickedItem = button.getText();		//...så sparar man föremålets namn för den knappen
						}
					}
					String inputValue = JOptionPane.showInputDialog("Hur många vill du köpa?");
					shopControl.buyControl(inputValue, clickedItem);	//Startar controllern med föremålet samt antal
				}
			});
	    	
	    	gridPanel.add(tempButton);	//lägger till knappen
	    	itemButtons.add(tempButton); //sparar knappen i en arraylist för att man ska kunna leta igenom knapparna
		}
	    
	  //**************************Skapa tomma knappar för resten av utrymmena**************************
	    int buffer = 25 - shopItems.size();
	    int j = 0;
	    for(j = 1; j <= buffer; j++ ){ //lägger till tomma knappar för att fylla skärmen
	    	JButton tempButton = new JButton ("Item nr " + j);
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);
	    	gridPanel.add(tempButton);
	    	itemButtons.add(tempButton); //för att kunna iterera genom knapparna, om det behövs senare...
		}
	    
	  //**************************Övriga menyknappar etc**************************
	    JButton centrumButton = new JButton ("Tillbaka till centrum");
	    centrumButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ui.changeRoom("center");
				//engine.changeCurrentRoom(engine.center);		//rumsreferenser ist. för strängar
				engine.changeRoom("center");				//flyttat till engine
				
			}
		});
	    centrumButton.setContentAreaFilled(false);
	    //centrumButton.setBounds(4,6,100,200);
	    //centrumButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(centrumButton);
	    
	    JButton gardenButton = new JButton ("Till trädgården");
	    gardenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ui.changeRoom("garden");
				//engine.changeCurrentRoom(engine.garden);		//rumsreferenser ist. för strängar
				engine.changeRoom("garden");				//flyttat till engine
				
			}
		});
	    gardenButton.setContentAreaFilled(false);
	    //gardenButton.setBounds(4,6,100,200);
	    //gardenButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(gardenButton);
	    
	    JButton inventoryButton = new JButton ("Ryggsäck");
	    inventoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Insert inventory", "Ryggsäck", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
	    inventoryButton.setContentAreaFilled(false);
	    //clickButton2.setBounds(300,400,200,200);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(inventoryButton);
	    
	    panel.add(gridPanel, BorderLayout.CENTER);
	    panel.add(downPanel, BorderLayout.SOUTH);

	    return panel;
	}
//*************************************************************************************************
//*************************************SHOP-PANEL END***********************************************
//*************************************************************************************************
	
	
	private JPanel createGardenPanel()
	{
		JPanel panel = new JPanel(); 
		panel.setOpaque(false);
	    panel.setLayout(null);
	    int rad = 0;
	    int column =0;
	    int i =0;
	    int j = 0;
	    
	    for( j=0;j<43;j++){    	
	    		    
		    for( i=0;i<23;i++)
		    {
		    		   
			    final JButton clickButton = new JButton ();
			    clickButton.setContentAreaFilled(false);
			    clickButton.setBounds(column,rad,30,30);
			     
			    clickButton.setBorderPainted(false);//Osynlig
			    clickButton.addActionListener(new ActionListener() {
			    	int option =1;
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						if(option ==1){
							option=0;
							clickButton.setContentAreaFilled(true);
						}
	
						else if(option ==0)
					    {
						option =1;
						clickButton.setContentAreaFilled(false);
						}
						
					}
					
			    });	
					
			    panel.add(clickButton);
			    
			    rad = rad + 30;
			    			   	    		    
		    }
		    /*rad = 0;
		    i=0;
		    for( i=0;i<23;i++)
		    {
		    		   
			    JButton clickButton = new JButton ();
			    clickButton.setContentAreaFilled(false);
			    clickButton.setBounds(30,rad,30,30);
			    //clickButton.setContentAreaFilled(false); Synlig för tillfället så jag ser vad jag gör
			    panel.add(clickButton);
			    
			    rad = rad + 30;
			    			   	    		    
		    }*/
		    column = column + 30;
		    i=0;
		    rad =0;
			 
			   	    		    
		}
	   
		    
		return panel;
			
	}
	
	private JPanel createMiniGamePanel()
	{
		JPanel panelClickable = new JPanel();
		
		panelClickable.setOpaque(false);
	    panelClickable.setLayout(null);
		
		return panelClickable;
	}
	
	private JPanel createInventoryPanel()
	{
		panelClickable.setOpaque(false);
	    panelClickable.setLayout(null);
		
		return panelClickable;
	}
	
	
}


