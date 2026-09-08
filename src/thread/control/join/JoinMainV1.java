package thread.control.join;

import util.ThreadUtils;

import static util.MyLogger.log;

public class JoinMainV1 {

    public static void main(String[] args) {
        log("start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int sum = task1.result + task2.result;
        log("task1.result + task2.result = " + sum);

        log("end");
        /*
        14:05:24.274 [     main] start
        14:05:24.283 [ thread-1] 작업 시작
        14:05:24.283 [ thread-2] 작업 시작
        14:05:24.292 [     main] task1.result = 0
        14:05:24.293 [     main] task2.result = 0
        14:05:24.293 [     main] task1.result + task2.result = 0
        14:05:24.294 [     main] end
        14:05:26.287 [ thread-1] 작업 완료, result = 1275
        14:05:26.287 [ thread-2] 작업 완료, result = 3775
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

            ThreadUtils.sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;

            log("작업 완료, result = " + result);
        }
    }
}
