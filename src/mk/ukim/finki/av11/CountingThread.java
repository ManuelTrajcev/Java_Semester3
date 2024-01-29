package mk.ukim.finki.av11;

import java.util.ArrayList;
import java.util.List;

class Printer extends Thread {
    int number;

    public Printer(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(number);
    }
}

public class CountingThread {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            int number = i;
            threads.add(new Thread(() -> System.out.println(number)));
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
