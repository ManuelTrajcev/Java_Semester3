package designPaterns.prototype;

import java.util.HashMap;
import java.util.Map;

public class VehicleCache {     //Registry
    private Map<String,Vehicle> cache = new HashMap<>();

    public VehicleCache() {
        Car car = new Car();
        Bus bus = new Bus();

        cache.put("Bugatti Ciron", car);
        cache.put("Mercedes Setra", bus);
    }
}
