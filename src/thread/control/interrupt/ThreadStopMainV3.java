package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

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
        09:08:20.482 [     work] 작업 중
        09:08:20.482 [     work] 작업 중
        09:08:20.483 [     work] 작업 중
        09:08:20.483 [     main] 작업 중단 지시
        09:08:20.483 [     work] 작업 중
        09:08:20.492 [     main] work 스레드 인터럽트 상태1 = true
        09:08:20.492 [     work] work 스레드 인터럽트 상태2 = true
        09:08:20.493 [     work] 자원 정리 시도
        09:08:20.494 [     work] 자원 정리 실패
        09:08:20.494 [     work] work 스레드 인터럽트 상태3 = false
        09:08:20.494 [     work] 작업 완료
        */
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(1000);
                log("자원 정리 완료");
            } catch (InterruptedException e) {
                log("자원 정리 실패");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }
            log("작업 완료");
        }
    }
}
