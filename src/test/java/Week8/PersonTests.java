package Week8;

import org.junit.jupiter.api.Test;
import nl.jqno.equalsverifier.*;

import  org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

final class PersonTests {
    private Class<Person> personClass;

    @Test
    void testConstructor(){
        Person testPerson = new Person(5L, "Hans", "Peter");

        assertEquals(5L, testPerson.getID());
        assertEquals("Hans", testPerson.getNachname());
        assertEquals("Peter", testPerson.getVorname());
    }

    @Test
    void testFalsePositiveLastName(){
        Person testPerson = new Person("Hans", "Peter");
        assertNotEquals("NichtHans", testPerson.getNachname());
    }

    @Test
    void checkID(){
        Person testPerson = new Person("Hans", "Peter");
        assertTrue(testPerson.getID() > 0);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(Person.class).usingGetClass().withOnlyTheseFields("ID").verify();
    }

    @Test
    void equalsFalsePositive() {
        Person testPerson = new Person(5L, "Hans", "Peter");
        Person comparePerson = new Person(6L, "Hans", "Peter");

        assertNotEquals(testPerson, comparePerson);
    }
}
