package NyaProjektarbetet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class PanelSklett {
	private GameEngine engine;
	public Room center;
	public Shop shop;
	public Room garden;
	public Room miniGame1;
	public Room miniGame2;
	JPanel panelClickable;
	private UserInterface ui;
	
	public PanelSklett(GameEngine e, UserInterface ui)
	{
		engine = e;
		this.ui = ui;
		center = new Center();
		//shop = new Shop(user.myInventory.getInventory());
		garden = new Garden();
		miniGame1 = new MiniGame();
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
	    JButton clickButton = new JButton ("Affär");
	    clickButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ui.changeRoom("shop");
				
			}
		});
	    clickButton.setBounds(4,6,200,400);
	    clickButton.setContentAreaFilled(false);
	    clickButton.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton);
	    
	    JButton clickButton2 = new JButton ("Skylt2");
	    clickButton2.setBounds(300,400,200,200);
	    clickButton2.setContentAreaFilled(false);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton2);
	    
	   	    
	    return panel;
    
    
	}
	
	private JPanel createShopPanel()
	{
		
		//HashMap<Integer, String> shopItems; // =shop.getShopItems();
		HashMap<Item, Boolean> shopItems = engine.shop.getShopItems();
		//HashMap<Item, Boolean> shopItems = shop.getShopItems();
		
		JPanel panel = new JPanel();    
		JPanel gridPanel = new JPanel(); 
		JPanel downPanel = new JPanel(); 
		ArrayList<JButton> itemButtons = new ArrayList<JButton>();
		
	    panel.setOpaque(false);
	    gridPanel.setOpaque(false);
	    downPanel.setOpaque(false);
	    
	    panel.setLayout(new BorderLayout());
	    downPanel.setLayout(new GridLayout(1,10));
	    gridPanel.setLayout(new GridLayout(5,5,40,40));
	    
	    downPanel.setPreferredSize(new Dimension(100, 100)); 
	    
	    int i = 1;
	    
	    /*
	    for(i = 1; i <= 25; i++){
	    	JButton tempButton = new JButton ("Item nr " + i);
	    	tempButton.setContentAreaFilled(false);
	    	gridPanel.add(tempButton);
	    	itemButtons.add(tempButton);
	    }*/
	    
	    for(Item item : shopItems.keySet() ){
	    	URL imageURL = this.getClass().getClassLoader().getResource(item.getItemPicture());
	    	ImageIcon icon = new ImageIcon(imageURL);
	    	JButton tempButton = new JButton (item.getItemName(), icon);
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);
	    	
	    	tempButton.addActionListener(new ActionListener()  {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String inputValue = JOptionPane.showInputDialog("Hur många vill du köpa?");
					JOptionPane.showMessageDialog(null, "Du vill köpa " + inputValue + " stycken.", "21 feb", JOptionPane.OK_CANCEL_OPTION);
					//anropa controllern med inputValue som argument (antal man vill köpa)
				}
			});
	    	
	    	gridPanel.add(tempButton);
	    	itemButtons.add(tempButton); //för att kunna iterera genom knapparna, om det behövs senare...
		}
	    
	    int buffer = 25 - shopItems.size();
	    int j = 0;
	    for(j = 1; j <= buffer; j++ ){ //lägger till tomma knappar för att fylla skärmen
	    	JButton tempButton = new JButton ("Item nr " + j);
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);
	    	gridPanel.add(tempButton);
	    	itemButtons.add(tempButton); //för att kunna iterera genom knapparna, om det behövs senare...
		}
	    
	    
	    JButton centrumButton = new JButton ("Tillbaka till centrum");
	    centrumButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ui.changeRoom("center");
				
			}
		});
	    //centrumButton.setBounds(4,6,100,200);
	    centrumButton.setContentAreaFilled(false);
	    //centrumButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(centrumButton);
	    
	    JButton gardenButton = new JButton ("Till trädgården");
	    gardenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ui.changeRoom("garden");
				
			}
		});
	    //gardenButton.setBounds(4,6,100,200);
	    gardenButton.setContentAreaFilled(false);
	    //gardenButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(gardenButton);
	    
	    JButton clickButton2 = new JButton ("Ryggsäck");
	    //clickButton2.setBounds(300,400,200,200);
	    clickButton2.setContentAreaFilled(false);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(clickButton2);
	    
	    panel.add(gridPanel, BorderLayout.CENTER);
	    panel.add(downPanel, BorderLayout.SOUTH);
	    
	   	    
	    return panel;
    
	    
	    //************************************************************************************
		
		
	}
	
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


