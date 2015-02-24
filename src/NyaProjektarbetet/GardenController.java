package NyaProjektarbetet;

import java.awt.Image;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class GardenController extends Observable{
	
	//Room garden;
	ImageIcon itemIcon;
	int nrOfItem;
	ImageIcon takenImage;
	GameEngine engine;
	HashMap<ImageIcon, Integer> inventory = fuskaFramHashMap();// Tillfällig  map när jag jobbar utan inventory Icon ska egentligen var Item
	
	
	public GardenController(Room g,Inventory in, GameEngine engine)
	{
		//inventory = in.getInventory();
		//garden = g;
		this.engine  = engine;
			
	}
	
	private HashMap<ImageIcon, Integer> fuskaFramHashMap()
	{
		HashMap<ImageIcon, Integer> inventoryFusk = new HashMap<ImageIcon, Integer>();
		
		ImageIcon i = new ImageIcon("pictures/skylt.png");
		inventoryFusk.put(i, 100);
		ImageIcon j = new ImageIcon("pictures/exitskylt.png");
		inventoryFusk.put(j, 100);
		ImageIcon k = new ImageIcon("pictures/fonster.png");
		inventoryFusk.put(k, 100);
		ImageIcon l = new ImageIcon("pictures/flowe.png");
		inventoryFusk.put(l, 100);
		ImageIcon m = new ImageIcon("pictures/pyramid.png");
		inventoryFusk.put(m, 100);
		ImageIcon n = new ImageIcon("pictures/redbrick.jpg");
		inventoryFusk.put(n, 100);
		ImageIcon q = new ImageIcon("pictures/bluebrick.png");
		inventoryFusk.put(q, 100);
		
		 Image o = n.getImage() ;  
		   Image newimg = o.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
		   ImageIcon icon = new ImageIcon( newimg );
		   inventoryFusk.put(icon, 25);
		   
					
		Image p = k.getImage() ;  
			 Image newimg1 = p.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
			 ImageIcon icon1 = new ImageIcon( newimg1 );
			 inventoryFusk.put(icon1, 25);
			     
		Image r = q.getImage() ;  
			 Image newimg2 = r.getScaledInstance( 55, 55,  java.awt.Image.SCALE_SMOOTH ) ;  
			 ImageIcon icon2 = new ImageIcon( newimg2 );
			 inventoryFusk.put(icon2, 25);
		   
		return inventoryFusk;
		}
	
		
	public HashMap<ImageIcon, Integer> getInventory()
	{
		//System.out.println("( metod getInventory: Detta är vad som finns sparat i inventory nu:  "+ inventory);
		return inventory;
	
	}
	

	
	public ImageIcon getIcon(int lopnr)
	{
		//System.out.println("Nu är vi i getIcon"+ lopnr);
		return engine.garden.getGardenIcon(lopnr);
	}
	
	
	
	
	public void remove(int lopnr)
	{
		ImageIcon icon = engine.garden.getGardenIcon(lopnr);
		engine.garden.removeItem(lopnr);
		//System.out.println("Nu är vi i remove"+ lopnr);
		//if(inventory.containsKey(icon))
		//{
			int nr = inventory.get(icon);
			nr++;
			setChanged();
			notifyObservers(nr);
			inventory.put(icon, nr);		//}
			//System.out.println("Detta är vad som finns sparat i inventory efter att " +icon+" har plockats tillbaka:  "+ inventory);
	}
	
	public void take(ImageIcon imageOfItem)
	{
		takenImage = imageOfItem;
		//System.out.println("Bilden som följde med till GardenControllers take är: : " +takenItem );
	}
	
	
	public ImageIcon getTakenImage()
	{
		//System.out.println("Bilden som följde med till GardenControllers getTakenItem är: : " +takenItem );
		
		if(inventory.get(takenImage)!=0)
		return takenImage;
		
		else return null;
		

	}
	
	public void build(int lopnr)
	{
		if(takenImage != null)
		{
			//System.out.println("takenItem är: : " +takenItem );
			//System.out.println("icon är: : " +icon );
			engine.garden.addItem(lopnr, takenImage);
			//System.out.println("Nu är vi i build"+ lopnr);
			
			
			//Minska värdet på key:n ImageIcon med 1
			int nr = 0;
			if(inventory.containsKey(takenImage)){
				nr =inventory.get(takenImage);
				nr--;
				setChanged();
				notifyObservers(nr);
				inventory.put(takenImage, nr);
			}
		}
		
				
	}
	
	
}
