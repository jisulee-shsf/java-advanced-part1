package thread.executor.future;

import static util.MyLogger.log;

public class SumTaskMainV1 {

    public static void main(String[] args) throws InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        log("main 스레드 대기 시작");
        thread1.join();
        thread2.join();
        log("main 스레드 대기 완료");

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int sumAll = task1.result + task2.result;
        log("task1.result + task2.result = " + sumAll);
        log("end");
        /*
        12:04:21.028 [ thread-1] 작업 시작
        12:04:21.028 [ thread-2] 작업 시작
        12:04:21.028 [     main] main 스레드 대기 시작
        12:04:23.046 [ thread-2] 작업 완료 / result = 3775
        12:04:23.046 [ thread-1] 작업 완료 / result = 1275
        12:04:23.047 [     main] main 스레드 대기 완료
        12:04:23.047 [     main] task1.result = 1275
        12:04:23.048 [     main] task2.result = 3775
        12:04:23.048 [     main] task1.result + task2.result = 5050
        12:04:23.048 [     main] end
        */
    }

    static class SumTask implements Runnable {
        private int startValue;
        private int endValue;
        private int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("작업 완료 / result = " + result);
        }
    }
}
