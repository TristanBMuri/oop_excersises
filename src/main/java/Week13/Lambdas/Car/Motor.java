package Week13.Lambdas.Car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class Motor implements Switchable {
    private int rpm;
    private MotorState motorState;
    private final List<PropertyChangeListener> changeListeners = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(Motor.class);


    public Motor(){
        this.rpm = 0;
        this.motorState  = MotorState.OFF;
    }

    public int getRpm() {
        return rpm;
    }


    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        // Throws error if null in notify/ fire method
        if (listener != null) {
            this.changeListeners.add(listener);
        }
    }

    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.changeListeners.remove(listener);
    }

    private void firePropertyChangeEvent(final PropertyChangeEvent event) {
        for (final PropertyChangeListener listener : this.changeListeners) {
            listener.propertyChange(event);
        }
    }

    @Override
    public void switchOn() {
        if (isSwitchedOff()) {
            this.motorState = MotorState.ON;
            this.rpm = 1000;
            final PropertyChangeEvent stateEvent = new PropertyChangeEvent(
                    this, "motorState", MotorState.OFF, MotorState.ON);
            final PropertyChangeEvent rpmEvent = new PropertyChangeEvent(
                    this, "rpm", 0, this.rpm);

            firePropertyChangeEvent(stateEvent);
            firePropertyChangeEvent(rpmEvent);
        }
    }

    @Override
    public void switchOff() {
        if (isSwitchedOn()) {
            this.motorState = MotorState.OFF;
            int oldRpm = this.rpm;
            this.rpm = 0;

            final PropertyChangeEvent stateEvent = new PropertyChangeEvent(
                    this, "motorState", MotorState.ON, MotorState.OFF);
            final PropertyChangeEvent rpmEvent = new PropertyChangeEvent(
                    this, "rpm", oldRpm, this.rpm);

            firePropertyChangeEvent(stateEvent);
            firePropertyChangeEvent(rpmEvent);
        }
    }

    @Override
    public boolean isSwitchedOn() {
        return this.motorState == MotorState.ON;
    }

    @Override
    public boolean isSwitchedOff() {
        return !(this.motorState == MotorState.ON);
    }
}
