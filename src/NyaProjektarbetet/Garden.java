package NyaProjektarbetet;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Garden extends Room implements Serializable{
	//public HashMap<String, HashMap<Integer, Integer>> gardenItems;
	public HashMap<Integer, ImageIcon> gardenItems; //d�r Integer �r l�pnummer och Icon Items bild 
	//public HashMap<Integer, HashMap<Integer, String>> gardenItems; //d�r int1 �r x, int2 �r y, string �r f�rem�lets namn el bild el liknande
	//private Item gardenItem; //vad �r den h�r till?
	//private Image gardenPicture; //ska inte sj�lva bildobjektet (Image) skapas i PanelSkelett?
	private Item gardenItem;
	private Image gardenPicture;
	
	public Garden(){
		gardenItems = new HashMap<Integer, ImageIcon>();
		}
	
	public HashMap<Integer, ImageIcon> getGardenItems(){
		return gardenItems;
	}
	/*
	public void addGardenItem(String newItem, int xpos, int ypos){
		gardenItems.get(xpos).put(ypos, newItem); //h�mtar hashmapen f�r given xposition, l�gger in newItem p� given yposition
	}*/
		
		public void addItem(int lopnr, ImageIcon icon){
			gardenItems.put(lopnr, icon); //l�gger till iconbilden p� given x-position
			//System.out.println("Nu �r vi i Gardens addItem med l�pnr: "+ lopnr);
			//System.out.println("Detta �r vad som finns sparat i tr�dg�rden "+ gardenItems);
		}
		
		public void removeItem(int lopnr){
			gardenItems.remove(lopnr); //Tar bort bild fr�n plats
			//System.out.println("Nu �r vi i Gardens remove"+ lopnr);
		}
		
		public ImageIcon getGardenIcon(int lopnr){
			 //H�mtar bilden till en speciell plats i Garden
			//System.out.println("Nu �r vi i Gardens getGardenIcon med l�pnr: "+ lopnr);
			return  gardenItems.get(lopnr);
			
		}
		
		public String getPicture(String current)
		{
			return "pictures/strand.jpg";
		}
}
