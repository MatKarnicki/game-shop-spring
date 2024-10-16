    import java.util.Scanner;
    public class PatternBuilder {
        static String patternSymbol = "X";
        public static void main(String[] args){        
        Scanner myScanner = new Scanner(System.in);
        int numberOfRows;
         do {
            System.out.print("Podaj liczbę naturalną n: ");
            if (!myScanner.hasNextInt()) {
                System.out.println("To nie jest liczba! Podaj liczbę naturalną.");
                myScanner.next();
            }
            numberOfRows = myScanner.nextInt();
            if (numberOfRows <= 0) {
                System.out.println("Liczba musi być większa od 0.");
            }
        } while (numberOfRows <= 0);
        String expected = "X\nXX\nXX\nX\nXX\n X\n X\nXX\n";
        String actual = createPatterns(numberOfRows, "X");
        System.out.println(expected.equals(actual));
        myScanner.close();
   }

    public static String createPatterns(int numberOfRows) {
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 1; i <= numberOfRows; i++) {
            for (int j = 1; j <= i; j++ ){
                result.append(patternSymbol);
            }
            result.append("\n");
        }
        
        for (int i = numberOfRows; i > 0; i--) {
            for (int j = 1; j <= i; j++ ){
                result.append(patternSymbol);
            }
            result.append("\n");
        }
        
        for (int i = 1; i <= numberOfRows; i++) {
            for (int j = 1; j < i; j++){
                result.append(" ");
            }
            for (int j = 1; j <= numberOfRows - i + 1; j++ ){
                result.append(patternSymbol);
            }
            result.append("\n");
        }
        
        for (int i = numberOfRows; i > 0; i--) {
            for (int j = 1; j < i; j++){
                result.append(" ");
            }
            for (int j = 1; j <= numberOfRows - i + 1; j++ ){
                result.append(patternSymbol);
            }
            result.append("\n");
        }
        return result.toString();
        
    }

    public static String createPatterns(int numberOfRows, String Character){
        patternSymbol = Character;
        return createPatterns(numberOfRows);
    }
}
