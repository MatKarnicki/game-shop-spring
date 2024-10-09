public enum Planet {
    MERKURY(0.2408467), 
    WENUS(0.61519726), 
    ZIEMIA(1), 
    MARS(1.8808158), 
    JOWISZ(11.862615), 
    SATURN(29.447498), 
    URAN(84.016846), 
    NEPTUN(164.79132);
    private final double earthYears;
    
    Planet(double earthYears){
        this.earthYears = earthYears;
    }

    public double getValue() {
        return this.earthYears;
    }

     public double calculateAge(double seconds){
        double age = seconds / 31557600;
        return age * this.getValue();
    }
}

