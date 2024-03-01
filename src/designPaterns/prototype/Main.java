package designPaterns.prototype;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public void clone(List<Vehicle> vehicles) {
        List<Vehicle> copyList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            copyList.add(vehicle.clone());
        }
    }

    public static void main(String[] args) {

    }
}
