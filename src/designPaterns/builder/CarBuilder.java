package designPaterns.builder;

//креирање на сложени објекти step-by-step
//посебни објекти наречени builders, поделба на сложен конструктор
//buil method - креирање објект
public class CarBuilder implements Builder {
    private int id;
    private String  brand;
    private String  model;
    private String  color;


    public Car build() {
        return new Car(id,brand,model,color);
    }

    @Override
    public Builder id(int id) {
        return null;
    }

    @Override
    public Builder brand(String brand) {
        return null;
    }

    @Override
    public Builder model(String model) {
        return null;
    }

    @Override
    public Builder color(String color) {
        return null;
    }
}
