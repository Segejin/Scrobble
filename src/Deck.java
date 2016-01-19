import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Deck {
	private List<Tile> deck;
	private Map<Tile,Integer> alphabet;
	private String dataFile = "rec/tile.data";
	

	public Deck() {
		deck = new CopyOnWriteArrayList<Tile>();
		generate_alphabet();
		shuffle();
	}
	
	private void generate_alphabet() {
		alphabet = new HashMap<Tile,Integer>();
		
		this.dataHandling();
		for(Map.Entry<Tile,Integer> u: alphabet.entrySet())
		{
			for(int x = 1; x <= u.getValue();x++)
				deck.add(u.getKey());
		}
	}
	
	private  void dataHandling(){
		String line = null;
		
		
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(this.dataFile);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String[] parts;
            while((line = bufferedReader.readLine()) != null) {
                parts= line.split(",");
                alphabet.put(new Tile(parts[0].charAt(0),Integer.parseInt(parts[1])), Integer.parseInt(parts[2]));
            }
		}
        catch(FileNotFoundException ex) {
            System.err.println(
                "Unable to open file '" + 
                this.dataFile + "'");                
        }
        catch(IOException ex) {
            System.err.println(
                "Error reading file '" 
                + this.dataFile + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }

	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	private void addCard(Tile card) {
		deck.add(card);
	}
	
	public Tile remove() {
		return deck.remove(0);
	}
	
	
	public boolean isEmpty() {
		if (deck.isEmpty()) {
			return true;
		}
		return false;
	}
}
