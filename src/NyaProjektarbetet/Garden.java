package NyaProjektarbetet;

import java.awt.Image;
import java.util.HashMap;

public class Garden extends Room{
	//public HashMap<String, HashMap<Integer, Integer>> gardenItems;
	public HashMap<Integer, HashMap<Integer, String>> gardenItems; //d�r int1 �r x, int2 �r y, string �r f�rem�lets namn el bild el liknande
	private Item gardenItem; //vad �r den h�r till?
	private Image gardenPicture; //ska inte sj�lva bildobjektet (Image) skapas i PanelSkelett?
	
	public Garden(){
		gardenItems = new HashMap<Integer, HashMap<Integer, String>>();
	}
	
	public HashMap<Integer, HashMap<Integer, String>> getGardenItems(){
		return gardenItems;
	}
	
	public void addGardenItem(String newItem, int xpos, int ypos){
		gardenItems.get(xpos).put(ypos, newItem); //h�mtar hashmapen f�r given xposition, l�gger in newItem p� given yposition
	}
}
