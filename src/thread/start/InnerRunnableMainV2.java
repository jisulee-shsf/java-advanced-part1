package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV2 {

    public static void main(String[] args) {
        log("main() start");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log("run()");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end");
        /*
        20:12:26.954 [     main] main() start
        20:12:26.964 [     main] main() end
        20:12:26.964 [ Thread-0] run()
        */
    }
}
