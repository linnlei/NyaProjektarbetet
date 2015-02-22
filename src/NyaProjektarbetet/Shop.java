package NyaProjektarbetet;

import java.util.HashMap;

import javax.swing.JOptionPane;


public class Shop extends Room{
	private HashMap<Item, Boolean> shopItems; //Boolean = f�r spelaren k�pa f�rem�let?
	private Player player;
	
	public Shop(HashMap<Item, Integer> inventory, Player player){
		shopItems = new HashMap<Item, Boolean>();
		this.player = player;

		
		for(Item item : inventory.keySet() ){	//L�gger in alla f�rem�l i aff�ren
			shopItems.put(item, false);			//Men s�tter att inget kan k�pas �n
		}
		
		updateShop();
	}
	
	public HashMap<Item, Boolean> getShopItems(){
		return shopItems;
	}
	
	private void updateShop(){
		
		for(Item item : shopItems.keySet() ){				//G�r igenom alla f�rem�l
			if(player.getLevel() >= item.getItemLevel()){	//Kollar om spelarens level >= f�rem�lens level
				shopItems.put(item, true);					//S�tter att f�rem�l f�r k�pas om ens level �r tillr�ckligt h�gt
			}
		}
		
	}
	
	public void calculatePrice(Item boughtItem, int numberOfItems){
		int price = 0;
		price = numberOfItems * boughtItem.getItemPrice();
		JOptionPane.showMessageDialog(null, "Det blir " + price + " kronor.", "", JOptionPane.OK_CANCEL_OPTION);
		
		if(player.getMoney() >= price){
    		JOptionPane.showMessageDialog(null, "Du har r�d!", "", JOptionPane.OK_CANCEL_OPTION);
    		buy(boughtItem, numberOfItems);
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Du har inte r�d!", "", JOptionPane.OK_CANCEL_OPTION);
    	}
	}
	
	public void buy(Item boughtItem, int numberOfItems){ 
		
		if ( shopItems.get(boughtItem) ){ 	//dvs om f�rem�let man klickat p� �r true = f�r k�pas
			JOptionPane.showMessageDialog(null, "Dina pengar innan k�p: " + player.getMoney(), "Pengar", JOptionPane.INFORMATION_MESSAGE);
			player.changeMoney((-1) * numberOfItems * boughtItem.getItemPrice()); 	//drar bort pengar fr�n spelarens pl�nbok
			JOptionPane.showMessageDialog(null, "Dina pengar efter k�p: " + player.getMoney(), "Pengar", JOptionPane.INFORMATION_MESSAGE);
			player.myInventory.updateInventory(boughtItem, numberOfItems); 	//l�gger till r�tt antal f�rem�l i ryggs�cken
		}
		else{
			JOptionPane.showMessageDialog(null, "... men du har f�r l�gt level.", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	
}
