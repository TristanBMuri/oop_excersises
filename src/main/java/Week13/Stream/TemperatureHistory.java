package Week13.Stream;

import Week10.InvalidTempInputException;
import Week11.Temperature.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TemperatureHistory implements TemperatureListener {
    private Collection<Temperature> tempHistory;
    /* TO-DO implement max and min as attribute for performance */
    private Temperature maxTemperature;
    private Temperature minTemperature;

    private List<TemperatureListener> listeners = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(TemperatureHistory.class);


    public TemperatureHistory() {
        this.tempHistory = new ArrayList<Temperature>();
    }

    public TemperatureHistory(Temperature temperature) {
        this.tempHistory = new ArrayList<Temperature>();
        this.tempHistory.add(temperature);
        this.maxTemperature = temperature;
        this.minTemperature = temperature;
    }

    public TemperatureHistory(ArrayList<Temperature> temperatures) {
        this.tempHistory = new ArrayList<Temperature>();
        this.tempHistory.addAll(temperatures);
        this.maxTemperature = temperatures
                .stream().max(Temperature::compareTo).orElse(temperatures.get(0));
        this.minTemperature = temperatures
                .stream().min(Temperature::compareTo).orElse(temperatures.get(0));
    }

    public void setTempHistory(ArrayList<Temperature> tempHistory) {
        this.tempHistory = tempHistory;
    }

    public void add(Temperature temp) {
        try {
            if (temp.getKelvin() > maxTemperature()) {
                tempHistory.add(temp);
                this.maxTemperature = temp;
                notifyListeners(new TemperatureEvent(temp, TemperatureEventsEnum.NEW_MAX_TEMPERATURE));
            }
            else if (temp.getKelvin() < minTemperature()) {
                tempHistory.add(temp);
                this.minTemperature = temp;
                notifyListeners(new TemperatureEvent(temp, TemperatureEventsEnum.NEW_MIN_TEMPERATURE));
            }
        } catch (EmptyHistoryException emptyHistoryException){
            LOG.info("First Element in History");
            tempHistory.add(temp);
            notifyListeners(new TemperatureEvent(temp, TemperatureEventsEnum.NEW_MAX_TEMPERATURE));
            notifyListeners(new TemperatureEvent(temp, TemperatureEventsEnum.NEW_MIN_TEMPERATURE));
        }
    }

    public Collection<Temperature> getTempHistory() {
        return tempHistory;
    }

    public int getCount() {
        return tempHistory.size();
    }

    public void clear() {
        this.tempHistory = new ArrayList<Temperature>();
    }

    public float maxTemperature() throws EmptyHistoryException {
        if (this.maxTemperature == null) {
            throw new EmptyHistoryException("The History is empty!");
        }

        return maxTemperature.getKelvin();
    }

    public float minTemperature() throws EmptyHistoryException {
        if (this.getCount() == 0) {
            throw new EmptyHistoryException("The History is empty!");
        }

        return minTemperature.getKelvin();
    }

    public float averageTemperature() throws InvalidTempInputException, EmptyHistoryException {
        float avg;
        if (this.getCount() > 0) {
            avg = (float) this.tempHistory
                    .stream().mapToDouble(temp -> temp.getKelvin()).average().getAsDouble();
        } else {
            throw new EmptyHistoryException("The History is empty!");
        }
        return avg;
    }

    @Override
    public String toString() {
        float max = 0;
        float min = 0;
        float avg = 0;
        float count = 0;
        try {
            max = maxTemperature();
            min = minTemperature();
            avg = averageTemperature();
            count = getCount();
        } catch (InvalidTempInputException e) {
            throw new RuntimeException(e);
        } catch (EmptyHistoryException e){
            LOG.error("toString called on empty history");
        }
        String text = new StringBuilder().append("Temperature history: \n")
                .append("Min value: " + min + "\n")
                .append("Max value: " + max + "\n")
                .append("Count of temperatures: " + count + "\n")
                .append("Average: " + avg).toString();
        return text;
    }

    public void addTemperatureListener(TemperatureListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void removeTemperatureListener(TemperatureListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(TemperatureEvent event) {
        for (TemperatureListener listener : listeners) {
            listener.onTemperatureEvent(event);
        }
    }

    @Override
    public void onTemperatureEvent(TemperatureEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) throws InvalidTempInputException, EmptyHistoryException {
        TemperatureHistory history = new TemperatureHistory();

        Temperature t1 = Temperature.createFromKelvin(10);
        Temperature t2 = Temperature.createFromKelvin(20);
        Temperature t3 = Temperature.createFromKelvin(30);
        Temperature t4 = Temperature.createFromKelvin(40);

        history.add(t1);
        history.add(t2);
        history.add(t3);
        history.add(t4);

        history.getTempHistory()
                .stream()
                .map(Temperature::getKelvin)
                .filter((i) -> i < 50 && i > 10)
                .forEach(System.out::println);

        System.out.println(history.averageTemperature());
    }
}
