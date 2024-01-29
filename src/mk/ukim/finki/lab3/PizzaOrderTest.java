package mk.ukim.finki.lab3;

import java.util.*;


class InvalidPizzaTypeException extends Exception {
    public InvalidPizzaTypeException(String message) {
        super(message);
    }
}

class InvalidExtraTypeException extends Exception {
    public InvalidExtraTypeException(String message) {
        super(message);
    }
}

class EmptyOrder extends Exception {
    public EmptyOrder(String message) {
        super(message);
    }
}

class OrderLockedException extends Exception {
    public OrderLockedException(String message) {
        super(message);
    }
}

class ItemOutOfStockException extends Exception {
    public ItemOutOfStockException(String message) {
        super(message);
    }
}

class ArrayIndexOutOfBоundsException extends Exception {
    public ArrayIndexOutOfBоundsException(String message) {
        super(message);
    }
}

interface Item extends Comparable<Item> {
    int getPrice();

    String getName();

    int getType();

    int getOrder();

    @Override
    default int compareTo(Item other) {
        return this.getName().compareTo(other.getName());
    }
}

class ExtraItem implements Item {
    int price;
    String name;

    public ExtraItem(String name) throws InvalidExtraTypeException {
        if (!name.equals("Coke") && !name.equals("Ketchup")) {
            throw new InvalidExtraTypeException("InvalidPizzaTypeException");
        } else {
            this.name = name;
            if (name.equals("Coke")) {
                this.price = 5;
            } else if (name.equals("Ketchup")) {
                this.price = 3;
            }
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public int getOrder() {
        if (this.name.equals("Coke")) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraItem extraItem = (ExtraItem) o;
        return price == extraItem.price && Objects.equals(name, extraItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }
}

class PizzaItem implements Item {
    int price;
    String name;

    public PizzaItem(String name) throws InvalidPizzaTypeException {
        if (!name.equals("Standard") && !name.equals("Pepperoni") && !name.equals("Vegetarian")) {
            throw new InvalidPizzaTypeException("InvalidPizzaTypeException");
        } else {
            this.name = name;
            if (name.equals("Standard")) {
                this.price = 10;
            } else if (name.equals("Pepperoni")) {
                this.price = 12;
            } else if (name.equals("Vegetarian")) {
                this.price = 8;
            }
        }
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getType() {
        return 1;
    }

    public int getOrder() {
        if (this.name.equals("Standard")) {
            return 1;
        } else if (this.name.equals("Vegetarian")) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaItem pizzaItem = (PizzaItem) o;
        return price == pizzaItem.price && Objects.equals(name, pizzaItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }
}

class Order {
    TreeMap<Item, Integer> items;
    List<Item> list;
    boolean isLocked;


    public Order() {
        this.items = new TreeMap<>();
        this.list = new ArrayList<>();
        this.isLocked = false;
    }

    public int getPrice() {
        int sum = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public void displayOrder() {
        list.sort(Comparator.comparingInt(Item::getType).thenComparing(Item::getOrder));
        Map<Item, Integer> sorted = new LinkedHashMap<>();
        for (Item i : list) {
            sorted.put(i, items.get(i));
        }

        Iterator<Map.Entry<Item, Integer>> itr = sorted.entrySet().iterator();
        int counter = 1;
        int total = 0;
        while (itr.hasNext()) {
            Map.Entry<Item, Integer> entry = itr.next();
            System.out.println(String.format("%3d.%-15sx%2s%5d$", counter++, entry.getKey().getName(), entry.getValue(), entry.getKey().getPrice() * entry.getValue()));
            total += entry.getValue() * entry.getKey().getPrice();
        }
        System.out.printf("Total:%21d$\n", total);
    }

    public void addItem(Item item, int i) throws OrderLockedException, ItemOutOfStockException {
        if (isLocked) {
            throw new OrderLockedException("OrderLockedException");
        }
        if (i > 10) {
            throw new ItemOutOfStockException(item.getName());
        }
        items.remove(item);
        list.remove(item);
        list.add(item);
        items.put(item, i);
    }

    public void removeItem(int i) throws OrderLockedException, ArrayIndexOutOfBоundsException {
        if (isLocked) {
            throw new OrderLockedException("OrderLockedException");
        }
        if (i > items.size()) {
            throw new ArrayIndexOutOfBоundsException(Integer.toString(i));
        }
        items.remove(list.get(i));
        list.remove(i);
    }

    public void lock() throws EmptyOrder {
        if (items.isEmpty()) {
            throw new EmptyOrder("EmptyOrder");
        }
        isLocked = true;
    }
}

public class PizzaOrderTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }

}