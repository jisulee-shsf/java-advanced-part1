package thread.start;

import static util.MyLogger.log;

public class ManyThreadMainV2 {

    public static void main(String[] args) {
        log("main() start");

        HelloRunnable runnable = new HelloRunnable();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

        log("main() end");
        /*
        20:01:09.540 [     main] main() start
        Thread-0 = run()
        Thread-2 = run()
        Thread-3 = run()
        Thread-7 = run()
        ...
        20:01:09.572 [     main] main() end
        Thread-99 = run()
        */
    }
}
