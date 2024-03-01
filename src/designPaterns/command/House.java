package designPaterns.command;

import java.util.ArrayList;
import java.util.List;

public class House {
    List<Room> rooms;
    public House() {
        rooms = new ArrayList<>();
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public static void main(String[] args) {
        House house = new House();
        house.addRoom(new LivingRoom(new Windows()));
        house.addRoom(new Bathroom("hot water"));
        house.addRoom(new Kitchen(new Oven()));
        house.addRoom(new Bedroom("Rock"));
        house.addRoom(new Bedroom("Pop"));
        house.rooms.forEach(r -> r.setCommand(new SwitchedLightsCommand(new Light())));
        
    }
}
