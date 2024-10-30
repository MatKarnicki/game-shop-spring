package lab3.zad1;

import lab3.zad1.domain.Person;
import org.springframework.core.io.ClassPathResource;
import lab3.zad1.service.PersonService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Zad1Application {
	public static void main(String[] args) {
		Map<String, Person> people = PersonService.loadFromCSV("file.csv");

		// Print loaded people to verify
		for (Map.Entry<String, Person> entry : people.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
}
