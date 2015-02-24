package NyaProjektarbetet;

import java.io.Serializable;

public class Player implements Serializable {
	private String userName;
	private int level;
	private int money;
	public Inventory myInventory;
	
	public Player(){
		myInventory = new Inventory();
		level = 1;
		money = 1000;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int newLevel){
		level = newLevel;
	}
	
	//eventuell metod f�r att levla upp beroende p� hur levlandet ska fungera
	public void levelUp(){
		level = level++;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void changeMoney(int value){ //value kan f�rst�s vara b�de positivt och negativt
		money = money + value;
	}
}
