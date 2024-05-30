package Week10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Scanner;

final public class Temperature implements Comparable<Temperature> {
    final static public float KELVIN_OFFSET = 273.15f;
    private float kelvin;

    private static final Logger LOG = LoggerFactory.getLogger(Temperature.class);

    private Temperature(float kelvin) throws InvalidTempInputException {
        if (validateTemperature(kelvin)){
            this.kelvin = Math.max(kelvin, 0f);
        }
        else {
            LOG.error("InvalidTempInput: " + kelvin);
            throw new InvalidTempInputException("InvalidTempInput: " + kelvin);
        }
    }

    static public Temperature createFromKelvin(float kelvin) throws InvalidTempInputException {
        return new Temperature(kelvin);
    }

    static public Temperature createFromCelsius(float celsius) throws InvalidTempInputException {
        return new Temperature(convertCelsiusToKelvin(celsius));
    }

    public void newFahrenheitTemp(float fahrenheit) throws InvalidTempInputException {
        new Temperature(fahrenheitToKelvin(fahrenheit));
    }

    public float getKelvin() {
        return kelvin;
    }

    public float getCelsius() {
        return convertKelvinToCelsius(this.kelvin);
    }

    public void setKelvin(float newKelvin) {
        this.kelvin = newKelvin;
    }

    public void setCelsius(float newCelsius) {
        this.kelvin = convertCelsiusToKelvin(newCelsius);
    }

    public static boolean validateTemperature(float kelvinToValidate) {
        try {
            if (kelvinToValidate < 0){
                return false;
            }
        } catch (NumberFormatException exc) {
            return false;
        }
        return true;
    }

    public static boolean validateTemperature(String kelvinToValidate) {
        try {
            float value = Float.valueOf(kelvinToValidate);
            return validateTemperature(value);
        } catch (NumberFormatException exc) {
            return false;
        }
    }

    static public float fahrenheitToKelvin(float f) {
        return (f - 32) * 5 / 9 + KELVIN_OFFSET;
    }

    static public float convertCelsiusToKelvin(float c) {
        return c + KELVIN_OFFSET;
    }

    static public float convertKelvinToCelsius(float k) {
        return k - KELVIN_OFFSET;
    }

    @Override
    final public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Temperature temp = (Temperature) obj;
        int equal = Float.compare(temp.kelvin, this.kelvin);

        if (equal == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Value in Kelvin: " + this.kelvin;
    }

    @Override
    final public int hashCode() {
        return Objects.hash(kelvin);
    }

    @Override
    final public int compareTo(Temperature otherTemp) {
        return Float.compare(this.kelvin, otherTemp.kelvin);
    }
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Bitte Temperatur eingeben (oder 'exit' zum Beenden): ");
            input = scanner.next();
            if (validateTemperature(input)){
                float value = Float.valueOf(input);
                System.out.println("Valid temperature input: " + value);
            }
            else if (!"exit".equalsIgnoreCase(input)){
                System.out.println("Invalid input, please enter valid input.");
                LOG.debug("Invalid input, please enter valid input.");
            }
        } while (!"exit".equalsIgnoreCase(input));
        System.out.println("Program beendet.");
    }
}

