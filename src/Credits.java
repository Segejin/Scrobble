import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Credits {
	private Shape menuSquare;
	
	public Credits() {
		menuSquare = new Rectangle2D.Float(0*.7f, 0*.7f,1200*.7f, 900*.7f);
	}
		
	public boolean clickCheck(MouseEvent e) {
		if(menuClickCheck(e)) {
			return true;
		}
		return false;
	}
	
	public boolean menuClickCheck(MouseEvent e) {
		if(menuSquare.contains(e.getPoint())) {
			return true;
		}
		return false;
	}
}
