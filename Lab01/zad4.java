import java.util.Scanner;

public class zad4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Podaj liczbę naturalną n: ");
            if (!scanner.hasNextInt()) {
                System.out.println("To nie jest liczba! Podaj liczbę naturalną.");
                scanner.next();
            }
            n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Liczba musi być większa od 0.");
            }
        } while (n <= 0);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("X");
            }
            System.out.println();
        }

        for (int i = n; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("X");
            }
            System.out.println();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = n; j >= i; j--) {
                System.out.print("X");
            }
            System.out.println();
        }

        for (int i = n; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = n; j >= i; j--) {
                System.out.print("X");
            }
            System.out.println();
        }

        scanner.close();
    }
}

//zrobic stringi a nie printy
//mnozenie stringow z repeat
//String builder