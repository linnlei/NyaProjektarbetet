package NyaProjektarbetet;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Garden extends Room implements Serializable{
	//public HashMap<String, HashMap<Integer, Integer>> gardenItems;
	public HashMap<Integer, ImageIcon> gardenItems; //där Integer är löpnummer och Icon Items bild 
	//public HashMap<Integer, HashMap<Integer, String>> gardenItems; //där int1 är x, int2 är y, string är föremålets namn el bild el liknande
	//private Item gardenItem; //vad är den här till?
	//private Image gardenPicture; //ska inte själva bildobjektet (Image) skapas i PanelSkelett?
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
		gardenItems.get(xpos).put(ypos, newItem); //hämtar hashmapen för given xposition, lägger in newItem på given yposition
	}*/
		
		public void addItem(int lopnr, ImageIcon icon){
			gardenItems.put(lopnr, icon); //lägger till iconbilden på given x-position
			//System.out.println("Nu är vi i Gardens addItem med löpnr: "+ lopnr);
			//System.out.println("Detta är vad som finns sparat i trädgården "+ gardenItems);
		}
		
		public void removeItem(int lopnr){
			gardenItems.remove(lopnr); //Tar bort bild från plats
			//System.out.println("Nu är vi i Gardens remove"+ lopnr);
		}
		
		public ImageIcon getGardenIcon(int lopnr){
			 //Hämtar bilden till en speciell plats i Garden
			//System.out.println("Nu är vi i Gardens getGardenIcon med löpnr: "+ lopnr);
			return  gardenItems.get(lopnr);
			
		}
		
		public String getPicture(String current)
		{
			return "pictures/strand.jpg";
		}
}
