package designPaterns.builder;

//параметри кои се повторуваат кај сите објекти - optional
public class Director {

    public void buildBugatti(CarBuilder builder) {
        builder.id(2122)
                .brand("Bugatti")
                .model("Chiron")
                .color("Blue");
    }

    public void buildLambo(CarBuilder builder) {
        builder.id(2122)
                .brand("Lamborghini")
                .model("Aventador")
                .color("Yellow");
    }
}
