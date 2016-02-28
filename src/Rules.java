/**
 * This code was created by
 * Documented by Omar Alamoudi
 * */
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Rules {
	private Shape menuSquare;
	/**
     * Create the rules page
     * */
	public Rules() {
		menuSquare = new Rectangle2D.Float(0*.7f, 0*.7f,1200*.7f, 900*.7f);
	}
	
    /**
     * Check if target clicked
     * @param e mouse click event
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
     * Menu click trigger
     * @param e mouse event
     * @return true if menu area clicked
     * @return false if menu area not clicked
     * */
	public boolean menuClickCheck(MouseEvent e) {
		if(menuSquare.contains(e.getPoint())) {
			return true;
		}
		return false;
	}
}
