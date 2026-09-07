package thread.start.test;

import static util.MyLogger.log;

public class StartTest1Main {

    public static void main(String[] args) {
        CounterThread thread = new CounterThread();
        thread.start();
        /*
        20:36:41.676 [ Thread-0] value = 1
        20:36:42.679 [ Thread-0] value = 2
        20:36:43.684 [ Thread-0] value = 3
        20:36:44.687 [ Thread-0] value = 4
        20:36:45.691 [ Thread-0] value = 5
        */
    }

    static class CounterThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                log("value = " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
