package designPaterns.factory;

public class BeefBurger implements Burger {
    public BeefBurger() {
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Beef Burger");
    }
}
