import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;


public class Game {
	private Image img;
	private Deck pile;
	private Player player_one;
	private Player player_two;
	private Board play_board;
	private Judge judge;
	private GUI gui;
	private int step;
	private boolean turn; //true = player 1, false = player 2.
	private boolean game_state; //True = running, False = game over.
	private boolean boardEnabler;
	private boolean guiEnabler;
	private boolean handEnabler; 
	public boolean updateGui;
	private Spot tempCell;
	private Spot tempBoardCell;
	Random random = new Random();
	private int startRandomizer = random.nextInt(2);
	private ArrayList<Integer> tempList;
	private String music_folder = "rec/music/";
	
	private Score score;
	public Game() {
		score = new Score();
		judge = new Judge();
		pile = new Deck();
		player_one = new Player();
		player_one = initHand(player_one, 7);
		player_two = new Player();
		player_two = initHand(player_two, 7);
		gui = new GUI(player_one.getScore(),player_two.getScore());
		Random random = new Random();
		random.nextInt(1);
		if (startRandomizer == 0) {
			turn = true;
		} else {
			turn = false;
		}
		game_state = true;
		play_board = new Board();
		step = 1;
		boardEnabler = false;
		guiEnabler = false;
		handEnabler = true;
		updateGui = true;
		tempList = new ArrayList<Integer>();
	}

	private Player initHand(Player aPlayer, int initDraw) {
		for(int i = 0; i<initDraw;i++)
		{
			aPlayer.addTile(i,pile.remove());
		}
		return aPlayer;
	}

	public boolean updateGame(Graphics g, boolean mouseState) {
		//if(!updateGui)
		//	return true;
		if(!mouseState)
			updateGui = false;
		play_board.displayBoard(g,tempList);
		gui.updateDeckSize(pile.getSize());
		if(turn) {
			gui.displayTurnOne(g);
			player_one.displayHand(g);
		} else {
			gui.displayTurnTwo(g);
			player_two.displayHand(g);
		}
		gui.displayGui(g);
		if(mouseState)
			displayMovingTile(g,mouseState);
		if(game_state == false)
		{
			game_state = true;
			return false;
		}
		return true;
	}

	private void displayMovingTile(Graphics g,boolean mouseState) {
		tempCell.displayTile(g,(float).8,mouseState);
		
	}

	public boolean boardUpdate() {
		return false;
	}

	public boolean clickCheck(MouseEvent e) {
		boolean tempRet = false;
		updateGui = true;
		boolean m = true;
		while(m==true)
		{
			m = false;
			//int tempIndex = 0;
			switch(step){
				case(1):			//select spot from hand
					if(tempCell!=null && play_board.clickCheck(e)!=null)		//if spot in hand is picked and a valid spot on the board is clicked
					{
						tempBoardCell = play_board.clickCheck(e);
						if(tempBoardCell.getTile()==null)						//if board tile is null (open space)
						{
							try {
								AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.music_folder + "tiledrop.wav"));
								Clip clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
								} catch  (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
									ex.printStackTrace();
								}
							play_board.addTile(tempBoardCell, tempCell.getTile());
																				//add tile to board
							tempList.add(tempBoardCell.getSpotNum());
							if(turn)											//remove from hand
								player_one.remove(tempCell);
							else
								player_two.remove(tempCell);
							++step;
						}
						if(turn)												//set hand opacity to 1
							player_one.setHandOpacity(1f);
						else
							player_two.setHandOpacity(1f);
						tempRet = true;
					}
					else{
						if(turn)												//set tile as selected with opacity .6
						{
							tempCell = player_one.clickCheck(e);
							player_one.setHandOpacity(1f);
							//tempIndex = player_one.getHand().indexOf(tempCell);
							player_one.getHand().get(player_one.getHand().indexOf(tempCell)).setOpacity(.6f);
							
						}
						else
						{
							tempCell = player_two.clickCheck(e);
							player_two.setHandOpacity(1f);
							//tempIndex = player_two.getHand().indexOf(tempCell);
							player_two.getHand().get(player_two.getHand().indexOf(tempCell)).setOpacity(.6f);
						}
						tempRet = true;
					}
					updateWord(tempList);
					return tempRet;
					//break;
				case(2):
					handEnabler = false;
					guiEnabler = true;
					if(gui.clickCheckEnd(e))
					{
						++step;
						m = true;
					}
					else if(gui.clickCheckUndo(e))
					{
						--step;
						handEnabler = true;
						guiEnabler = false;
						if(turn)											//add to hand
							player_one.addTile(tempBoardCell.getTile());
						else
							player_two.addTile(tempBoardCell.getTile());
						play_board.addTile(tempBoardCell, null);
						tempCell = null;
						//tempIndex = 0;
						tempBoardCell = null;
						tempList.clear();
						break;
					}
					else
					{
						if(play_board.clickCheck(e)!=null)
						{
							if(verifySpot(play_board.clickCheck(e).getSpotNum()))
							{
								System.out.println("verify accepted");
								tempList.add(play_board.clickCheck(e).getSpotNum());
								Collections.sort(tempList);
							}
							else if(tempList.contains(play_board.clickCheck(e).getSpotNum()))
							{
								int tempSpotNum = play_board.clickCheck(e).getSpotNum();
								if(tempSpotNum!=tempBoardCell.getSpotNum() 
										&& (tempSpotNum == tempList.get(0)
										|| tempSpotNum == tempList.get(tempList.size()-1)))
								{
									tempList.remove(tempList.indexOf(play_board.clickCheck(e).getSpotNum()));
									System.out.println("removal accepted");
								}
							}
							else
							{
								System.out.println("selected a bad tile");
								//tempBoardCell = null;
							}
						updateWord(tempList);
						if(gui.clickCheckEnd(e))
							++step;
						}
						else
							System.out.println("invalid selection");
						break;
					}
					//break;
				case(3):			//submit word to judge
						++step;
						if(tempList!=null)
						{
							ArrayList<Tile> tempListAlt = listConvert(tempList);
							if(judge.getResult(tempListAlt))
							{
								if(turn)
									player_one.setScoreRemoval(tempListAlt);
								else
									player_two.setScoreRemoval(tempListAlt);
								for(int x: tempList)
								{
									play_board.removeTile(x);
								}

							}
						}
						tempList = new ArrayList<Integer>();
					break;
				case(4):			//turn finalization
					if(turn)
						player_one.addTile(pile.remove());
					else
						player_two.addTile(pile.remove());
					if(play_board.isFull()||pile.isEmpty()||player_one.getScore()>=50||player_two.getScore()>=50) {
						System.out.println("game OVER");
						game_state = false;
					}
					else 
					{
						if(turn)
							turn = false;
						else
							turn = true;
						handEnabler = true;
						guiEnabler = false;
						boardEnabler = false;
						step = 1;
						tempCell = null;
						tempBoardCell = null;
					}
					break;
			}
		}
		updateWord(tempList);
		gui.updateScore(player_one.getScore(), player_two.getScore());
		return false;
	}
	private void updateWord(ArrayList<Integer> aTempList)
	{
		String word = getSelectedString(aTempList);
		int wordScore = 0;
		boolean aFlag = false;
		if(judge.getResult(word))
		{
			aFlag = true;
			wordScore = score.getPointsLocal(listConvert(aTempList));
		}
		gui.updateWordCheck(word,wordScore,aFlag);
	}
	private String getSelectedString(ArrayList<Integer> aTempList)
	{
		String word = "";
		ArrayList<Tile> tiles = listConvert(aTempList);
		for (Tile tile : tiles){
			word += tile.getLetter();
		}
		return word;
	}
	private ArrayList<Tile> listConvert(ArrayList<Integer> aTempList) {
		ArrayList<Tile> tempTileList = new ArrayList<Tile>();
		for(int x: aTempList)
		{
			tempTileList.add(play_board.getTile(x));
		}
		return tempTileList;
	}

	private boolean verifySpot(int spotNum) {
		if(tempList.size() == 0)
			return true;
		else if(tempList.indexOf(spotNum)!=-1)
			return false;
		else if(tempList.size()==1)
		{
			if(tempList.get(0)+7==spotNum||tempList.get(0)-7==spotNum)
				return true;
			else
			{
				if((tempList.get(0)-1)%7-(spotNum-1)%7 == -1||(tempList.get(0)-1)%7-(spotNum-1)%7 == 1)
				{
					if(tempList.get(0)+1 == spotNum||tempList.get(0)-1 == spotNum)
						return true;
				}
			}
			return false;
		}
		else
		{
			if(tempList.get(0)+7==tempList.get(1)||tempList.get(0)-7==tempList.get(1))
			{
				if(tempList.get(0)-7==spotNum||tempList.get(tempList.size()-1)+7==spotNum)
					return true;
			}
			else if((tempList.get(0)-1)%7-(spotNum-1)%7==1||(tempList.get(tempList.size()-1)-1)%7-(spotNum-1)%7==-1)
			{
				if(tempList.get(0)-1 == spotNum||tempList.get(tempList.size()-1)+1 == spotNum)
					return true;
			}
			else
				return false;
			return false;
		}
	}
	
	public int getPlayerScore(boolean opt){
		if(opt)
			return player_one.getScore();
		else
			return player_two.getScore();
	}

	public void displayMovingTile(int m, int n) {
		PointerInfo a = MouseInfo.getPointerInfo();
		int x =a.getLocation().x - m;
		int y =a.getLocation().y - n;
		System.out.println("x: " + a.getLocation().getX() + " y: " + a.getLocation().getY());
		try {
			tempCell.displayMovedTile(x,y);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void resetTile(MouseEvent e) {
		try {
			if(!clickCheck(e))
				tempCell.displayMovedTile(0, 0);
		} catch (Exception ao1) {
			// TODO Auto-generated catch block
			ao1.printStackTrace();
		}
		
	}

}
