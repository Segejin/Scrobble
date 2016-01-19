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
