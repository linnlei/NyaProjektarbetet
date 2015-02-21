package NyaProjektarbetet;

public class Item {
	/*Vi f�r best�mma hur vi vill se p� f�rem�l. Ska det finnas ETT objekt av varje sort som h�ller reda p�
	f�rem�lens egenskaper, eller massor av objekt f�r varje man har?
	Det r�cker med ett objekt f�r varje f�rem�lstyp, eftersom vi �nd� har integers i inventory som h�ller reda
	p� hur m�nga vi har. S� vi flyttar inte runt sj�lva Item-objekten, utan l�gger bara till och drar bort i
	integer-variablerna f�r hur m�nga vi har. Item-objektet anv�nds bara f�r att titta p� vad ett visst f�rem�l
	kostar, dess bild, eventuellt level, osv. Man tittar p� ett visst item via dess namn antagligen, som s�tts
	upp n�r nytt Item skapas. (ex: Item NAMNNNN = new Item(100, 3, "redbrick.jpg");*/
	
	private int price;
	private int level;
	private String name;
	private String pictureFile;
	//private int numberOwned;
	
	public Item(int price, int level, String pictureFile, String name){
		this.price = price;
		this.level = level;
		this.name = name;
		this.pictureFile = pictureFile;
		//numberOwned = 0;
	}
	
	public String getItemPicture(){
		return pictureFile;
	}
	
	public int getItemPrice(){
		return price;
	}
	
	public int getItemLevel(){
		return level;
	}
	
	public String getItemName(){
		return name;
	}
	
	/*public void changeNumberOwned(int value){
		numberOwned = numberOwned + value;
	}*/
	
	
}
