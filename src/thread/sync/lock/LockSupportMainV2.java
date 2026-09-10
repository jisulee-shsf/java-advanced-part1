package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTask(), "thread-1");
        thread1.start();

        sleep(100);
        log("thread1 상태 = " + thread1.getState());
    }

    static class ParkTask implements Runnable {

        @Override
        public void run() {
            log("part 시작");
            LockSupport.parkNanos(2_000_000_000);
            log("part 종료 및 thread1 상태 = " + Thread.currentThread().getState());
            log("thread1 인터럽트 상태 = " + Thread.currentThread().isInterrupted());
            /*
            13:21:42.697 [ thread-1] part 시작
            13:21:42.737 [     main] thread1 상태 = TIMED_WAITING
            13:21:44.708 [ thread-1] part 종료 및 thread1 상태 = RUNNABLE
            13:21:44.719 [ thread-1] thread1 인터럽트 상태 = false
            */
        }
    }
}
