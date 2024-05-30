package Week11.Temperature;

import Week10.InvalidTempInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TemperatureHistory implements TemperatureListener {
    private Collection<Temperature> tempHistory;
    /* TO-DO implement max and min as attribute for performance */

    private List<TemperatureListener> listeners = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(TemperatureHistory.class);


    public TemperatureHistory() {
        this.tempHistory = new ArrayList<Temperature>();
    }

    public TemperatureHistory(Temperature temperature) {
        this.tempHistory = new ArrayList<Temperature>();
        this.tempHistory.add(temperature);
    }

    public TemperatureHistory(ArrayList<Temperature> temperature) {
        this.tempHistory = new ArrayList<Temperature>();
        this.tempHistory.addAll(temperature);
    }

    public void setTempHistory(ArrayList<Temperature> tempHistory) {
        this.tempHistory = tempHistory;
    }

    public void add(Temperature temp) {
        try {
            if (temp.getKelvin() > maxTemperature()) {
                tempHistory.add(temp);
                notifyListeners(new TemperatureEvent(temp, TemperatureEventsEnum.NEW_MAX_TEMPERATURE));
            }
            else if (temp.getKelvin() < minTemperature()) {
                tempHistory.add(temp);
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
        if (this.getCount() == 0) {
            throw new EmptyHistoryException("The History is empty!");
        }
        Temperature max = Collections.max(this.tempHistory);

        return max.getKelvin();
    }

    public float minTemperature() throws EmptyHistoryException {
        if (this.getCount() == 0) {
            throw new EmptyHistoryException("The History is empty!");
        }
        Temperature min = Collections.min(this.tempHistory);

        return min.getKelvin();
    }

    public float averageTemperature() throws InvalidTempInputException, EmptyHistoryException {
       Temperature avg = Temperature.createFromKelvin(0);
       if (this.getCount() > 0) {
           this.tempHistory.forEach((t) -> avg.setKelvin(Float.sum(t.getKelvin(), avg.getKelvin())));
       }
       else {
           throw new EmptyHistoryException("The History is empty!");
       }
       return  avg.getKelvin() / this.getCount();
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
}
