package NyaProjektarbetet;
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
