package designPaterns.command;

public class SwitchedLightsCommand implements Command{
    private Light light;

    public SwitchedLightsCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.switchLights();
    }
}
