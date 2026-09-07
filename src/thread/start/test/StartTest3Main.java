package thread.start.test;

import static util.MyLogger.log;

public class StartTest3Main {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
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
        };

        Thread thread = new Thread(runnable, "counter");
        thread.start();
        /*
        20:42:36.110 [  counter] value = 1
        20:42:36.117 [  counter] value = 2
        20:42:36.117 [  counter] value = 3
        20:42:36.117 [  counter] value = 4
        20:42:36.118 [  counter] value = 5
        */
    }
}
