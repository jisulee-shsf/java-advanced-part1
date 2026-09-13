package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CasMainV3 {
    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                compareAndSet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() + " result = " + result);
        /*
        start value = 0
        12:57:09.531 [ Thread-0] getValue = 0
        12:57:09.531 [ Thread-1] getValue = 0
        12:57:09.539 [ Thread-0] result = true
        12:57:09.539 [ Thread-1] result = false
        12:57:10.544 [ Thread-1] getValue = 1
        12:57:10.544 [ Thread-1] result = true
        AtomicInteger result = 2
        */
    }

    private static int compareAndSet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            sleep(1000); // 스레드 동시 실행을 위한 대기
            log("getValue = " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result = " + result);
        } while (!result);

        return getValue + 1;
    }
}
