package NyaProjektarbetet;

public class Money { //denna klass beh�vs nog inte l�ngre

	private int money;
	
	public Money(){
		money = 0;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void changeMoney(int value){ //value kan f�rst�s vara b�de positivt och negativt
		money = money + value;
	}
}
