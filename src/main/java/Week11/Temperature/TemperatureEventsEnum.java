package Week11.Temperature;

public enum TemperatureEventsEnum {
    TEMPERATURE_SET("Set Temperature"),
    NEW_MAX_TEMPERATURE("New max temperature"),
    NEW_MIN_TEMPERATURE("New min temperature");

    private final String eventDescription;

    private TemperatureEventsEnum(String value){
        eventDescription = value;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
