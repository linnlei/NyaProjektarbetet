package NyaProjektarbetet;
import javax.swing.*;

/**
 * Den här klassen skapar rum.
 * Return: myFrame och currentRoom med hjälp av två public metoder.
 */
public class GameEngine {
	private Player user;
	private UserInterface gui;
	private String current;
	public Shop shop;
	
	public GameEngine() {
		user = new Player();
		gui = new UserInterface(this);
		createRooms();
		gui.gameStart();
	}
	
	public String getCurrent() {
		return current;
	}
	
	public Player getPlayer(){
		return user;
	}
	
	//Jag la till denna /Jenny
	public void setCurrent(String room) {
		current = room;
	}
	
	public void printWelcome() {
		String name;
		JOptionPane.showMessageDialog(gui.myFrame(), "Välkommen till vårt spel!!", "", JOptionPane.INFORMATION_MESSAGE);
		name = JOptionPane.showInputDialog(gui.myFrame(), "Vad är ditt namn?", "", JOptionPane.QUESTION_MESSAGE);
		user.setUserName(name);
	}
	
	private void createRooms() {
        Room center, garden, minigame1;
        //Room shop;
      
        center = new Room();
        //shop = new Room();
        garden = new Room();
        minigame1 = new Room();
        //Shop shop = new Shop(user.myInventory.getInventory(), user); 	//shop som lokal variabel
        shop = new Shop(user.myInventory.getInventory(), user);			//shop som instansvariabel, förmodligen att föredra

        current = "center";		//startar spelet i centrum
    }
	
	public Shop getShop(){
		return shop;
	}
	
}
