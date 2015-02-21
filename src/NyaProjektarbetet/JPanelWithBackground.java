package NyaProjektarbetet;
import javax.swing.*;

import java.awt.*;

/**
 * S�tter skalad bakgrundsbild. Kan anv�nda setLayout(null) och add-funktion f�r att adda synliga/osynliga knappar p� bilden.
 * Inparameter: s�kv�g f�r bild
 */
public class JPanelWithBackground extends JPanel {
	private static final long serialVersionUID = 1L;
	Image bgimage = null;

	public JPanelWithBackground(String fileName /*,UserInterface ui*/) {
		MediaTracker mt = new MediaTracker(this);
		bgimage = Toolkit.getDefaultToolkit().getImage(fileName);
		mt.addImage(bgimage, 0);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(bgimage, 0, 0, getSize().width, getSize().height, null);
	}
	
	//Metod f�r att byta bakgrundsbild
	public void changePanelImg(Image fileName) {
		bgimage = fileName;

	}
}
