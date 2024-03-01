package designPaterns.builder;

public interface Builder {
    Builder id(int id);
    Builder brand(String brand);
    Builder model(String model);
    Builder color(String color);
}
