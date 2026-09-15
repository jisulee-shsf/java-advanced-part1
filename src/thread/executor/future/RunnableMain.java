package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class RunnableMain {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "thread-1");
        thread.start();
        thread.join();
        int result = task.value;
        log("result = " + result);
        /*
        23:29:20.774 [  thread1] Runnable 시작
        23:29:22.794 [  thread1] value = 2
        23:29:22.794 [  thread1] Runnable 종료
        23:29:22.795 [     main] result = 2
        */
    }

    static class MyRunnable implements Runnable {
        private int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("value = " + value);
            log("Runnable 종료");
        }
    }
}
