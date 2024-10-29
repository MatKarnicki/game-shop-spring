package lab3.zad1;

import domain.Person;
import service.PersonService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Zad1Application {
	public static void main(String[] args) {
		PersonService personService = new PersonService();
		String fileName = "src/main/resources/file.csv";

		Map<String, Person> people = personService.loadFromCSV(fileName);

		// Print loaded people to verify
		for (Map.Entry<String, Person> entry : people.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
}
