package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag 확인 = " + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag 변경 시도");
        task.runFlag = false;
        log("runFlag 확인 = " + task.runFlag);
        log("main 종료");
        /*
        15:11:32.053 [     main] runFlag 확인 = true
        15:11:32.061 [     work] task 시작
        15:11:33.065 [     main] runFlag 변경 시도
        15:11:33.066 [     main] runFlag 확인 = false
        15:11:33.066 [     main] main 종료
        */
    }

    static class MyTask implements Runnable {

        boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
            }
            log("task 종료");
        }
    }
}
