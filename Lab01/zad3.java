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
        double age = seconds / 31557600;
        
        switch (answer) {
            case 1 -> age /= 0.2408467;
            case 2 -> age /= 0.61519726;
            case 3 -> age /= 1;
            case 4 -> age /= 1.8808158;
            case 5 -> age /= 11.862615;
            case 6 -> age /= 29.447498;
            case 7 -> age /= 84.016846;
            case 8 -> age /= 164.79132;
            default -> age = -1;
        }
        System.out.println("Twoj wiek na wybranej planecie to : "+ age);
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
}