package designPaterns.prototype;

public abstract class Vehicle {
    private  int id;
    private  String  brand;
    private  String  model;
    private  String  color;

    public Vehicle() {
    }

    public Vehicle(int id, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Vehicle(Vehicle vehicle) {
        this.id = vehicle.id;
        this.brand = vehicle.brand;
        this.model = vehicle.model;
        this.color = vehicle.color;
    }

    public abstract Vehicle clone();

}
