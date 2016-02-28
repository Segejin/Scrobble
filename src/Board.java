/*
 * This code is created by 
 * Documented by Omar Alamoudi
 * */

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Board {

	Spot board[][];
	/**
     * Create 7x7 board for the game
     * */
	public Board()
	{
		// Create board size 7x7
        this.board = new Spot[7][7];
		float x = 16;
		float y = 16;
		int k = 1;
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

	/**
     * Adding a tile to the board using the horizontal and 
     * vertical location of the tile on the board
     * @param x the horizontal location of the tile on the board
     * @param y the vertical location of the tile on the board
     * @param Tile the value of the added tile
     * */
	public void addTile(int x, int y, Tile aTile)
	{
		this.board[x][y].setTile(aTile);
	}

    /**
     * Remove a tile from the board using the horizontal and 
     * vertical location of the tile on the board
     * @param x the horizontal location of the tile on the board
     * @param y the vertical location of the tile on the board
     * @return the score of the tile
     * */
	public int removeTile(int x, int y)
	{
		int score = this.board[x][y].getTile().getScore();
		this.board[x][y].setTile(null);
		return score;
	}

    /**
     * Get the by using the horizontal and vertical location of 
     * the tile.
     * @param x the horizontal location of the tile on the board
     * @param y the vertical location of the tile on the board
     * */
	public Tile getTile(int x, int y)
	{
		return this.board[x][y].getTile();
	}
    
    /**
     * Indicate the spot number clicked on the board
     * @param e is a mouse event
     * */
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

    /**
     * Display board 
     * @param g is a graphics object
     * @param tempList an integer arraylist 
     * */

	public void displayBoard(Graphics g, ArrayList<Integer> tempList) {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(tempList!=null)
				{
					if(tempList.contains(board[j][i].getSpotNum()))
						board[j][i].displayTile(g,.6f,false);
					else
						board[j][i].displayTile(g);
				}
				else
					board[j][i].displayTile(g);
			}
		}		
	}
    
    /**
     * Add tile to the board from sellcted tile
     * @param tempBoardCell spot object 
     * @param tile tile objrct
     * */
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
    
    /**
     * get the tile by the spot number 
     * @param x int represent tile spot number
     * @return tile at spot x if exists in the board 
     * @return null if tile cannot be found at the indicated spot x 
     * */
	public Tile getTile(int x) {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(this.board[j][i].getSpotNum()==x)
					return this.board[j][i].getTile();
			}
		}
		return null;
	}

    /**
     * Remove Tile at spot number
     * @param x int represent tile spot number
     * */
	public void removeTile(int x) {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(this.board[j][i].getSpotNum()==x)
					this.board[j][i].setTile(null);
			}
		}
	}
    
    /**
     * Check if the board is full
     * @return false if the board is not full
     * @return true if the board is full
     * */
	public boolean isFull() {
		for(int i = 0; i<7;i++)
		{
			for(int j = 0; j<7;j++)
			{
				if(this.board[j][i].getTile() == null)
					return false;
			}
		}
		return true;
	}
}
