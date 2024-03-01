package designPaterns.builder;

//
public class CarSchemaBuilder implements Builder{
    private int id;
    private String  brand;
    private String  model;
    private String  color;

    public CarSchemaBuilder id(int id) {
        this.id = id;
        return this;
    }

    public CarSchemaBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarSchemaBuilder model(String model) {
        this.model = model;
        return this;
    }

    public CarSchemaBuilder color(String color) {
        this.brand = color;
        return this;
    }

    public CarSchema build() {
        return new CarSchema(id,brand,model,color);
    }
}
