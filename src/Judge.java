import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Judge{
	private String fileName = "rec/US.dic";
	private String word;
	private List<String> dict = new ArrayList<String>();
	public Judge(){
		this.dictHandling();
	}
	
	private  void dictHandling(){
		String line = null;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(this.fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if (line.length() >2){
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
	
	public boolean getResult(List<Tile> tiles){
		this.word = "";
		for (Tile tile : tiles){
			this.word += tile.getLetter();
		}
		return this.dict.contains(this.word);
	}
	
	
}