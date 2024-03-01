package designPaterns.builder;

public class Car {
    private final int id;
    private final String  brand;
    private final String  model;
    private final String  color;

    public Car(int id, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public static void main(String[] args) {
        CarBuilder builder = new CarBuilder();
        builder.id(2122)
                .brand("Bugatti")
                .model("Chiron")
                .color("Blue");
        Car car = builder.build();
    }
}
