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
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
import minTestzon.Center;
import minTestzon.GameEngine;
import minTestzon.Garden;
import minTestzon.GardenController;
import minTestzon.Inventory;
import minTestzon.MiniGame;
import minTestzon.Room;
import minTestzon.Shop;
import minTestzon.UserInterface;*/


public class PanelSklett implements Observer{
	private GameEngine engine;
	private JPanel panelClickable;
	private UserInterface ui;
	private ShopController shopControl;
	private ArrayList<JButton> itemButtons = new ArrayList<JButton>();
	
	/*public Room center;
	public Room shop;
	public Room garden;
	public Room miniGame1;
	public Room miniGame2;*/

	public GardenController gardenController;
	public Inventory in = new Inventory();
	JButton itemsLeft = null;
	
	//*******Experiment
	JButton kurt;
	String ex;
	
	
	public PanelSklett(GameEngine e, UserInterface ui)
	{
		engine = e;
		this.ui = ui;
		/*center = new Center();		//har flyttat dessa till gameengine ist�llet
		garden = new Garden();		
		miniGame1 = new MiniGame();	*/
		shopControl = new ShopController(engine, this);
		/*
		center = new Center();
		shop = new Shop();
		garden = new Garden();
		miniGame1 = new MiniGame();*/
		
		gardenController = new GardenController(engine.garden, in, engine);
		gardenController.addObserver(this);
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
	    

	    //Tar in skyltbilden, skalar om den, s�tter som ikon till JButton
    	URL imageURL = this.getClass().getClassLoader().getResource("skylt.png");
    	ImageIcon icon = new ImageIcon(imageURL);
	    Image img = icon.getImage();
	    Image newimg = img.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH);
	    ImageIcon newIcon = new ImageIcon(newimg);
	    
	    JButton clickButton = new JButton ("Aff�r", newIcon);
	    clickButton.setHorizontalTextPosition(JButton.CENTER);
	    clickButton.setVerticalTextPosition(JButton.CENTER);
	    
	    clickButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//ui.changeRoom("shop");					//str�ngar ist. f�r rumsreferenser
				//engine.changeCurrentRoom(engine.shop);	//rumsreferenser ist. f�r str�ngar
				engine.changeRoom("shop");				//flyttat till engine
				
			}
		});
	    clickButton.setBounds(200,100,200,100); //x,y,width,height
	    clickButton.setContentAreaFilled(false);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton);
	    
	    JButton clickButton2 = new JButton ("Tr�dg�rd");
	    clickButton2.setBounds(300,400,200,200);
	    clickButton2.setContentAreaFilled(false);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton2);
	    
	    clickButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//ui.changeRoom("garden");					//str�ngar ist. f�r rumsreferenser
				//engine.changeCurrentRoom(engine.garden);	//rumsreferenser ist. f�r str�ngar
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
	    //Ska l�gga till funktion som g�r ok�pbara f�rem�l typ gr� eller liknande
	    
	    for(Item item : shopItems.keySet() ){ 
	    	URL imageURL = this.getClass().getClassLoader().getResource(item.getItemPicture());
	    	ImageIcon icon = new ImageIcon(imageURL);
	    	JButton tempButton = new JButton (item.getItemName(), icon);
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);

	    	
	    	tempButton.addActionListener(new ActionListener()  {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String clickedItem = new String();			//Str�ng f�r att spara namnet p� det man klickat p�
					for(JButton button : itemButtons){			//Letar igenom vilken knapp man klickat p�
						if(arg0.getSource().equals(button)){	//N�r knappen hittats...
							clickedItem = button.getText();		//...s� sparar man f�rem�lets namn f�r den knappen
						}
					}
					String inputValue = JOptionPane.showInputDialog("Hur m�nga vill du k�pa?");
					shopControl.buyControl(inputValue, clickedItem);	//Startar controllern med f�rem�let samt antal
				}
			});
	    	
	    	gridPanel.add(tempButton);	//l�gger till knappen
	    	itemButtons.add(tempButton); //sparar knappen i en arraylist f�r att man ska kunna leta igenom knapparna
		}
	    
	  //**************************Skapa tomma knappar f�r resten av utrymmena**************************
	    int buffer = 25 - shopItems.size();
	    int j = 0;
	    for(j = 1; j <= buffer; j++ ){ //l�gger till tomma knappar f�r att fylla sk�rmen
	    	JButton tempButton = new JButton ("Item nr " + j);
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);
	    	gridPanel.add(tempButton);
	    	itemButtons.add(tempButton); //f�r att kunna iterera genom knapparna, om det beh�vs senare...
		}
	    
	  //**************************�vriga menyknappar etc**************************
	    JButton centrumButton = new JButton ("Tillbaka till centrum");
	    centrumButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ui.changeRoom("center");
				//engine.changeCurrentRoom(engine.center);		//rumsreferenser ist. f�r str�ngar
				engine.changeRoom("center");				//flyttat till engine
				
			}
		});
	    centrumButton.setContentAreaFilled(false);
	    //centrumButton.setBounds(4,6,100,200);
	    //centrumButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(centrumButton);
	    
	    JButton gardenButton = new JButton ("Till tr�dg�rden");
	    gardenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ui.changeRoom("garden");
				//engine.changeCurrentRoom(engine.garden);		//rumsreferenser ist. f�r str�ngar
				engine.changeRoom("garden");				//flyttat till engine
				
			}
		});
	    gardenButton.setContentAreaFilled(false);
	    //gardenButton.setBounds(4,6,100,200);
	    //gardenButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(gardenButton);
	    
	    JButton inventoryButton = new JButton ("Ryggs�ck");
	    inventoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Insert inventory", "Ryggs�ck", JOptionPane.INFORMATION_MESSAGE);
				
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
	    JPanel showInventory;
	    
	    int rad = 0;
	    int column =0;
	    int lopnr=0;
	    
	    
	    HashMap<ImageIcon, Integer> inventory = gardenController.getInventory();
        Set<Entry<ImageIcon, Integer>> pairs = inventory.entrySet();
	    
	    // Det osynlliga rutn�tet med knappar
	    
	    for( int j=0;j<37;j++){    	
	    		    
		    for( int i=0;i<23;i++)
		    {
		    	lopnr++;// Varje knapp f�r ett eget nummer			    		    
			    final int nr = lopnr;
			    
			    String s = "buildable"; // Kom ih�g om det finns en tegelsten h�r eller inte...
			    if(gardenController.getIcon(nr)!= null)  s= "unbuildable";
			    final String startState = s;
			    
			    			     
		    	final ImageIcon icon3= gardenController.getIcon(nr);
		    	
		    	
			    
			    final JButton clickButton = new JButton(icon3);
			    //setResizable(false);// Testa senare
			    clickButton.setBounds(column,rad,30,30);
			    clickButton.setContentAreaFilled(false);//Osynlighet
			    clickButton.setBorderPainted(false);//Osynlighet
			    clickButton.addActionListener(new ActionListener() {
			    	
			    	String state = startState;
			    	ImageIcon takenImage; 
			    	//String state ="buildable";
			    	@Override
					public void actionPerformed(ActionEvent arg0) {
			    		
			    		
						if(state.equals("buildable"))
						{
							takenImage = gardenController.getTakenImage();
							if(takenImage!=null)
							{
								state="unbuildable";
								//System.out.println("Bilden som f�ljde med till Panelskletts rutn�t �r: : " +takenImage );
								clickButton.setIcon(takenImage);
								gardenController.build(nr);
								takenImage = null;
							}
														
						}
	
						else if(state.equals("unbuildable"))
					    {
							state="buildable";
							clickButton.setIcon(null);
							clickButton.setContentAreaFilled(false);
							gardenController.remove(nr);
						}
					}
					
			    });	
					
			    panel.add(clickButton);
			    
			    rad = rad + 30;
			    			   	    		    
		    }
		   
		    column = column + 30;
		    rad =0;
						   	    		    
		}
	  //**************************************************************************************************** 	
	    
	    //Panelen vid sidan av rutn�tet
	    showInventory = new JPanel();
	    showInventory.setLayout(null);
	    showInventory.setBounds(1110,0, 81, 1000);
	    
	    // Nollst�ller f�r ny loop
	     rad = 30;
	     column = 30;
	   
	 
	        
	   
	        //Fixar fram det som ska visas p� sidopanelen
	        for(Entry<ImageIcon, Integer> entry: pairs){
	    	
	    	// Visa bild p� tillg�nliga Item
	    	final ImageIcon imageOfItem = entry.getKey();
	    	//System.out.println("imageOfItem i sidopanelen" +imageOfItem );
	    	final JButton showItem;
	    	showItem = new JButton(imageOfItem);
		    showItem.setBounds(column, rad,30,30);
		    
		    //Vad bildknappen ska g�ra
		    
		    showItem.addActionListener(new ActionListener() {
		    	    	
		    	@Override
				public void actionPerformed(ActionEvent arg0) {
											
						gardenController.take(imageOfItem);
						//System.out.println("Tagen bild i sidopanelen �r: " +imageOfItem );
				}
				
		    });	
		    
		    // Visa antal  tillg�nliga Items
		    int x =entry.getValue();
		    itemsLeft = new JButton();
		    itemsLeft.setText("" + x);
		    itemsLeft.setBounds(column-15, rad+30,60,30);
		    itemsLeft.setContentAreaFilled(false);//Osynlighet
		    itemsLeft.setBorderPainted(false);//Osynlighet
		    
		     
		    //*****************************************************************
		    
		   
		   		    
		    //L�gg till bild och antal 
		    showInventory.add(showItem);
		    //showInventory.add(itemsLeft);
		    
		    // Byt till ny position
		    rad= rad +60;
	    }
	        
	        //**************Experiment********************
		   
		    kurt = new JButton();
		    kurt.setBounds(0,rad +30 ,100,30);
		    kurt.setContentAreaFilled(false);//Osynlighet
		    kurt.setBorderPainted(false);//Osynlighet
		    
		    showInventory.add(kurt);
		    
		    
		   //*****************************************************************
		    
	     panel.add(showInventory);
	     return panel;
	     
	}
	
	
	
	private JPanel createMiniGamePanel()
	{
		JPanel panel = new JPanel();
		
		panel.setOpaque(false);
	    panel.setLayout(null);
		
		return panel;
		
	}
	
	private JPanel createInventoryPanel()
	{
		JPanel panel = new JPanel();	
		panel.setOpaque(false);
	    panel.setLayout(null);
		
		return panel;
		
	}
	
	public void update(Observable obj, Object arg)
	{
		if(obj instanceof GardenController && arg instanceof Integer)
			//itemsLeft.setText("" + arg);
			kurt.setText("kvar:"+ arg);
			
	}
	
}
	
	
	
	
	/*
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
			    //clickButton.setContentAreaFilled(false); Synlig f�r tillf�llet s� jag ser vad jag g�r
			    panel.add(clickButton);
			    
			    rad = rad + 30;
			    			   	    		    
		    }
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
	
	
}*/


