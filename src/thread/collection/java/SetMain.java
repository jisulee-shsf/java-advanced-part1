package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {

    public static void main(String[] args) {
        Set<Integer> set1 = new CopyOnWriteArraySet<>();
        set1.add(2);
        set1.add(1);
        set1.add(3);
        System.out.println(set1);

        Set<Object> set2 = new ConcurrentSkipListSet<>();
        set2.add(2);
        set2.add(1);
        set2.add(3);
        System.out.println(set2);
    }
}
