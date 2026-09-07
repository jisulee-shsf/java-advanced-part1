package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV3 {

    public static void main(String[] args) {
        log("main() start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log("run()");
            }
        });
        thread.start();

        log("main() end");
        /*
        20:14:16.002 [     main] main() start
        20:14:16.012 [     main] main() end
        20:14:16.012 [ Thread-0] run()
        */
    }
}
