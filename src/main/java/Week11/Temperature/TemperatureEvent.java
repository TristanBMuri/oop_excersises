package Week11.Temperature;


import java.util.EventObject;

public class TemperatureEvent extends EventObject {
    private final Temperature source;
    private final TemperatureEventsEnum eventType;

    public TemperatureEvent(Temperature source, TemperatureEventsEnum eventType) {
        super(source);
        this.source = source;
        this.eventType = eventType;
    }

    public Temperature getSource() {
        return source;
    }

    public TemperatureEventsEnum getEventType() {
        return eventType;
    }

    @Override
    public String toString(){
        return eventType.getEventDescription() + source.toString();
    }
}
