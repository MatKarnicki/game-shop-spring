

package lab3.zad2.service;

import lab3.zad2.domain.Person;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public static Map<String, Person> loadFromCSV(String fileName) {
        Map<String, Person> personMap = new HashMap<>();
        try {

            CSVReader csvReader = new CSVReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()));
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                String id = nextRecord[0];
                String name = nextRecord[1];
                String surname = nextRecord[2];
                int age = Integer.parseInt(nextRecord[3]);
                String role = nextRecord[4];
                Person person = new Person(id, name, surname, age, role);
                personMap.put(id, person);
            }

            csvReader.close(); // close the reader
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personMap;
    }

}
