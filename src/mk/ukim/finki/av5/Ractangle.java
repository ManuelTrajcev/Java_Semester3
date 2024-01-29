package mk.ukim.finki.av5;

public class Ractangle implements Drawable{
    private static long ID = 1L;

    private long id;

    public Ractangle() {
        id = ID++;
    }

    @Override
    public String toString() {
        return "Ractangle{" +
                "id=" + id +
                '}';
    }

    @Override
    public void draw() {
        System.out.printf("Ractangle: %d\n", id);
    }
}
