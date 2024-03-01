package designPaterns.factory;

public class VeggieBurger implements Burger{
    public VeggieBurger() {
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Veggie Burger");
    }
}
