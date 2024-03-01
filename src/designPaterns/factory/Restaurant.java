package designPaterns.factory;

public abstract class Restaurant {
    public Burger orderBurger() {
        Burger burger = createBurger();
        burger.prepare();
        return burger;
    }

    public abstract Burger createBurger();

    public static void main(String[] args) {
        Restaurant beefRestaurant = new BeefBurgerRestaurant();
        Burger beefBurger = beefRestaurant.createBurger();

        Restaurant veggieRestaurant = new VeggieBurgerRestaurant();
        Burger veggieBurger = veggieRestaurant.orderBurger();
    }
}
