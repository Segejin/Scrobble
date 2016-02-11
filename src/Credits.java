/**
 * This code created by 
 * Documented by Omar Alamoudi
 * */
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Credits {
	private Shape menuSquare;
	
    /**
     * Create credit
     * */
	public Credits() {
		menuSquare = new Rectangle2D.Float(0*.7f, 0*.7f,1200*.7f, 900*.7f);
	}
    
    /**
     * Check clicked
     * @param e mouse event
     * @return true if menu clicked
     * @return false if menu not clicked 
     * */
	public boolean clickCheck(MouseEvent e) {
		if(menuClickCheck(e)) {
			return true;
		}
		return false;
	}
	
    /**
     * Click menu area
     * @param e mouse event
     * @return true if menu square clicked
     * @return false if menu square not clicked 
     * */
	public boolean menuClickCheck(MouseEvent e) {
		if(menuSquare.contains(e.getPoint())) {
			return true;
		}
		return false;
	}
}
