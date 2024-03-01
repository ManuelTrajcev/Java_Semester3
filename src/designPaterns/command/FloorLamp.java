package designPaterns.command;

public class FloorLamp {
    private Light light;

    public FloorLamp() {
        this.light = new Light();
    }
    public void switchLight() {
        light.switchLights();
    }
}
