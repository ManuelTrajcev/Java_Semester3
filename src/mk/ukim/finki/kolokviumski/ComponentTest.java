package mk.ukim.finki.kolokviumski;

import java.util.*;


class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}

class Component {
    String color;
    int weight;
    List<Component> innerComponents;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        this.innerComponents = new ArrayList<>();
    }

    void addComponent(Component component) {
        innerComponents.add(component);
        Collections.sort(innerComponents, Comparator.comparing(Component::getWeight).thenComparing(Component::getColor));
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(weight).append(":").append(color).append("\n");

        for (Component c : innerComponents) {
            stringBuilder.append(c.toString().replaceAll("(?m)^", "---")).append("\n");
        }

        return stringBuilder.toString().trim();
    }

}

class Window {
    String name;
    Component[] components;

    public Window(String name) {
        this.name = name;
        this.components = new Component[0];
    }

    void addComponent(int position, Component component) throws InvalidPositionException {
        if (components.length <= position) {
            Component[] tmp = new Component[position + 1];
            for (int i = 0; i < components.length; i++) {
                tmp[i] = components[i];
            }
            tmp[position] = component;
            components = tmp;
        } else {
            if (components[position] != null) {
                throw new InvalidPositionException(String.format("Invalid position %d, alredy taken!", position));
            }
            components[position] = component;
        }
    }

    void changeColor(int weight, String color) {
        for (Component component : components) {
            changeColorRecursive(component, weight, color);
        }
    }

    private void changeColorRecursive(Component component, int weight, String color) {
        if (component != null && component.getWeight() < weight) {
            component.setColor(color);
            for (Component innerComponent : component.innerComponents) {
                changeColorRecursive(innerComponent, weight, color);
            }
        }
    }

    void swichComponents(int pos1, int pos2) {
        switchComponentsRecursive(components, pos1, pos2);
    }

    private void switchComponentsRecursive(Component[] components, int pos1, int pos2) {
        if (components != null) {
            if (pos1 >= 0 && pos1 < components.length && pos2 >= 0 && pos2 < components.length) {
                Component tmp = components[pos1];
                components[pos1] = components[pos2];
                components[pos2] = tmp;

                for (Component component : components) {
                    switchComponentsRecursive(component != null ? component.innerComponents.toArray(new Component[0]) : null, pos1, pos2);
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WINDOW ").append(name).append("\n");
        for (int i = 1; i < components.length; i++) {
            stringBuilder.append(i).append(":");
            stringBuilder.append(components[i]).append("\n");
        }
        return stringBuilder.toString();
    }
}

public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if (what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }
}

// вашиот код овде