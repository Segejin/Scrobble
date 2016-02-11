import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Board {

	Spot board[][];
	
	public Board()
	{
		board = new Spot[7][7];
		float x = 16;
		float y = 16;
		int k = 0;
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				board[j][i] = new Spot(x,y,null,k);
				k+=1;
				x+=107.2;
			}
			x=16;
			y+=107.2;
		}
	}
	
	public void addTile(int x, int y, Tile aTile)
	{
		board[x][y].setTile(aTile);
	}
	public int removeTile(int x, int y)
	{
		int score = board[x][y].getTile().getScore();
		board[x][y].setTile(null);
		return score;
	}
	public Tile getTile(int x, int y)
	{
		return board[x][y].getTile();
	}

	public Spot clickCheck(MouseEvent e) {
		for(Spot s[]: board)
		{
			for(Spot cell: s)
			{
				if(cell.getShape().contains(e.getPoint()))
				{
					System.out.println("clicked square: " + cell.getSpotNum());
					return cell;
				}
			}
		}
		return null;
		
	}

	public void displayBoard(Graphics g, ArrayList<Integer> tempList) {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(tempList!=null)
				{
					if(tempList.contains(board[j][i].getSpotNum()))
						board[j][i].displayTile(g,.6f);
					else
						board[j][i].displayTile(g);
				}
				else
					board[j][i].displayTile(g);
			}
		}		
	}

	public void addTile(Spot tempBoardCell, Tile tile) {
		int spotNum = tempBoardCell.getSpotNum();
		for(Spot s[]: board)
		{
			for(Spot cell: s)
			{
				if(spotNum == cell.getSpotNum())
				{
					System.out.println("adding tile to board");
					cell.setTile(tile);
				}
			}
		}
		
		
	}

	public Tile getTile(int x) {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(board[j][i].getSpotNum()==x)
					return board[j][i].getTile();
			}
		}
		return null;
	}
	public void removeTile(int x) {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(board[j][i].getSpotNum()==x)
					board[j][i].setTile(null);
			}
		}
	}
	public boolean isFull() {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(board[j][i].getTile() == null)
					return false;
			}
		}
		return true;
	}
}