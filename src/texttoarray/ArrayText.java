package texttoarray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayText {
    
    private String filepath;
    private BufferedReader file;
    
    private String originalText;
    private ArrayList<ArrayList> arrayListText;
    
    public ArrayText( String filepath ) {

        this.filepath = filepath;  
        
        try {
            
            openFile();

            splitFile();
            
        } catch (FileNotFoundException ex) {
            System.err.println("No file found...");
        } catch (IOException ex) {
            System.err.println("There was a problem with this file...");
        }

    }

    private void openFile() throws FileNotFoundException {

        file = new BufferedReader(new FileReader(filepath));

    }

    private void splitFile() throws IOException {

        // Initialize outputs
        originalText = "";
        arrayListText = new ArrayList();
        
        // Every line
        String line;
        while ((line = file.readLine()) != null) {
            
            // Fill original text
            originalText += line + "\n";
            
            // Split words on whitespaces
            String[] wordsArray = line.split("[\\n \\r \\s]+");
            
            //TODO: split on symbols
            
            // Store words in an arraylist
            ArrayList<String> words = new ArrayList();
            words.addAll(Arrays.asList(wordsArray));
            
            // Add "line" arraylist to "text" arraylist
            arrayListText.add(words);
        }

    }
    
    public String getText() { return originalText; }
    
    public ArrayList<ArrayList> getArray() { return arrayListText; }

    public String get(int i, int j) {        
        return (String) arrayListText.get(i).get(j);
    }
}