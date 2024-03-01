package designPaterns.singleton;

//може да се направи само една инстанца од класата во даден момент

public class Singleton {
    private static volatile Singleton instance;     //volatile - multi thread handling
    private String data;

    public Singleton(String data) {
        this.data = data;
    }

    public static Singleton getInstance(String data) {
        Singleton result = instance;        //земање во локална променлива, пристап до меморија само еднаш
        if (result == null) {      //да не чека синхорнизаација без потреба
            synchronized (Singleton.class) {     //multi threating issue solution, да не се случи во исто време да се креираат повеќе
                if (result == null) {     //ако не постои инстанца креира нова, ако не ја враќа веќе постоечката
                    instance = result = new Singleton(data);
                }
            }
        }
        return result;
    }
}
