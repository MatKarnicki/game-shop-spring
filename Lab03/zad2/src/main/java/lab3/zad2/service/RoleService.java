package lab3.zad2.service;

import lab3.zad2.domain.Person;
import lab3.zad2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    private final Person president;
    private final Person vicePresident;
    private final Person secretary;
    private final List<Person> additionalPersons;


    @Autowired
    public RoleService(Person president, Person vicePresident, Person secretary, PersonService personService, @Value("${file.path}") String fileName) {
        this.president = president;
        this.vicePresident = vicePresident;
        this.secretary = secretary;
        Map<String, Person> people = personService.loadFromCSV(fileName);

        this.additionalPersons = new ArrayList<>(people.values());
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
