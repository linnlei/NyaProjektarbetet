package NyaProjektarbetet;
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
	
	public GameEngine() {
		user = new Player();
		gui = new UserInterface(this);
		createRooms();
		gui.gameStart();
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
	
}
