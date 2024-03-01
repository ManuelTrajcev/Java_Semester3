package designPaterns.command;

public class Light {
    private boolean switchedOn;

    public Light() {
        this.switchedOn = false;
    }

    public void setSwitchedOn(boolean switchedOn) {
        this.switchedOn = switchedOn;
    }

    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public void switchLights() {
        switchedOn = !switchedOn;
    }
}
