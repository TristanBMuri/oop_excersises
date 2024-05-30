package Week11;

import static org.junit.jupiter.api.Assertions.*;

import Week11.Car.Car;
import Week11.Car.Motor;
import Week11.Car.StillRunningException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        // Assuming Motor and Light can be instantiated without any setup.
        car = new Car("Tesla Model S");
    }

    @Test
    void testCarInitialization() {
        assertEquals("Tesla Model S", car.getModel());
        assertFalse(car.isRunning());
        assertNotNull(car.getMotor());
    }

    @Test
    void testSwitchOnOff() {
        assertFalse(car.isRunning(), "Car should initially be off");

        car.switchOn();
        assertTrue(car.isRunning(), "Car should be running after switch on");

        car.switchOff();
        assertFalse(car.isRunning(), "Car should be off after switch off");
    }

    @Test
    void testSetMotorWhileRunning() {
        car.switchOn(); // First switch on to ensure the car is running.
        Motor newMotor = new Motor();

        Exception exception = assertThrows(StillRunningException.class, () -> car.setMotor(newMotor));
        assertEquals("Turn Car off first!", exception.getMessage());
    }

    @Test
    void testLightsBehaviorWhenSwitching() {
        car.switchOn();
        assertTrue(car.getLightFrontLeft().isSwitchedOn(), "Front left light should be on after car is switched on");
        assertTrue(car.getLightFrontRight().isSwitchedOn(), "Front right light should be on after car is switched on");

        car.switchOff();
        assertFalse(car.getLightFrontLeft().isSwitchedOn(), "Front left light should be off after car is switched off");
        assertFalse(car.getLightFrontRight().isSwitchedOn(), "Front right light should be off after car is switched off");
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(Car.class).usingGetClass().withOnlyTheseFields("model", "motor").suppress(Warning.NONFINAL_FIELDS).verify();
    }

}
