import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Over {
	private Shape menuSquare;
	
	public Over() {
		menuSquare = new Rectangle2D.Float(0*.7f, 0*.7f,1200*.7f, 900*.7f);
	}
		
	/**
     * Check if mouse clicked on sellected area 
     * @param e mouse event
     * @return true if area clicked
     * @return false if area not clicked
     * */
	public boolean clickCheck(MouseEvent e) {
		if(menuClickCheck(e)) {
			return true;
		}
		return false;
	}
	
	/**
     * Check if menu  botton is clicked
     * @param e mouse event
     * @return true if botton clicked
     * @return false if botton not clicked
     * */
	public boolean menuClickCheck(MouseEvent e) {
		if(menuSquare.contains(e.getPoint())) {
			return true;
		}
		return false;
	}
}
