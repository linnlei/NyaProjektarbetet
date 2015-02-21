package NyaProjektarbetet;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class ShopController {
    //... The Controller needs to interact with both the Model and View.
    private Shop model;
    private PanelSklett  view;

    //========================================================== constructor
    /** Constructor */
    ShopController(Shop model, PanelSklett view) {
        this.model = model;
        this.view  = view;
    }
    
    public void buyControl(String value){
    	//I controllern:
		//om det är null, ingen varningstext och inget händer
		//kolla så det är siffror man skrivit in, annars varningstext
		//kolla så man har råd, annars varningstext)
    	
    	
    	if( isInteger(value) ){
    		JOptionPane.showMessageDialog(null, "Du vill köpa stycken.", "KÖP", JOptionPane.OK_CANCEL_OPTION);
    	}
    	else if (value == null){
    		
    	}
    	
    }
    
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}

    /*
    ////////////////////////////////////////// inner class MultiplyListener
     When a multiplication is requested.
     //  1. Get the user input number from the View.
     //  2. Call the model to multiply by this number.
     //  3. Get the result from the Model.
     //  4. Tell the View to display the result.
     // If there was an error, tell the View to display it.
     
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
    }//end inner class MultiplyListener


    //////////////////////////////////////////// inner class ClearListener
     //1. Reset model.
      //  2. Reset View.
      //   
    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	model.reset();
        	view.reset();
        }
    }// end inner class ClearListener
     */
}
