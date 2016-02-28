/**
 * This code was created by
 * Edited by Omar Alamoudi
 * */
/*
 * Import related libraries
 * */
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
	
    /**
     * Starts a Deck with shuffled tiles
     * */
	public Deck() {
        // Setup the deck
		this.deck = new CopyOnWriteArrayList<Tile>();
        // generate the alphabet tiles
		this.generate_alphabet();
        // shuffle the deck
		this.shuffle();
	}
	
    /**
     * Generate the alphabet tile for the deck 
     * */
	private void generate_alphabet() {
		this.alphabet = new HashMap<Tile,Integer>();
		
		this.dataHandling();
		for(Map.Entry<Tile,Integer> u: alphabet.entrySet())
		{
			for(int x = 1; x <= u.getValue();x++)
				this.deck.add(u.getKey());
		}
	}
	
    /**
     * Reading the tiles values from the data file tile.data
     * ading the tiles to the alphabet collection
     * */
	private  void dataHandling(){
		// initiate line variable
        String line = null;
		
		
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(this.dataFile);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            // initiate the part of the line string array
            String[] parts;
            // if there is a line in the folder continue
            while((line = bufferedReader.readLine()) != null) {
                // Store an array of line parts
                parts= line.split(",");
                /*
                 * Adding elements to the alphabet hashMap in this form
                 * this.alphabet.put( new Tile ( <alphabet character>, <alphabet score>), <alphabet occurrence>);
                 * */
                this.alphabet.put(new Tile(parts[0].charAt(0),Integer.parseInt(parts[1])), Integer.parseInt(parts[2]));
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
	

    /**
     * Shuffle the collection of the deck
     * */
	public void shuffle() {
		Collections.shuffle(this.deck);
	}
	
    /**
     * Add a card to the deck
     * @param card as tile 
     * */
	private void addCard(Tile card) {
		this.deck.add(card);
	}
	/**
     * Remove tile from deck
     * 
     * */
	public Tile remove() {
		return this.deck.remove(0);
	}
	
	/**
     * Check if the deck is empty
     * @return true if deck is empty, false if deck hold something
     * */
	public int getSize(){
		return deck.size();
	}
	public boolean isEmpty() {
		if (this.deck.isEmpty()) {
			return true;
		}
		return false;
	}
}
