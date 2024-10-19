public class PlanetTester {
    public static void main(String[] args) {
        int seconds = 10_000_000;
        for(int i = 0; i < Planet.values().length; i++){
            double actual = Planet.values()[i].calculateAge(seconds);
            double expected = ((double)seconds/ 31557600) * Planet.values()[i].getValue();
            System.out.println(Planet.values()[i]);
            System.out.println(expected == actual);
        }

    }
}
