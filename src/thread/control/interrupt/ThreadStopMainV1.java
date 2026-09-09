package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시");
        task.runFlag = false;
        /*
        07:22:24.790 [     work] 작업 중
        07:22:27.801 [     work] 작업 중
        07:22:28.722 [     main] 작업 중단 지시
        07:22:30.806 [     work] 자원 정리
        07:22:30.806 [     work] 작업 종료
        */
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업 중");
                sleep(3000);
            }
            log("자원 정리");
            log("작업 종료");
        }
    }
}
