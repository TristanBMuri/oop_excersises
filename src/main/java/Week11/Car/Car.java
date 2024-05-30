package Week11.Car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Car implements PropertyChangeListener, Switchable{
    private final String model;
    private boolean running;
    private Motor motor;
    private Light lightFrontLeft;
    private Light lightFrontRight;
    private final List<PropertyChangeListener> changeListeners = new ArrayList<PropertyChangeListener>();
    private static final Logger LOG = LoggerFactory.getLogger(Car.class);


    private class MotorPropertyListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt){
            if ("running".equals(evt.getPropertyName())){
                LOG.info("Motor state changed from " + evt.getOldValue() + " to " + evt.getNewValue());
            }
            handleMotorEvent("Event vom Motor", evt);
        }
    }

    public Car(String model) {
        // Initializing Components
        this.model = model;
        this.running = false;
        this.motor = new Motor();
        this.lightFrontRight = new Light();
        this.lightFrontLeft = new Light();

        this.addPropertyChangeListener(new MotorPropertyListener());
    }

    public String getModel() {
        return model;
    }

    public boolean isRunning() {
        return running;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) throws StillRunningException {
        motor.switchOff();
        if (this.isSwitchedOn()){
            LOG.error("Tried changing motor while Car was still running");
            throw new StillRunningException("Turn Car off first!");
        }

        this.motor = motor;
    }

    public Light getLightFrontLeft() {
        return lightFrontLeft;
    }

    public Light getLightFrontRight() {
        return lightFrontRight;
    }

    @Override
    final public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car car = (Car) obj;

        return car.motor == this.motor && Objects.equals(car.model, this.model);
    }

    @Override
    public String toString() {
        return "Car model: " + this.model + this.motor.toString();
    }

    @Override
    final public int hashCode() {
        return Objects.hash(this.model + this.motor);
    }


    final public int compareTo(Car otherCar) {
        return Integer.compare(this.motor.getRpm(), otherCar.motor.getRpm());
    }


    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.changeListeners.add(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        this.changeListeners.remove(listener);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if(evt.getSource() == this.motor){
            this.handleMotorEvent("Motor", evt);
        }
        if(evt.getSource() == this.lightFrontLeft){
            this.handleLightEvent("Scheinwerfer Links", evt);
        }
        if(evt.getSource() == this.lightFrontRight){
            this.handleLightEvent("Scheinwerfer Rechts", evt);
        }
    }

    private void handleLightEvent(String scheinwerfer, PropertyChangeEvent evt) {
        for (PropertyChangeListener listener : changeListeners) {
            LOG.info("{}{}", scheinwerfer, evt);
            listener.propertyChange(evt);
        }
    }

    private void handleMotorEvent(String motor, PropertyChangeEvent evt) {
        for (PropertyChangeListener listener : changeListeners) {
            LOG.info("{}{}", motor, evt);
            listener.propertyChange(evt);
        }
    }


    @Override
    public void switchOn() {
        if (!running) {
            boolean oldRunning = false;
            this.running = true;

            propertyChange(new PropertyChangeEvent(this, "Motor", oldRunning, running));

            if (this.lightFrontLeft.isSwitchedOff() || this.lightFrontRight.isSwitchedOff()){
                lightFrontRight.switchOn();
                lightFrontLeft.switchOn();
            }
        }
    }

    @Override
    public void switchOff() {
        if (running) {
            boolean oldRunning = true;
            this.running = false;

            propertyChange(new PropertyChangeEvent(this, "Motor", oldRunning, running));

            if (this.lightFrontLeft.isSwitchedOn() || this.lightFrontRight.isSwitchedOn()){
                lightFrontRight.switchOff();
                lightFrontLeft.switchOff();
            }
        }
    }

    @Override
    public boolean isSwitchedOn() {
        return this.running;
    }

    @Override
    public boolean isSwitchedOff() {
        return !this.running;
    }
}
