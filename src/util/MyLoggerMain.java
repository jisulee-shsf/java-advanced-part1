package util;

import static util.MyLogger.log;

public class MyLoggerMain {

    public static void main(String[] args) {
        log("logger");
        // 19:49:35.111 [     main] logger
        log(123);
        // 19:49:35.120 [     main] 123
    }
}
