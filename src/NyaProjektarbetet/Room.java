package NyaProjektarbetet;

import javax.swing.JPanel;

public class Room {
	//Tillfällig kod för att det ska kompilera
	private PanelSklett jp;
	private GameEngine e;
	int i;
	
	public Room()
	{
	}
	
	public Room(PanelSklett jp, GameEngine e)
	{
		this.jp = jp;
		this.e = e;
		
	}
	
	public String getPicture(String current)
	{
		if(current.equals("center"))
		{
			
			return "pictures/stig.jpg";
			
		}
		
		if(current.equals("shop"))
		{
			
			return "pictures/slott.jpg";
			
		}
		
		else 
		{
			
			return "pictures/sno.jpg";
		}
		
	}

	//Onödig!!
	/*public JPanel getRoomPanel(String current)
	//{
		if (jp != null)
		return jp.getPanel("Shop"/*e.getCurrent()*//*);
		/*
		else 
			return new JPanel();
	}
      */

}


