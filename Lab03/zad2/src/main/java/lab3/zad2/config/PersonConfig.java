package lab3.zad2.config;

import lab3.zad2.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public Person president() {
        return new Person("Kappa","John", "Doe",32, "President");
    }

    @Bean
    public Person vicePresident() {
        return new Person("Beta","Eith", "GoldenStein",20,  "Vice President");
    }

    @Bean
    public Person secretary() {
        return new Person("Gamma","Emily", "Doe",53 , "Secretary");
    }
}