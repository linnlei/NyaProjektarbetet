package NyaProjektarbetet;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * Just nu inneh�ller inventory en hashmap med bluebrick o redbrick bara
 * F�r att testa detta kan man k�ra den bortkommenterade raden
 *
 */

public class Inventory implements Serializable {
	private HashMap<Item, Integer> items;
	
	public Inventory() {
		items = new HashMap<Item, Integer>();
		createInventory();
	}
	
	public HashMap<Item, Integer> getInventory() {
		return items;
	}
	
	public void updateInventory(Item item, Integer amount) {
		items.put(item, amount);
	}
	
	private void createInventory(){
		Item blueBrick = new Item(100, 1, "BrickBlue.png", "Bl� tegelsten");
		Item redBrick = new Item(200, 2, "BrickRed.png", "R�d tegelsten");
		items.put(blueBrick, 0);
		items.put(redBrick, 0);
		//JOptionPane.showMessageDialog(gui.myFrame(), items.get(blueBrick), "", JOptionPane.INFORMATION_MESSAGE);
	}
	
}

	
	

