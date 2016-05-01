package texttoarray;

public class TextToArray {

    public static void main(String[] args) {

        String filepath = "test.txt";
        
        ArrayText a = new ArrayText(filepath);
        
        System.out.println(a.get(0, 1));
    }
    
}
