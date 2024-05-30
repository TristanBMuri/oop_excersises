package Week9;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Collection;

public class TemperatureHistoryTests {

    @Before
    public void setUp() {
        TemperatureHistory temperatureHistory = new TemperatureHistory();
    }

    @Test
    public void testAddTemperatur() {
        TemperatureHistory temperatureHistory = new TemperatureHistory();

        Temperature temp = new Temperature(25.0f); // Assuming 25 Celsius
        temperatureHistory.add(temp);
        assertEquals("Count should be 1 after adding a temperature", 1, temperatureHistory.getCount());
    }

    @Test
    public void testGetCount() {
        TemperatureHistory temperatureHistory = new TemperatureHistory();

        assertEquals("Initial count should be 0", 0, temperatureHistory.getCount());
        temperatureHistory.add(new Temperature(20.0f));
        temperatureHistory.add(new Temperature(22.0f));
        assertEquals("Count should be 2 after adding two temperatures", 2, temperatureHistory.getCount());
    }

    @Test
    public void testClear() {
        TemperatureHistory temperatureHistory = new TemperatureHistory();

        temperatureHistory.add(new Temperature(20.0f));
        temperatureHistory.add(new Temperature(22.0f));
        temperatureHistory.clear();
        assertEquals("Count should be 0 after clearing", 0, temperatureHistory.getCount());
    }

    @Test
    public void testMaxTemperature() {
        TemperatureHistory temperatureHistory = new TemperatureHistory();

        temperatureHistory.add(new Temperature(10.0f));
        temperatureHistory.add(new Temperature(20.0f));
        temperatureHistory.add(new Temperature(30.0f));
        float maxTemp = temperatureHistory.maxTemperature();
        assertEquals("Max temperature should be 30.0f", 30.0f, maxTemp, 0.0f);
    }

    @Test
    public void testMinTemperature() {
        TemperatureHistory temperatureHistory = new TemperatureHistory();

        temperatureHistory.add(new Temperature(10.0f));
        temperatureHistory.add(new Temperature(20.0f));
        temperatureHistory.add(new Temperature(30.0f));
        float minTemp = temperatureHistory.minTemperature();
        assertEquals("Min temperature should be 10.0f", 10.0f, minTemp, 0.0f);
    }

    @Test
    public void testAverageTemperature() {
        TemperatureHistory temperatureHistory = new TemperatureHistory();

        temperatureHistory.add(new Temperature(10.0f));
        temperatureHistory.add(new Temperature(20.0f));
        temperatureHistory.add(new Temperature(30.0f));
        float average = temperatureHistory.averageTemperature();
        assertEquals("Average temperature should be 20.0f", 20.0f, average, 0.0f);
    }

}
