package Week11.Temperature;


public class TemperatureAlertListener implements TemperatureListener {
    @Override
    public void onTemperatureEvent(TemperatureEvent event) {
        switch (event.getEventType()) {
            case NEW_MAX_TEMPERATURE:
                System.out.println("New maximum temperature recorded: " + event.getSource().getKelvin() + " Kelvin");
                break;
            case NEW_MIN_TEMPERATURE:
                System.out.println("New minimum temperature recorded: " + event.getSource().getKelvin() + " Kelvin");
                break;
        }
    }
}
