package Week13.Lambdas.Car;

import java.beans.PropertyChangeEvent;

public class Light implements Switchable, PropertyChangeListener {
    private int lumen;
    private boolean switchedOn;

    public Light(){
        this.lumen = 100;
        this.switchedOn = false;
    }

    public Light(int lumen){
        this.lumen = lumen;
        this.switchedOn = false;
    }

    @Override
    public void switchOn() {
        switchedOn = true;
    }

    @Override
    public void switchOff() {
        switchedOn = false;
    }

    @Override
    public boolean isSwitchedOn() {
        return switchedOn;
    }

    @Override
    public boolean isSwitchedOff() {
        return !switchedOn;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
