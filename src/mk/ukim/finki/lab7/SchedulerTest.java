package mk.ukim.finki.lab7;

import java.util.*;
import java.util.stream.Collectors;

class Scheduler<T> {
    TreeMap<Date, T> events;
    Date now;

    public Scheduler() {
        this.events = new TreeMap<>();
        this.now = new Date();
    }

    public void add(Date date, T event) {
        events.put(date, event);
    }

    public boolean remove(Date d) {
        if (events.containsKey(d)) {
            events.remove(d);
            return true;
        } else {
            return false;
        }
    }

    public T next() {
        return events
                .entrySet()
                .stream()
                .filter(i -> i.getKey().getTime() > now.getTime())
                .findFirst()
                .get()
                .getValue();
    }

    public T last() {
        return events
                .entrySet()
                .stream()
                .filter(i -> i.getKey().getTime() < now.getTime())
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .get();

    }

    public ArrayList<T> getAll(Date begin, Date end) {
        return (ArrayList<T>) events
                .entrySet()
                .stream()
                .filter(i -> i.getKey().getTime() > begin.getTime() && i.getKey().getTime() < end.getTime())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public T getFirst() {
        return events.firstEntry().getValue();
    }

    public T getLast() {
        return events.lastEntry().getValue();
    }

}

public class SchedulerTest {


    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) {
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime() - 7200000), jin.next());
            scheduler.add(new Date(now.getTime() - 3600000), jin.next());
            scheduler.add(new Date(now.getTime() - 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 7200000), jin.next());
            scheduler.add(new Date(now.getTime() + 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 3600000), jin.next());
            scheduler.add(new Date(now.getTime() + 18000000), jin.next());
            System.out.println(scheduler.getFirst());
            System.out.println(scheduler.getLast());
        }
        if (k == 3) { //test Scheduler with String
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime() - 7200000), jin.next());
            scheduler.add(new Date(now.getTime() - 3600000), jin.next());
            scheduler.add(new Date(now.getTime() - 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 7200000), jin.next());
            scheduler.add(new Date(now.getTime() + 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 3600000), jin.next());
            scheduler.add(new Date(now.getTime() + 18000000), jin.next());
            System.out.println(scheduler.next());
            System.out.println(scheduler.last());
            ArrayList<String> res = scheduler.getAll(new Date(now.getTime() - 10000000), new Date(now.getTime() + 17000000));
            Collections.sort(res);
            for (String t : res) {
                System.out.print(t + " , ");
            }
        }
        if (k == 4) {//test Scheduler with ints complex
            Scheduler<Integer> scheduler = new Scheduler<Integer>();
            int counter = 0;
            ArrayList<Date> to_remove = new ArrayList<Date>();

            while (jin.hasNextLong()) {
                Date d = new Date(jin.nextLong());
                int i = jin.nextInt();
                if ((counter & 7) == 0) {
                    to_remove.add(d);
                }
                scheduler.add(d, i);
                ++counter;
            }
            jin.next();

            while (jin.hasNextLong()) {
                Date l = new Date(jin.nextLong());
                Date h = new Date(jin.nextLong());
                ArrayList<Integer> res = scheduler.getAll(l, h);
                Collections.sort(res);

                String ls = l.toString().replace("UTC", "GMT");
                String hs = h.toString().replace("UTC", "GMT");
                System.out.println(ls + " <: " + print(res) + " >: " + hs);
            }

            System.out.println("test");
            ArrayList<Integer> res = scheduler.getAll(new Date(0), new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
            for (Date d : to_remove) {
                scheduler.remove(d);
            }
            res = scheduler.getAll(new Date(0), new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
        }
    }

    private static <T> String print(ArrayList<T> res) {
        if (res == null || res.size() == 0) return "NONE";
        StringBuffer sb = new StringBuffer();
        for (T t : res) {
            sb.append(t + " , ");
        }
        return sb.substring(0, sb.length() - 3);
    }


}