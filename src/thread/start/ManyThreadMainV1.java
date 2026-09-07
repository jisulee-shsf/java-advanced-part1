package thread.start;

import static util.MyLogger.log;

public class ManyThreadMainV1 {

    public static void main(String[] args) {
        log("main() start");

        HelloRunnable runnable = new HelloRunnable();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        Thread thread3 = new Thread(runnable);
        thread3.start();

        log("main() end");
        /*
        19:59:01.779 [     main] main() start
        19:59:01.788 [     main] main() end
        Thread-1 = run()
        Thread-0 = run()
        Thread-2 = run()
        */
    }
}
