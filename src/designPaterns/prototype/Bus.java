package designPaterns.prototype;

public class Bus extends Vehicle {
    private int doors;

    public Bus() {
    }

    public Bus(Bus bus) {
        super(bus);
        this.doors = bus.doors;
    }

    @Override
    public Vehicle clone() {
        return new Bus(this);
    }
}
