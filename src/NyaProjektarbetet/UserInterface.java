package NyaProjektarbetet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * 
 */
public class UserInterface {
	private GameEngine engine;
	private JFrame myFrame;
	private JTextField entryField;
	private JTextArea log;
	private String image;
	private JButton exitButton;
	private HashMap<String,JButton> exitButtons = new HashMap<String,JButton>();
	private JPanelWithBackground panel;
	private Room room;
	public PanelSklett invisPanels;
	private JPanel invisPanel;
	private JPanelWithBackground background;
	private UserInterface that = this; // ;-)
	
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        //createGUI();
        //room = new Room();
        invisPanels = new PanelSklett(engine, this);
    }
    
    public JFrame myFrame() {
    	return myFrame;
    }
    
    public void gameStart() {
    	myFrame = new JFrame("spel");
		background = new JPanelWithBackground("pictures/startbackground.jpg");
		background.setLayout(null);
		Font font = new Font("Viner Hand ITC", Font.BOLD, 50);
		
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //double width = screenSize.getWidth();
        //double height = screenSize.getHeight();
        
		//Frame har en bestämd storlek 1280x800. Kan finnas kvar tills man implementerar resize-funktion för JButton
        myFrame.setPreferredSize(new Dimension(1280, 800));
        myFrame.setMinimumSize(new Dimension(1280, 800));
        myFrame.setResizable(false);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        createMenu();
		
		JButton startButton = new JButton ("Starta spel");
        startButton.setBounds(420,580,400,80);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false); //med eller utan kant
        startButton.setFont(font);
        startButton.setForeground(Color.pink); //färg på startknappen
        
        background.add(startButton);
		myFrame.add(background);
		
		//Skapa några lyssnare
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //starta spel här
            	createGUI();
            	engine.printWelcome();}
            	
        });
		
        myFrame.pack();
        myFrame.setVisible(true);
    }
	
	public void createMenu() {
    	//Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Meny");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //Nytt spel
        menuItem = new JMenuItem("Nytt spel", new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent event) {
    			createGUI();
    			engine.printWelcome();
    		}
		});
		menu.add(menuItem);

        //Öppna en sparad fil
        menuItem = new JMenuItem("Öppna",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);
        
        //Spara en fil
        menuItem = new JMenuItem("Spara", new ImageIcon("image.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(menuItem);
        
        //Avsluta
        menuItem = new JMenuItem("Avsluta",
                                 new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	int svar = JOptionPane.showConfirmDialog(null, "Vill du lämna spelet utan att spara?", "Avsluta", JOptionPane. YES_NO_OPTION);
            	if(svar == JOptionPane.YES_OPTION)
            		System.exit(0);
            }
        });
        
        menu.add(menuItem);


        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        //a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Inställningar");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);

        myFrame.setJMenuBar(menuBar);
        
      //Build second menu in the menu bar.
        menu = new JMenu("Hjälp");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Hjälp",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent event) {
        			JOptionPane.showMessageDialog( null, "[hjälp kommer senare]","Hjäälp",JOptionPane.OK_CANCEL_OPTION); 
        		}
        });
        
        menu.add(menuItem);
        myFrame.setJMenuBar(menuBar);
        
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
		
        myFrame.pack();
	}
	
	 public void createGUI() {
		
	        //entryField = new JTextField(34);
	        image ="pictures/startbackground.jpg";
	        /*
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        double width = screenSize.getWidth();
	        double height = screenSize.getHeight();
	        
	        double textHeight = height * 0.15;
	        double textWidth = height * 0.15;
	        double imgWidth = width * 0.9;
	        double imgHeight = height * 0.9;
	        
	        myFrame.setPreferredSize(new Dimension((int)width, (int)height));
	        myFrame.setMinimumSize(new Dimension((int)width, (int)height));
	        myFrame.setResizable(false);
	        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	         */	
	        panel = new JPanelWithBackground(image); 
	        addBorderLayout(panel, engine.getCurrent());
	        //createMenu();
	       	               	        
	        myFrame.pack();
	        //entryField.requestFocus();
	              
	        
	    }
	 
	 private void addBorderLayout(JPanel pa, String current)
	 {
		 	int i = 0;
		 	String nextRoom = "Centrum";
		 	if("center".equals(current)){ nextRoom = "Affär";}
		 	final int j = i;
		 	final String c = current;
		 			 	
		 	exitButton = new JButton("Exit");
	        JButton button2 = new JButton(nextRoom);
	        JButton mapButton = new JButton("Karta");
	        JButton button4 = new JButton("Föremål");
	        JButton button5 = new JButton("Pengar");
	        JButton button6 = new JButton("Föremål");
	        JButton button7 = new JButton("Pengar"); 
	        
	        //String image ="pictures/startbackground.jpg";
	                
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        double width = screenSize.getWidth();
	        double height = screenSize.getHeight();
	        
	        double textHeight = height * 0.15;
	        double textWidth = height * 0.15;
	        double imgWidth = width * 0.9;
	        double imgHeight = height * 0.9;
	        
	        JPanel p = new JPanel(new GridLayout(4,1));
	        JPanel p2 = new JPanel(new GridLayout(4,1));
	        JPanel b = new JPanel();
	        b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));

	        p.add(button2);
	        p.add(mapButton);
	        p.add(exitButton);
	        b.add(button4);
	        b.add(button5);
	        
	        panel.setLayout(new BorderLayout());
	        //panel.add(textBox, BorderLayout.AFTER_LAST_LINE);
	        panel.add(p, BorderLayout.WEST);
	        panel.add(p2, BorderLayout.EAST);
	        panel.add(b, BorderLayout.NORTH);
	       
	        
	        panel.setPreferredSize(new Dimension((int)width, (int)height)); //bildstorlek, gör om till att skala
	        panel.setMinimumSize(new Dimension((int)width, (int)height)); //istället för att skära av
	        
	        myFrame.getContentPane().add(panel, BorderLayout.NORTH);
	        
	       button2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if("center".equals(c)){
					that.changeRoom("shop");
					}
					else that.changeRoom("center");
					
					
				}
			});
	       
	            
	        	
	        exitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	System.exit(0);
	            }
	        });
	        
	        mapButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	System.exit(0);
	            }
	        });
	 }
	 
	 private void setJPanelWithBackground(String i)
	 {
		  myFrame.remove(panel);
		  panel = new JPanelWithBackground(i);
		  panel.setLayout(new BorderLayout());
		  addBorderLayout(panel, engine.getCurrent());
		  panel.add(invisPanels.getPanel(engine.getCurrent()), BorderLayout.CENTER); //room.getRoomPanel("Shop"/*engine.getCurrent()*/));
		  myFrame.add(panel);			
		  myFrame.pack();
		  //myFrame.setVisible(true);
		  
		    
	 }
	 
	 public void changeRoom(String current)
	 {
		 engine.setCurrent(current);
		 if(current.equals("Center")) room = invisPanels.center; 
		 else if(current.equals("Shop")) room = invisPanels.shop;
		 else if(current.equals("Garden")) room = invisPanels.garden;
		 else room = invisPanels.miniGame1;
		 
		 setJPanelWithBackground(room.getPicture(engine.getCurrent()));
		 
	 }
	 

}
