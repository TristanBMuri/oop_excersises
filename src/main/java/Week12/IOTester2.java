package Week12;

import Week10.InvalidTempInputException;
import Week11.Temperature.Temperature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static Week11.Temperature.Temperature.createFromKelvin;

public class IOTester2 {
    private static final Logger LOG = LoggerFactory.getLogger(IOTester2.class);

    public static void writeTextFile(String fileName, String input) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),
                StandardCharsets.UTF_8))) {
            writer.write(input);
            writer.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static void writeIntTextFile(String fileName) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            out.writeInt(77);
            out.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static void writeTemperatureToFile(String fileName, Temperature temperature) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            writer.write(temperature.toString());
            writer.flush();
        } catch (IOException e) {
            LOG.error("Failed to write temperature: {}", e.getMessage());
        }
    }

    public static void writeTemperaturesToFile(String fileName, Temperature[] temperatures) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            out.writeInt(temperatures.length); // Write the number of temperatures
            for (Temperature temp : temperatures) {
                out.writeDouble(temp.getKelvin());
            }
            out.flush();
        } catch (IOException e) {
            LOG.error("Failed to write temperatures: {}", e.getMessage());
        }
    }

    public static Temperature[] readTemperaturesFromFile(String fileName) throws IOException {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            int count = in.readInt(); // Read the number of temperatures
            Temperature[] temps = new Temperature[count];

            for (int i = 0; i < count; i++) {
                float value = in.readFloat();
                temps[i] = createFromKelvin(value);
            }
            return temps;
        } catch (IOException e) {
            LOG.error("Failed to read temperatures: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (InvalidTempInputException e) {
            throw new RuntimeException(e);
        }
    }

    public static Temperature readTemperatureFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            return Temperature.parseTemperature(line);
        } catch (IOException e) {
            LOG.error("Failed to read temperature: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (InvalidTempInputException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readTextFile(String fileName) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)))
        {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readIntTextFile(String fileName) throws IOException {
        try(DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            final double value = in.readInt();
            System.out.println(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException, InvalidTempInputException {
        // This class is for test purposes only
        // For reading an int from the mentioned textfile
        String fileName = "demo.txt";

        writeIntTextFile(fileName);

        readIntTextFile(fileName);

        String fileName2 = "text.txt";
        String input2 = "bing bong";
        writeTextFile(fileName2, input2);

        readTextFile(fileName2);


        // Multiple temperatures
        Temperature[] temperatures = {
                createFromKelvin(23.5f),
                createFromKelvin(75.2f),
                createFromKelvin(10.0f)
        };

        writeTemperaturesToFile(fileName, temperatures);

        Temperature[] readTemperatures = readTemperaturesFromFile(fileName);
        for (Temperature t : readTemperatures) {
            System.out.println("Read temperature: " + t);
        }
    }
}
