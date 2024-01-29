package mk.ukim.finki.lab2;

import java.util.Arrays;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class CirclesTest {

    public static class MovableObjectNotFittableException extends Exception {
        public MovableObjectNotFittableException(String message) {
            super(message + " can not be fitted into the collection");
        }
    }

    public static class ObjectCanNotBeMovedException extends Exception {
        public ObjectCanNotBeMovedException(int x, int y) {
            super("Point (" + x + "," + y + ") is out of bounds");
        }
    }

    public static interface Movable {
        void moveUp() throws ObjectCanNotBeMovedException;

        void moveDown() throws ObjectCanNotBeMovedException;

        void moveRight() throws ObjectCanNotBeMovedException;

        void moveLeft() throws ObjectCanNotBeMovedException;

        int getCurrentXPosition();

        int getCurrentYPosition();
    }

    public static class MovablePoint implements Movable {
        private int x;
        private int y;
        private int xSpeed;
        private int ySpeed;

        public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
            this.x = x;
            this.y = y;
            this.xSpeed = xSpeed;
            this.ySpeed = ySpeed;
        }

        public void moveUp() throws ObjectCanNotBeMovedException {
            if (y - ySpeed < 0) {
                throw new ObjectCanNotBeMovedException(x, y - ySpeed);
            }
            y -= ySpeed;
        }

        public void moveDown() throws ObjectCanNotBeMovedException {
            if (y - ySpeed > MovablesCollection.MAX_Y) {
                throw new ObjectCanNotBeMovedException(x, y + ySpeed);
            }
            y += ySpeed;
        }

        public void moveRight() throws ObjectCanNotBeMovedException {
            if (x + xSpeed > MovablesCollection.MAX_X) {
                throw new ObjectCanNotBeMovedException(x + xSpeed, y);
            }
            x += xSpeed;
        }

        public void moveLeft() throws ObjectCanNotBeMovedException {
            if (x - xSpeed < 0) {
                throw new ObjectCanNotBeMovedException(x - xSpeed, y);
            }
            x -= xSpeed;
        }

        public int getCurrentXPosition() {
            return x;
        }

        public int getCurrentYPosition() {
            return y;
        }

        @Override
        public String toString() {
            return "Movable point with coordinates (" + x + "," + y + ')';
        }
    }

    public static class MovableCircle implements Movable {
        private int r;
        private MovablePoint o;

        public MovableCircle(int r, MovablePoint o) {
            this.r = r;
            this.o = o;
        }

        public void moveUp() throws ObjectCanNotBeMovedException {
            if (o.y + o.ySpeed > MovablesCollection.MAX_Y) {
                throw new ObjectCanNotBeMovedException(o.x, o.y + o.ySpeed);
            }
            o.y += o.ySpeed;
        }

        public void moveDown() throws ObjectCanNotBeMovedException {
            if (o.y - o.ySpeed < 0) {
                throw new ObjectCanNotBeMovedException(o.x, o.y - o.ySpeed);
            }
            o.y -= o.ySpeed;
        }

        public void moveRight() throws ObjectCanNotBeMovedException {
            if (o.x + o.xSpeed > MovablesCollection.MAX_X) {
                throw new ObjectCanNotBeMovedException(o.x + o.xSpeed, o.y);
            }
            o.x += o.xSpeed;
        }

        public void moveLeft() throws ObjectCanNotBeMovedException {
            if (o.x - o.xSpeed < 0) {
                throw new ObjectCanNotBeMovedException(o.x - o.xSpeed, o.y);
            }
            o.x -= o.xSpeed;
        }

        public int getCurrentXPosition() {
            return o.getCurrentXPosition();
        }

        public int getCurrentYPosition() {
            return o.getCurrentYPosition();
        }

        public int getR() {
            return r;
        }

        @Override
        public String toString() {
            return "Movable circle with center coordinates (" + o.getCurrentXPosition() + "," + o.getCurrentYPosition() + ") and radius " + r;
        }
    }

    public static class MovablesCollection {
        private Movable[] movable;
        public static int MAX_X;
        public static int MAX_Y;
        public static int curr;

        public MovablesCollection(int x_Max, int y_Max) {
            MAX_X = x_Max;
            MAX_Y = y_Max;
            movable = new Movable[0];
            curr = 0;
        }

        void addMovableObject(Movable m) throws MovableObjectNotFittableException {
            if (m instanceof MovableCircle) {
                StringBuilder str = new StringBuilder();
                str.append(m.toString(), 0, 27).append(m.toString().substring(39));
                if (m.getCurrentXPosition() - ((MovableCircle) m).getR() < 0 || m.getCurrentXPosition() + ((MovableCircle) m).getR() > MAX_X) {
                    throw new MovableObjectNotFittableException(str.toString());
                }
                if (m.getCurrentYPosition() - ((MovableCircle) m).getR() < 0 || m.getCurrentYPosition() + ((MovableCircle) m).getR() > MAX_Y) {
                    throw new MovableObjectNotFittableException(str.toString());
                }
            }
            if (m.getCurrentXPosition() < 0 || m.getCurrentXPosition() > MAX_X || m.getCurrentYPosition() < 0 || m.getCurrentYPosition() > MAX_Y) {
                throw new MovableObjectNotFittableException(m.toString());
            }
            Movable[] newMovable = new Movable[curr + 1];
            for (int i = 0; i < movable.length; i++) {
                newMovable[i] = movable[i];
            }
            newMovable[curr++] = m;
            movable = newMovable;
        }

        void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) {
            if (type == TYPE.POINT) {
                Arrays.stream(movable).forEach(m -> {
                    if (m instanceof MovablePoint) {
                        try {
                            switch (direction) {
                                case UP:
                                    m.moveUp();
                                    break;
                                case DOWN:
                                    m.moveDown();
                                    break;
                                case LEFT:
                                    m.moveLeft();
                                    break;
                                case RIGHT:
                                    m.moveRight();
                                    break;
                            }
                        } catch (ObjectCanNotBeMovedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            } else if (type == TYPE.CIRCLE) {
                Arrays.stream(movable).forEach(m -> {
                    if (m instanceof MovableCircle) {
                        try {
                            switch (direction) {
                                case UP:
                                    m.moveUp();
                                    break;
                                case DOWN:
                                    m.moveDown();
                                    break;
                                case LEFT:
                                    m.moveLeft();
                                    break;
                                case RIGHT:
                                    m.moveRight();
                                    break;
                            }
                        } catch (ObjectCanNotBeMovedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }
        }


        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Collection of movable objects with size ").append(movable.length).append(":").append('\n');
            for (Movable m : movable) {
                stringBuilder.append(m.toString()).append("\n");
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            try {
                if (Integer.parseInt(parts[0]) == 0) {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } else {
                    int radius = Integer.parseInt(parts[5]);
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                }
            } catch (MovableObjectNotFittableException exception) {
                System.out.println(exception.getMessage());
            }
        }
        System.out.println(collection.toString());


        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());


        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.MAX_X = 90;
        MovablesCollection.MAX_Y = 90;


        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());


        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());

    }
}
