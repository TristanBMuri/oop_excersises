package Week8;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class Person implements Comparable<Person> {
    private final long ID;
    private String Nachname;
    private String Vorname;

    public Person(String Nachname, String Vorname) {
        this.ID = newID();
        this.Nachname = Nachname;
        this.Vorname = Vorname;
    }

    public Person(Long ID, String Nachname, String Vorname) {
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

}
