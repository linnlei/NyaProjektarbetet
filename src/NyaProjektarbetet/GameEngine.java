package NyaProjektarbetet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;

/**
 * Den h�r klassen skapar rum.
 * Return: myFrame och currentRoom med hj�lp av tv� public metoder.
 */
public class GameEngine {
	private Player user;
	private UserInterface gui;
	//Current room:
	private String current;
	private Room currentRoom;
	//Rum:
	public Shop shop;
	public Room center, garden, minigame1;
	
	public static class State implements Serializable{
		//Player, Garden, Inventory ska vara Serializable f�r att kunna skrivas till fil
		Player player = new Player();
		Garden gard = new Garden();
		Inventory inven = new Inventory();

	}
	public State gameState;
	
	public GameEngine() {
		user = new Player();
		gui = new UserInterface(this);
		createRooms();
		gui.gameStart();
	}


	    	public void save(){
    		try{
    			//FileOutputStream saveFile = new FileOutputStream( "Libraries/Documents/sparat.sav" );
    			//ObjectOutputStream save = new ObjectOutputStream( saveFile );
    			//FileOutputStream saveFile = new FileOutputStream( "Libraries/Documents/" + gameState.player.getUserName() + ".sav" );
    			FileOutputStream saveFile = new FileOutputStream( "saves/" + gameState.player.getUserName() + ".sav" );
    			ObjectOutputStream save = new ObjectOutputStream( saveFile );

    			save.writeObject( gameState.player );
    			save.writeObject( gameState.gard );
    			save.writeObject( gameState.inven );
    			
    			saveFile.close();
    			save.close();
    		}
    			
    		catch(Exception e){
    			e.printStackTrace();
    			System.out.println("\nHoppsan, n�got gick fel!");
    		}

    	}
    	
    	public void load(){
    		try{
    			//FileInputStream saveFile = new FileInputStream( "Libraries/Documents/sparat.sav" );
    			//ObjectInputStream load = new ObjectInputStream( saveFile );
    			//FileInputStream saveFile = new FileInputStream( "Libraries/Documents/" + gameState.player.getUserName() + ".sav" );
    			FileInputStream saveFile = new FileInputStream( "saves/" + gameState.player.getUserName() + ".sav" );
    			ObjectInputStream load = new ObjectInputStream( saveFile );

    			gameState.player = (Player) load.readObject();
    			gameState.gard = (Garden) load.readObject();
    			gameState.inven = (Inventory) load.readObject();
    			
    			saveFile.close();
    			load.close();
    		}
    		
    		catch(Exception e){
    			e.printStackTrace();
    			System.out.println("\nN�men, n�got gick fel!");
    		}

    	}
	
	public String getCurrent() {
		return current;
	}
	
	public Room getCurrentRoom() {	//som getCurrent fast med Room ist.
		return currentRoom;
	}
	
	public Player getPlayer(){
		return user;
	}
	
	//Jag la till denna /Jenny
	public void setCurrent(String room) {
		current = room;
	}
	
	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}
	
	public void printWelcome() {
		String name;
		JOptionPane.showMessageDialog(gui.myFrame(), "V�lkommen till v�rt spel!!", "", JOptionPane.INFORMATION_MESSAGE);
		name = JOptionPane.showInputDialog(gui.myFrame(), "Vad �r ditt namn?", "", JOptionPane.QUESTION_MESSAGE);
		user.setUserName(name);
	}
	
	private void createRooms() {
        //Room center, garden, minigame1;
        //Room shop;
      
        center = new Room();
        garden = new Garden();
        minigame1 = new Room();
        //shop = new Room();
        //Shop shop = new Shop(user.myInventory.getInventory(), user); 	//shop som lokal variabel
        shop = new Shop(user.myInventory.getInventory(), user);			//shop som instansvariabel, f�rmodligen att f�redra

        current = "center";		//startar spelet i centrum
    }
	
	public Shop getShop(){
		return shop;
	}
	
	 public void changeRoom(String current)
	 {
		 Room room; //tillf�lligt rum f�r rumsbyte
		 
		 setCurrent(current);
		 if(current.equals("Center")) room = center; 
		 else if(current.equals("Shop")) room = shop;
		 else if(current.equals("Garden")) room = garden;
		 else room = minigame1;
		 
		 gui.setJPanelWithBackground(room.getPicture(getCurrent()));
	 }
	 
	 
	 public void changeCurrentRoom(Room current)	//rumsreferenser ist. f�r str�ngar. kr�vs dock m�nga kod�ndringar om denna ska funka
	 {
		 setCurrentRoom(current);
		 //setJPanelWithBackground(current.getPicture(engine.getCurrentRoom()));	//ex beh�ver getpicture �ndras is�fall
	 }
	
}
