package Week13.Lambdas.Comparator;


import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public class Person implements Comparable<Person> {
    private final long ID;
    private String Nachname;
    private String Vorname;

    public Person(String Vorname, String Nachname) {
        this.ID = newID();
        this.Nachname = Nachname;
        this.Vorname = Vorname;
    }

    public Person(Long ID, String Vorname, String Nachname) {
        this.ID = ID;
        this.Nachname = Nachname;
        this.Vorname = Vorname;
    }


    public static long newID() {
        Random newID = new Random();

        return Math.abs(newID.nextLong());
    }

    public long getID() {
        return ID;
    }

    public String getVorname() {
        return Vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public String getFullName() {
        return Nachname + " " + Vorname;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID + ", Vorname: " + this.Vorname + ", Nachname: " + this.Nachname;
    }


    @Override
    final public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return ID == person.ID;
    }

    @Override
    final public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public int compareTo(@NotNull Person other) {
        return Long.compare(this.ID, other.ID);
    }

    public static void main(final String[] args) {
        List<Person> personCollection = new ArrayList<>();

        Person p1= new Person(5L, "John", "Doe");
        Person p2= new Person(4L, "Jane", "Doe");
        Person p3= new Person(3L, "Hoo", "Doe");
        Person p4= new Person(2L, "Bane", "Doe");
        Person p5= new Person(1L, "Dane", "Doe");

        personCollection.add(p1);
        personCollection.add(p2);
        personCollection.add(p3);
        personCollection.add(p4);
        personCollection.add(p5);

        // Sorting the collection
        personCollection.sort((Person cp1, Person cp2)
            -> Long.compare(p2.getID(), p1.getID()));

        System.out.println(personCollection);
    }
}
