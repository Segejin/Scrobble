import java.util.List;



public class Score {
	private int score=0;
	private List<Tile> list;
	
	public Score(){

		
	}
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
	
	private void calculate_score(){
		for(Tile tile: this.list){
			this.score += tile.getScore();
//			System.out.println("here, score= "+this.score);
		}
		this.bonus();
		
	}
	private void bonus(){
		this.score += this.list.size()-4;
	}
	

}
