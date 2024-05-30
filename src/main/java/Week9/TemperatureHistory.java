package Week9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static java.lang.Float.NaN;

public class TemperatureHistory {
    private Collection<Temperature> tempHistory;

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
        this.tempHistory.add(temp);
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

    public float maxTemperature() {
        if (this.getCount() == 0) {
            return 0f;
        }
        Temperature max = Collections.max(this.tempHistory);

        return max.getKelvin();
    }

    public float minTemperature() {
        if (this.getCount() == 0) {
            return NaN;
        }
        Temperature min = Collections.min(this.tempHistory);

        return min.getKelvin();
    }

    public float averageTemperature(){
       Temperature avg = new Temperature(0f);
       if (this.getCount() > 0) {
           this.tempHistory.forEach((t) -> avg.setKelvin(Float.sum(t.getKelvin(), avg.getKelvin())));
       }
       else {
           return 0f;
       }

       return  avg.getKelvin() / this.getCount();
    }
}
