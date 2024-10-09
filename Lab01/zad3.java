import java.util.Scanner;

public class zad3 {

    public static void main(String[] args) {
        menu();
        Scanner myReader = new Scanner(System.in);
        int answer = Integer.parseInt(myReader.nextLine());
        System.out.println("Podaj wiek w sekundach: ");
        double seconds = myReader.nextInt();
        myReader.nextLine();
        myReader.close();
        System.out.println(Planet.values()[answer-1].calculateAge(seconds));
    }

    public static void menu() {
        System.out.println("wybierz planetę");
        System.out.println("1. Merkury");
        System.out.println("2. Wenus");
        System.out.println("3. Ziemia");
        System.out.println("4. Mars");
        System.out.println("5. Jowisz");
        System.out.println("6. Saturn");
        System.out.println("7. Uran");
        System.out.println("8. Neptun");
    
    }
    public static void menuError(){
        System.out.println("Wybrano złą opcje");
    }
   
}
