package Week9;

import static Week9.Temperature.KELVIN_OFFSET;
import static org.junit.Assert.*;
import nl.jqno.equalsverifier.*;


import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

public class TemperatureTests {

    private static final float DELTA = 1e-15f; // A small delta for floating-point comparisons

    private Temperature temperatur;

    @Before
    public void setUp() {
        temperatur = new Temperature(0.0f); // Initialize with 0 Celsius which is 273.15 Kelvin
    }

    @Test
    public void testGetCelsius() {
        Temperature testTemp = new Temperature(KELVIN_OFFSET);

        assertEquals("Celsius should be 0.0", 0.0f, testTemp.getCelsius(), DELTA);
    }

    @Test
    public void testGetKelvin() {
        Temperature testTemp = new Temperature(273.15f);
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
    public void testConvertCelsiusToKelvin() {
        Temperature kelvin = Temperature.newCelsiusTemp(0.0f);
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
    public void testCompareTo_SameTemperature() {
        Temperature sameTemp = new Temperature(0.0f);
        assertEquals("Comparing the same temperatures should return 0", 0, temperatur.compareTo(sameTemp));
    }

    @Test
    public void testCompareTo_LessThan() {
        Temperature higherTemp = new Temperature(10.0f);
        assertTrue("Comparing lower to higher temperature should return a negative value", temperatur.compareTo(higherTemp) < 0);
    }

    @Test
    public void testCompareTo_GreaterThan() {
        Temperature lowerTemp = new Temperature(-10.0f);
        assertTrue("Comparing higher to lower temperature should return a positive value", temperatur.compareTo(lowerTemp) > 0);
    }
}

