package service;

import domain.Person;

import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import com.opencsv.CSVReader;

public class PersonService {
    private Map<String, Person> personMap = new HashMap<>();

    public Map<String, Person> loadFromCSV(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                String id = nextRecord[0];
                String name = nextRecord[1];
                String surname = nextRecord[2];
                int age = Integer.parseInt(nextRecord[3]);
                Person person = new Person(id, name, surname, age);
                personMap.put(id, person);
            }

            csvReader.close(); // close the reader
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personMap;
    }

    public Map<String, Person> getPersonMap() {
        return personMap;
    }
}
