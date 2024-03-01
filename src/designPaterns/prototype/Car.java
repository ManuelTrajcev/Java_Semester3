package designPaterns.prototype;

import designPaterns.builder.CarBuilder;

public class Car extends Vehicle {
    private  int topSpeed;
    private  GpsSystem gpsSystem;

    public Car() {
    }

    public Car(Car car) {       //copy constructor
        super(car);
        this.topSpeed = car.topSpeed;
//        this.gpsSystem = car.gpsSystem;   //shallow copy
        this.gpsSystem = new GpsSystem();   // deep copy
    }


    public static void main(String[] args) {
        CarBuilder builder = new CarBuilder();
        builder.id(2122)
                .brand("Bugatti")
                .model("Chiron")
                .color("Blue");
        designPaterns.builder.Car car = builder.build();
    }

    @Override
    public Car clone() {
        return new Car(this);
    }
}
