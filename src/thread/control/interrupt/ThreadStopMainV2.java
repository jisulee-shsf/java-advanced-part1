package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
        /*
        07:56:58.358 [     work] 작업 중
        07:57:01.369 [     work] 작업 중
        07:57:02.303 [     main] 작업 중단 지시
        07:57:02.310 [     main] work 스레드 인터럽트 상태1 = true
        07:57:02.310 [     work] work 스레드 인터럽트 상태2 = false
        07:57:02.311 [     work] 인터럽트 메시지 = sleep interrupted
        07:57:02.311 [     work] 스레드 상태 = RUNNABLE
        07:57:02.312 [     work] 자원 정리
        07:57:02.312 [     work] 작업 종료
        */
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    log("작업 중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("인터럽트 메시지 = " + e.getMessage());
                log("스레드 상태 = " + Thread.currentThread().getState());
            }
            log("자원 정리");
            log("작업 종료");
        }
    }
}
