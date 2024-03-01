package designPaterns.builder;

public class CarSchema {
    private final int id;
    private final String brand;
    private final String model;
    private final String color;

    public CarSchema(int id, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }
}
