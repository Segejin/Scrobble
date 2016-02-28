/**
 * This code was created by Omar Alamoudi
 * edited by
 * */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Judge{
	private String fileName = "rec/US.dic";
	private String word;
	private List<String> dict = new ArrayList<String>();
	/**
     * Create judge instanse 
     * */
    public Judge(){
		this.dictHandling();
	}
	
    /**
     * Read the dictionary from a file and save it in a dict list 
     * */
	private  void dictHandling(){
        // initiate line containor
		String line = null;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(this.fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                // skip all the words are less than 2 char
                if (line.length() >2){
                    // add a word to the dictionary list
                	this.dict.add(line);
                }
            }
		}
        catch(FileNotFoundException ex) {
            System.err.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.err.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }

	}
	
    /**
     * Get the result if the tile list forming the word is in dictionary
     * @param tiles is a list of tiles forming a word
     * @return true if word exists
     * @return false if word doesn't exists in dictionary
     * */
	public boolean getResult(List<Tile> tiles){
		this.word = "";
		for (Tile tile : tiles){
			this.word += tile.getLetter();
		}

		return this.dict.contains(this.word);
	}
	
    /**
     * Get the result if word is in dictionary
     * @param word is a word to combare with the dictionary
     * @return true if word exists
     * @return false if word doesn't exists in dictionary
     * */
	public boolean getResult(String word)
	{
		this.word=word;
        return this.dict.contains(this.word);
	}
	
}
