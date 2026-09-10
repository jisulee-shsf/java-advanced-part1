package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTask(), "thread-1");
        thread1.start();

        sleep(100);
        log("thread1 상태 = " + thread1.getState());

        log("main -> unpark(thread1)");
//        LockSupport.unpark(thread1);
        thread1.interrupt();
        /*
        12:52:19.665 [ thread-1] part 시작
        12:52:19.708 [     main] thread1 상태 = WAITING
        12:52:19.709 [     main] main -> unpark(thread1)
        12:52:19.710 [ thread-1] part 종료 및 thread1 상태 = RUNNABLE
        12:52:19.719 [ thread-1] thread1 인터럽트 상태 = true
        */
    }

    static class ParkTask implements Runnable {

        @Override
        public void run() {
            log("part 시작");
            LockSupport.park();
            log("part 종료 및 thread1 상태 = " + Thread.currentThread().getState());
            log("thread1 인터럽트 상태 = " + Thread.currentThread().isInterrupted());
        }
    }
}
