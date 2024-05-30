package Week12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TemperatureFileReader {
    public static void main(String[] args) {
        // Don't use .txt for files which aren't supposed to be read use .bin instead !!!!!!!!

        String filePath =
                "C:\\Users\\trist\\Documents\\big_coding\\oop\\oop_excersises\\src\\main\\java\\Week12\\netatmo-export-202301-202304.csv";
        File file = new File(filePath);
        if (file.exists()) {
            try {
                readAndProcessFile(filePath);
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        } else {
            System.err.println("File does not exist: " + filePath);
        }
        displayStatistics();
    }

    private static List<MeasuringPoint> measurements = new ArrayList<>();

    private static void readAndProcessFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        }
    }

    private static void processLine(String line) {
        String[] parts = line.split(";");
        if (parts.length >= 4) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime timestamp = LocalDateTime.parse(parts[1].replace("\"", ""), formatter);

                double temperature = Double.parseDouble(parts[2].replace("\"", ""));
                int humidity = Integer.parseInt(parts[3].replace("\"", ""));
                System.out.printf("Timestamp: %s, Temperature: %.2f, Humidity: %d%%%n", timestamp, temperature, humidity);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing numbers from line: " + line);
            }
        } else {
            System.err.println("Invalid line format: " + line);
        }
    }

    public static void displayStatistics() {
        if (measurements.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        MeasuringPoint maxTemp = measurements.stream().max(Comparator.comparing(MeasuringPoint::getTemperature)).orElse(null);
        MeasuringPoint minTemp = measurements.stream().min(Comparator.comparing(MeasuringPoint::getTemperature)).orElse(null);
        double averageTemp = measurements.stream().mapToDouble(MeasuringPoint::getTemperature).average().orElse(Double.NaN);

        System.out.printf("Highest Temperature: %.2f°C at %s%n", maxTemp.getTemperature(), maxTemp.getTimestamp());
        assert minTemp != null;
        System.out.printf("Lowest Temperature: %.2f°C at %s%n", minTemp.getTemperature(), minTemp.getTimestamp());
        System.out.printf("Average Temperature: %.2f°C%n", averageTemp);
    }
}
