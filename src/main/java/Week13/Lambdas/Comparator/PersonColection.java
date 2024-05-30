package Week13.Lambdas.Comparator;

import java.util.ArrayList;

public class PersonColection {
    private ArrayList<Person> people;

    public PersonColection() {
        people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }
    public ArrayList<Person> getPeople() {
        return people;
    }
}
