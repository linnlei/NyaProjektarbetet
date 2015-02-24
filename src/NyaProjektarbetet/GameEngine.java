package NyaProjektarbetet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;

/**
 * Den här klassen skapar rum.
 * Return: myFrame och currentRoom med hjälp av två public metoder.
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
		//Player, Garden, Inventory ska vara Serializable för att kunna skrivas till fil
		Player player = new Player();
		Room gard = new Garden();
		Inventory inven = new Inventory();
		
		public Player getSavedPlayer(){
			return player;
		}
		public void setStatePlayer(Player user){
			player = user;
		}
		
		public Room getSavedGarden(){
			return gard;
		}
		public void setStateGarden(Room garden){
			gard = garden;
		}
		
		public Inventory getSavedInventory(){
			return inven;
		}
		public void setStateInventory(Inventory inventory){
			inven = inventory;
		}
		

	}
	public State gameState;
	
	public GameEngine() {
		
		gameState = new State();
		gui = new UserInterface(this);
		gui.gameStart();
		user = new Player();
		
	}


	    	public void save(){
	    	System.out.println("" + gameState.getSavedPlayer().getMoney());
	    	gameState.setStatePlayer(user);
	    	gameState.setStateGarden(garden);
	    	gameState.setStateInventory(user.myInventory);
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
    			
    			System.out.println("Sparar som: saves/" + gameState.player.getUserName() + ".sav");
    		}
    			
    		catch(Exception e){
    			e.printStackTrace();
    			System.out.println("\nHoppsan, något gick fel vid sparandet!");
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
    			
    			if( !(gameState.getSavedPlayer().equals(user)) ){
    				user = gameState.getSavedPlayer();
    				garden = gameState.getSavedGarden();
    				user.myInventory = gameState.getSavedInventory();
    				JOptionPane.showMessageDialog( null, "Din sparfil har laddats in!","yay!",JOptionPane.OK_CANCEL_OPTION);
    			}
    			else{
    				JOptionPane.showMessageDialog( null, "Du är en ny spelare!","Ny spelare",JOptionPane.OK_CANCEL_OPTION);
    			}
    			
    			System.out.println("Laddar: saves/" + gameState.player.getUserName() + ".sav");
    		}
    		
    		catch(Exception e){
    			e.printStackTrace();
    			System.out.println("\nNämen, något gick fel vid laddandet!");
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
		JOptionPane.showMessageDialog(gui.myFrame(), "Välkommen till vårt spel!!", "", JOptionPane.INFORMATION_MESSAGE);
		name = JOptionPane.showInputDialog(gui.myFrame(), "Vad är ditt namn?", "", JOptionPane.QUESTION_MESSAGE);
		
		stateSetup(name);
		
		
		
	}
	
	private void createRooms() {
        //Room center, garden, minigame1;
        //Room shop;
      
        center = new Room();
        garden = new Garden();
        minigame1 = new Room();
        //shop = new Room();
        //Shop shop = new Shop(user.myInventory.getInventory(), user); 	//shop som lokal variabel
        shop = new Shop(user.myInventory.getInventory(), user);			//shop som instansvariabel, förmodligen att föredra

        current = "center";		//startar spelet i centrum
    }
	
	public Shop getShop(){
		return shop;
	}
	
	 public void changeRoom(String current)
	 {
		 Room room; //tillfälligt rum för rumsbyte
		 
		 setCurrent(current);
		 if(current.equals("Center")) room = center; 
		 else if(current.equals("Shop")) room = shop;
		 else if(current.equals("Garden")) room = garden;
		 else room = minigame1;
		 
		 gui.setJPanelWithBackground(room.getPicture(getCurrent()));
	 }
	 
	 
	 public void changeCurrentRoom(Room current)	//rumsreferenser ist. för strängar. krävs dock många kodändringar om denna ska funka
	 {
		 setCurrentRoom(current);
		 //setJPanelWithBackground(current.getPicture(engine.getCurrentRoom()));	//ex behöver getpicture ändras isåfall
	 }
	 
	 public void stateSetup(String name){
		 	user.setUserName(name);
			gameState.setStatePlayer(user);
			
			
			System.out.println("" + gameState.getSavedPlayer().getUserName());
			load();	
			
			/*Player newPlayer = new Player();
			newPlayer.setUserName(name);*/
			/*
			if( !(gameState.getSavedPlayer().equals(user)) ){
				user = gameState.getSavedPlayer();
				JOptionPane.showMessageDialog( null, "Din sparfil har laddats in!","yay!",JOptionPane.OK_CANCEL_OPTION);
			}
			else{
				JOptionPane.showMessageDialog( null, "Du är en ny spelare!","Ny spelare",JOptionPane.OK_CANCEL_OPTION);
			}*/
			
			createRooms();
	 }
	
}
