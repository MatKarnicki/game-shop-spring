import java.util.Scanner;

public class Zad2 {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Podaj Liczbę by sprawdzić czy jest liczbą armstronga");
        String answer = myScanner.nextLine();
        System.out.println(armstrongNumber(Integer.valueOf(answer)));
        myScanner.close();
        
    }
    public static boolean armstrongNumber(int number){
        int lenghtOfNumber = Integer.toString(number).length();
        int numberToAdd = Integer.valueOf(number);
        numberToAdd = numberToAdd *10;
        int sum = 0;
        for(int i = 1; i<=lenghtOfNumber; i++){
            numberToAdd = numberToAdd/10;
            sum += Math.pow(numberToAdd%10, lenghtOfNumber);
        }
        return sum == number; 
    }
    
}
