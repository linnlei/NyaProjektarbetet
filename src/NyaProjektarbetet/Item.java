package NyaProjektarbetet;

public class Item {
	/*Vi får bestämma hur vi vill se på föremål. Ska det finnas ETT objekt av varje sort som håller reda på
	föremålens egenskaper, eller massor av objekt för varje man har?
	Det räcker med ett objekt för varje föremålstyp, eftersom vi ändå har integers i inventory som håller reda
	på hur många vi har. Så vi flyttar inte runt själva Item-objekten, utan lägger bara till och drar bort i
	integer-variablerna för hur många vi har. Item-objektet används bara för att titta på vad ett visst föremål
	kostar, dess bild, eventuellt level, osv. Man tittar på ett visst item via dess namn antagligen, som sätts
	upp när nytt Item skapas. (ex: Item NAMNNNN = new Item(100, 3, "redbrick.jpg");*/
	
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
