package texttoarray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayText {
    
    private String filepath;
    private BufferedReader file;
    
    /* TODO: Add options
    private boolean removeEmptyLines;
    private boolean splitSymbols;
    */
    
    private String originalText;
    private ArrayList<ArrayList> arrayListText;
    
    
    public ArrayText(String filepath, 
            boolean removeEmptyLines, boolean splitSymbols ) {

        this.filepath = filepath;  
        
        /* TODO
        this.removeEmptyLines = removeEmptyLines;
        this.splitSymbols = splitSymbols;
        */
        
        try {
            
            openFile();

            splitFile();
            
        } catch (FileNotFoundException f) {
            System.err.println("No file found");
            System.exit(1);
        } catch (IOException io) {
            System.err.println("There was a problem with this file");
            System.exit(2);
        } catch (Exception ex) {
            System.err.println(ex);
            System.exit(3);
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


            // Split line into words
            ArrayList<String> words = splitLineIntoWords(line);
            
            //TODO: split on symbols
            
            /* TODO
             * If remove empty lines is enabled and this line is empty,
             * continue with next line */

            // Add "line" arraylist to "text" arraylist
            arrayListText.add(words);
        }

    }
    
    private ArrayList<String> splitLineIntoWords(String line) {
        
        ArrayList<String> words = new ArrayList();

        if (line.isEmpty()) {
            words.add("");
            return words;
        }

        char[] characters = line.toCharArray();
        String word = "";
        boolean inWord = false;

        // find words dividing by spaces
        for(char c: characters) {

            switch(c) {
                case '\n':
                case '\r':
                case '\t':
                case '\0':
                case ' ':
                    if( inWord ) { // if it was reading a word and found a whitespace
                        words.add(word); // store current word
                        inWord = false; // turn inWord to false
                        word = ""; //
                    }
                    break;
                default:
                    if( !inWord )  inWord = true;

                    word += c;
            }

        }
        
        // if line ended and the last character was a letter then it's a finished word
        if( inWord )  words.add(word);

        return words;
    }
    
    public String getText() { return originalText; }
    
    public ArrayList<ArrayList> getArray() { return arrayListText; }

    public int getNumberOfLines() { return arrayListText.size(); }
    
    public int getNumberOfWords() { 
        
        int numberOfWords = 0;
        
        for(ArrayList line : arrayListText)
            numberOfWords += line.size();
            
        return numberOfWords; 
    }
    
    public int getNumberOfWords(int line) { return arrayListText.get(line).size(); }
    
    /**
     * Get element of ArrayText by its coordinates.
     * @param lineNum line number
     * @param wordNum word number on this line
     * @return requested word as a string
     * @throws java.lang.Exception 
     */
    public String get(int lineNum, int wordNum) throws Exception {
              
        try {
            
            String word = (String) arrayListText.get(lineNum).get(wordNum);
            return word;
            
        } catch (IndexOutOfBoundsException b) {
            return "";
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    public void printAsTable() {
        
        // find max line size
        int maxWords = -1;
        for(ArrayList line: arrayListText) 
            if ( line.size() > maxWords )
                maxWords = line.size();
        
        // find max word length
        int maxWordLength = -1;
        for(ArrayList line: arrayListText) {
            for(Object word: line) {
                
                int wordLength = word.toString().length();
                
                if ( wordLength > maxWordLength )
                    maxWordLength = wordLength;                
            }
        }

        // draw rows
        int lines = arrayListText.size();
        int words;
        
        for(int line=0; line<lines; line++) {
            
            System.out.print("|");
            words = arrayListText.get(line).size();
            
            for(int word=0; word<maxWords; word ++) {
                
                try {
                    System.out.print(String.format("%"+ maxWordLength+"s",get(line,word)));
                    System.out.print("|");
                } catch (Exception ex) {
                    System.err.println(ex);
                }
                
            }
            
            System.out.println();
            
        }
            
    }
}