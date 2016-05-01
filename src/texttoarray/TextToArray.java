package texttoarray;

public class TextToArray {

    public static void main(String[] args) throws Exception {

        String filepath = "test.txt";
        
        ArrayText a = new ArrayText(filepath, true, false); 
        a.printAsTable();

    }
    
}
