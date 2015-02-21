package NyaProjektarbetet;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

public class Shop extends Room{
	//private HashMap<Item, Integer> shopItems; //Integer = f�rem�ls level
	private HashMap<Item, Boolean> shopItems; //Boolean = f�r spelaren k�pa f�rem�let?
	private Image shopPicture; //ska inte sj�lva bildobjektet (Image) skapas i PanelSkelett?????
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
		
		for(Item item : shopItems.keySet() ){
			if(player.getLevel() >= item.getItemLevel()){	//Kollar om spelarens level >= f�rem�lens level
				shopItems.put(item, true);		//S�tter att f�rem�l f�r k�pas om ens level �r tillr�ckligt h�gt
			}
		}
		
		//*****Test-popup f�r att kolla s� levelsystemet f�r f�rem�l funkar
		/*for(Item item : shopItems.keySet() ){
			JOptionPane.showMessageDialog(null, shopItems.get(item), "", JOptionPane.INFORMATION_MESSAGE);
		}*/
		
		/*
		for(Item item : shopItems.keySet() ){
			JOptionPane.showMessageDialog(null, item.getItemPrice(), "", JOptionPane.INFORMATION_MESSAGE);
		}*/
	}
	
	public void buy(Item boughtItem){ 
		//ska kolla om valt f�rem�l f�r k�pas (true eller false i hashmapen)
		//ska dra bort pengar fr�n spelarens pl�nbok
		//ska l�gga till valt f�rem�l i spelarens ryggs�ck
		
		if ( shopItems.get(boughtItem) ){ //dvs om f�rem�let man klickat p� �r true = f�r k�pas
			JOptionPane.showMessageDialog(null, player.getMoney(), "Dina pengar innan k�p", JOptionPane.INFORMATION_MESSAGE);
			player.changeMoney((-1) * boughtItem.getItemPrice());
			JOptionPane.showMessageDialog(null, player.getMoney(), "Dina pengar efter k�p", JOptionPane.INFORMATION_MESSAGE);
			player.myInventory.updateInventory(boughtItem, 1);
		}
		else{
			//popup eller n�got som s�ger att du m�ste ha h�gre level
		}
		
		//
		//          -varnuMoneyligger-.setMoney( (-varnuMoneyligger-.getMoney()) - boughtItem.getItemPrice() );
		//			-spelarensinventory-.updateInventory(newItem, antal);
	}

}
