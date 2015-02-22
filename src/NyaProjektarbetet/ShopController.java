package NyaProjektarbetet;


import javax.swing.JOptionPane;

public class ShopController {
    private PanelSklett view;
    private GameEngine engine;

    ShopController(GameEngine engine, PanelSklett view) {
        this.view  = view;		//view �r allts� panelskelettet
        this.engine  = engine;	//via engine kommer man �t shop, som �r model
    }
    
    public void buyControl(String value, String clickedItem){
	
    	if( isInteger(value) ){		//Om man skrivit in siffror, forts�tter med kontrollen
    		JOptionPane.showMessageDialog(null, "Du vill k�pa " + value + " stycken.", "K�P", JOptionPane.OK_CANCEL_OPTION);
    		moneyControl(value, clickedItem);
    	}
    	else if (value == null){ 	//Om instr�ngen �r null
    		JOptionPane.showMessageDialog(null, "Skriv in ett antal du vill k�pa!", "K�P", JOptionPane.OK_CANCEL_OPTION);
    	}
    	else{ 	//Om man skrivit in n�got annat �n siffror
    		JOptionPane.showMessageDialog(null, "Du m�ste skriva in siffror!", "K�P", JOptionPane.OK_CANCEL_OPTION);
    	}
    }
    
    public void moneyControl(String value, String clickedItem){
    	
    	Item boughtItem = new Item(0, 1, "BrickBlue.png", "null"); //skapa tempor�rt f�rem�l som skrivs �ver
    	
    	for(Item item : engine.shop.getShopItems().keySet() ){ //letar upp f�rem�let med r�tt namn
    		if(clickedItem == item.getItemName()){
    			boughtItem = item;
    		}
    	}
    	
    	engine.shop.calculatePrice(boughtItem, Integer.parseInt(value));	//anropar k�pmetoderna i shop
    	
    }
    
	public static boolean isInteger(String s) {	//kollar om instr�ng �r en integer
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true; 	    // man kommer bara hit om man inte redan returnerat false
	}

    /* -----------Man kan �ven g�ra inre klasser som implementerar actionlistener. Vore detta mer korrekt?
    class MultiplyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = "";
            try {
                userInput = view.getUserInput();
                model.multiplyBy(userInput);
                view.setTotal(model.getValue());

            } catch (NumberFormatException nfex) {
            	view.showError("Bad input: '" + userInput + "'");
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	model.reset();
        	view.reset();
        }
    }// end inner class ClearListener
     */
}
