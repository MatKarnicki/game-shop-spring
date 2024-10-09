    import java.util.Scanner;
    public class Zad4 {
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
        System.out.println(createPatterns(numberOfRows));
        myScanner.close();
   }


    public static String createPatterns(int numberOfRows) {
        
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= numberOfRows; i++) {
            result.append("X".repeat(i)).append("\n");
        }

        for (int i = numberOfRows; i > 0; i--) {
            result.append("X".repeat(i)).append("\n");
        }

        for (int i = 1; i <= numberOfRows; i++) {
            result.append(" ".repeat(i - 1))
                  .append("X".repeat(numberOfRows - i + 1))
                  .append("\n");
        }

        for (int i = numberOfRows; i > 0; i--) {
            result.append(" ".repeat(i - 1))
                  .append("X".repeat(numberOfRows - i + 1))
                  .append("\n");
        }
        return result.toString();

    }
}

