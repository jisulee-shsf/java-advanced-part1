package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
        /*
        ...
        09:30:58.843 [     work] 작업 중
        09:30:58.843 [     work] 작업 중
        09:30:58.844 [     work] 작업 중
        09:30:58.844 [     main] 작업 중단 지시
        09:30:58.844 [     work] 작업 중
        09:30:58.853 [     main] work 스레드 인터럽트 상태1 = true
        09:30:58.853 [     work] work 스레드 인터럽트 상태2 = false
        09:30:58.854 [     work] 자원 정리 시도
        09:30:59.859 [     work] 작업 정리 완료
        09:30:59.860 [     work] 작업 완료
        */
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) { // true -> false
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(1000);
                log("작업 정리 완료");
            } catch (InterruptedException e) {
                log("자원 정리 실패");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }
            log("작업 완료");
        }
    }
}
