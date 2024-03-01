package designPaterns.command;

public class Kitchen extends Room{
    private Oven oven;

    public Kitchen(Oven oven) {
        this.oven = oven;
    }
}
