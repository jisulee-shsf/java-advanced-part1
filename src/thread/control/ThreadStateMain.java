package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable, "myThread");
        log("myThread.state1 = " + thread.getState()); // NEW

        thread.start();
        Thread.sleep(1000);
        log("myThread.state3 = " + thread.getState()); // TIMED_WAITING

        Thread.sleep(4000);
        log("myThread.state5 = " + thread.getState()); // TERMINATED
        /*
        12:26:53.086 [     main] myThread.state1 = NEW
        12:26:53.098 [ myThread] start
        12:26:53.098 [ myThread] myThread.state2 = RUNNABLE
        12:26:54.100 [     main] myThread.state3 = TIMED_WAITING
        12:26:58.103 [ myThread] myThread.state4 = RUNNABLE
        12:26:59.105 [     main] myThread.state5 = TERMINATED
        */
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                log("start");
                log("myThread.state2 = " + Thread.currentThread().getState()); // RUNNABLE

                Thread.sleep(3000);
                log("myThread.state4 = " + Thread.currentThread().getState()); // RUNNABLE
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
