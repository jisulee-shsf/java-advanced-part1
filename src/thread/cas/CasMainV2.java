package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        int result1 = compareAndSet(atomicInteger);
        System.out.println("result1 = " + result1);

        int result2 = compareAndSet(atomicInteger);
        System.out.println("result2 = " + result2);
        /*
        start value = 0
        11:53:50.021 [     main] getValue = 0
        11:53:50.034 [     main] result = true
        result1 = 1
        11:53:50.035 [     main] getValue = 1
        11:53:50.036 [     main] result = true
        result2 = 2
        */
    }

    private static int compareAndSet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            log("getValue = " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result = " + result);
        } while (!result);

        return getValue + 1; // atomicInteger.get() X
    }
}
