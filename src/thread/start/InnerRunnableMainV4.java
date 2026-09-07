package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV4 {

    public static void main(String[] args) {
        log("main() start");

        Thread thread = new Thread(() -> log("run()"));
        thread.start();

        log("main() end");
        /*
        20:15:45.334 [     main] main() start
        20:15:45.343 [     main] main() end
        20:15:45.343 [ Thread-0] run()
        */
    }
}
