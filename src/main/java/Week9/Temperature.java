package Week9;

import java.util.Objects;

final public class Temperature implements Comparable<Temperature> {
    final static public float KELVIN_OFFSET = 273.15f;
    private float kelvin;

    public Temperature(float kelvin) {
        if (kelvin < -273.15f) {
            this.kelvin = -273.15f;
        } else {
            this.kelvin = kelvin;
        }

    }

    static public Temperature newCelsiusTemp(float celsius) {
        return new Temperature(celsius + KELVIN_OFFSET);
    }

    public void newFahrenheitTemp(float fahrenheit) {
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

}
