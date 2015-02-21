package NyaProjektarbetet;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

public class Shop extends Room{
	//private HashMap<Item, Integer> shopItems; //Integer = föremåls level
	private HashMap<Item, Boolean> shopItems; //Boolean = får spelaren köpa föremålet?
	private Image shopPicture; //ska inte själva bildobjektet (Image) skapas i PanelSkelett?????
	private Player player;
	
	public Shop(HashMap<Item, Integer> inventory, Player player){
		shopItems = new HashMap<Item, Boolean>();
		this.player = player;

		
		for(Item item : inventory.keySet() ){	//Lägger in alla föremål i affären
			shopItems.put(item, false);			//Men sätter att inget kan köpas än
		}
		
		updateShop();
	}
	
	public HashMap<Item, Boolean> getShopItems(){
		return shopItems;
	}
	
	private void updateShop(){
		
		for(Item item : shopItems.keySet() ){
			if(player.getLevel() >= item.getItemLevel()){	//Kollar om spelarens level >= föremålens level
				shopItems.put(item, true);		//Sätter att föremål får köpas om ens level är tillräckligt högt
			}
		}
		
		//*****Test-popup för att kolla så levelsystemet för föremål funkar
		/*for(Item item : shopItems.keySet() ){
			JOptionPane.showMessageDialog(null, shopItems.get(item), "", JOptionPane.INFORMATION_MESSAGE);
		}*/
		
		/*
		for(Item item : shopItems.keySet() ){
			JOptionPane.showMessageDialog(null, item.getItemPrice(), "", JOptionPane.INFORMATION_MESSAGE);
		}*/
	}
	
	public void buy(Item boughtItem){ 
		//ska kolla om valt föremål får köpas (true eller false i hashmapen)
		//ska dra bort pengar från spelarens plånbok
		//ska lägga till valt föremål i spelarens ryggsäck
		
		if ( shopItems.get(boughtItem) ){ //dvs om föremålet man klickat på är true = får köpas
			JOptionPane.showMessageDialog(null, player.getMoney(), "Dina pengar innan köp", JOptionPane.INFORMATION_MESSAGE);
			player.changeMoney((-1) * boughtItem.getItemPrice());
			JOptionPane.showMessageDialog(null, player.getMoney(), "Dina pengar efter köp", JOptionPane.INFORMATION_MESSAGE);
			player.myInventory.updateInventory(boughtItem, 1);
		}
		else{
			//popup eller något som säger att du måste ha högre level
		}
		
		//
		//          -varnuMoneyligger-.setMoney( (-varnuMoneyligger-.getMoney()) - boughtItem.getItemPrice() );
		//			-spelarensinventory-.updateInventory(newItem, antal);
	}

}
