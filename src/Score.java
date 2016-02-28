/*
 * This code is created by Omar Alamoudl
 * */

import java.util.List;



public class Score {
	private int score;
	private List<Tile> list;
	
	public Score(){
        this.score = 0;
	}

    /**
     * Get the points of a list of tiles
     * @param list a list of tiles
     * @return the total score of the list of tiles
     * */
	public int getPoints(List<Tile> list){
		this.list = list;
		calculate_score();
		return this.score;
	}

	public int getPointsLocal(List<Tile> list){
		int aScore = calculate_score_local(list);
		return aScore;
	}
	private int calculate_score_local(List<Tile> list){
		int aScore = 0;
		for(Tile tile: list){
			aScore += tile.getScore();
//			System.out.println("here, score= "+this.score);
		}
		aScore += list.size()-4;
		return aScore;
	}
	public void reset(){
		this.score=0;
	}
	
    /**
     * Calculate the score of the list of tile
     * */
	private void calculate_score(){
        // iterate in the list of tile to get score value
		for(Tile tile: this.list){
            // Add the tile score to the total score
			this.score += tile.getScore();
//			System.out.println("here, score= "+this.score);
		}
        // Calculate the bonus 
		this.bonus();
		
	}

    /**
     * Calculate the bonus and add it to the score
     * */
	private void bonus(){
        /* 
         * Increase score if the list size is bigger than 4, 
         * by the size of the list -4  
         * for example size = 6 bonus = 2
         * Decrease score if list is less than 4, 
         * by the size of the list -4
         * for example size = 3 bonus= -1
         **/   
		this.score += this.list.size()-4;
	}
	

}
