package Week10;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;

import static Week10.Temperature.KELVIN_OFFSET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemperatureTests {

    private static final float DELTA = 1e-15f; // A small delta for floating-point comparisons

    private Temperature temperatur;

    @Before
    public void setUp() throws InvalidTempInputException {
        temperatur = Temperature.createFromCelsius(0.0f); // Initialize with 0 Celsius which is 273.15 Kelvin
    }

    @Test
    public void testGetCelsius() throws InvalidTempInputException {
        Temperature testTemp = Temperature.createFromKelvin(KELVIN_OFFSET);

        assertEquals("Celsius should be 0.0", 0.0f, testTemp.getCelsius(), DELTA);
    }

    @Test
    public void testGetKelvin() throws InvalidTempInputException {
        Temperature testTemp = Temperature.createFromKelvin(273.15f);
        assertEquals("Kelvin should be 273.15", 273.15f, testTemp.getKelvin(), DELTA);
    }

    @Test
    public void testSetCelsius() {
        temperatur.setCelsius(100.0f);
        assertEquals("Celsius should be set to 100.0", 100.0f, temperatur.getCelsius(), DELTA);
    }

    @Test
    public void testSetKelvin() {
        temperatur.setKelvin(373.15f);
        assertEquals("Kelvin should be set to 373.15", 373.15f, temperatur.getKelvin(), DELTA);
    }

    @Test
    public void testConvertCelsiusToKelvin() throws InvalidTempInputException {
        Temperature kelvin = Temperature.createFromCelsius(0.0f);
        assertEquals(273.15f, kelvin.getKelvin(), DELTA);
    }

    @Test
    public void testConvertKelvinToCelsius() {
        float celsius = Temperature.convertKelvinToCelsius(KELVIN_OFFSET);
        assertEquals("273.15 Kelvin should be 0 Celsius", 0.0f, celsius, DELTA);
    }

    // Other tests can include checking the hashCode, equals, toString, and compareTo methods
    // Here is an example for the equals method

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Temperature.class).usingGetClass().withOnlyTheseFields("kelvin").suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void testCompareTo_SameTemperature() throws InvalidTempInputException {
        Temperature sameTemp = Temperature.createFromKelvin(temperatur.getKelvin());
        assertEquals("Comparing the same temperatures should return 0", 0, temperatur.compareTo(sameTemp));
    }

    @Test
    public void testCompareTo_LessThan() throws InvalidTempInputException {
        Temperature higherTemp = Temperature.createFromKelvin(temperatur.getKelvin() + 10.0f);
        assertTrue("Comparing lower to higher temperature should return a negative value", temperatur.compareTo(higherTemp) < 0);
    }

    @Test
    public void testCompareTo_GreaterThan() throws InvalidTempInputException {
        Temperature lowerTemp = Temperature.createFromCelsius(temperatur.getCelsius() -10.0f);
        assertTrue("Comparing higher to lower temperature should return a positive value", temperatur.compareTo(lowerTemp) > 0);
    }
}

