package Week12;

import java.time.LocalDateTime;

public class MeasuringPoint {
    private LocalDateTime timestamp;
    private float temperature;

    public MeasuringPoint(LocalDateTime timestamp, float temperature) {
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public float getTemperature() {
        return temperature;
    }
}
