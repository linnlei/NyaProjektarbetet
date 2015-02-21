package NyaProjektarbetet;

import java.awt.Image;
import java.util.HashMap;

public class Garden extends Room{
	//public HashMap<String, HashMap<Integer, Integer>> gardenItems;
	public HashMap<Integer, HashMap<Integer, String>> gardenItems; //där int1 är x, int2 är y, string är föremålets namn el bild el liknande
	private Item gardenItem; //vad är den här till?
	private Image gardenPicture; //ska inte själva bildobjektet (Image) skapas i PanelSkelett?
	
	public Garden(){
		gardenItems = new HashMap<Integer, HashMap<Integer, String>>();
	}
	
	public HashMap<Integer, HashMap<Integer, String>> getGardenItems(){
		return gardenItems;
	}
	
	public void addGardenItem(String newItem, int xpos, int ypos){
		gardenItems.get(xpos).put(ypos, newItem); //hämtar hashmapen för given xposition, lägger in newItem på given yposition
	}
}
