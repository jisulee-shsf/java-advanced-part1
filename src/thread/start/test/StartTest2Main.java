package thread.start.test;

import static util.MyLogger.log;

public class StartTest2Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new CounterRunnable(), "counter");
        thread.start();
        /*
        20:37:28.415 [  counter] value = 1
        20:37:28.416 [  counter] value = 2
        20:37:28.416 [  counter] value = 3
        20:37:28.417 [  counter] value = 4
        20:37:28.417 [  counter] value = 5
        */
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                log("value = " + i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
