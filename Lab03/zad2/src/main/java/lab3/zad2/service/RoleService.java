package lab3.zad2.service;

import lab3.zad2.domain.Person;
import lab3.zad2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final Person president;
    private final Person vicePresident;
    private final Person secretary;
    private final List<Person> additionalPersons;

    @Autowired
    public RoleService(Person president, Person vicePresident, Person secretary, PersonService personService) {
        this.president = president;
        this.vicePresident = vicePresident;
        this.secretary = secretary;

        // Load additional persons from the CSV file
        String fileName = "src/main/resources/file.csv";
        Map<String, Person> people = personService.loadFromCSV(fileName);

        // Collect additional persons, excluding the main roles
        this.additionalPersons = people.values().stream()
                .filter(person -> !person.equals(president) && !person.equals(vicePresident) && !person.equals(secretary))
                .collect(Collectors.toList());
    }

    public void printAllRoles() {
        System.out.println("Main Roles:");
        System.out.println(president);
        System.out.println(vicePresident);
        System.out.println(secretary);
        System.out.println("Additional Roles:");
        additionalPersons.forEach(System.out::println);
    }
}
