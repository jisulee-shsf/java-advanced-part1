package thread.control.join;

import util.ThreadUtils;

import static util.MyLogger.log;

public class JoinMainV2 {

    public static void main(String[] args) throws InterruptedException {
        log("start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        log("main 대기 시작");
        thread1.join();
        thread2.join();
        log("main 대기 완료");

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int sum = task1.result + task2.result;
        log("task1.result + task2.result = " + sum);

        log("end");
        /*
        23:02:52.025 [     main] start
        23:02:52.032 [     main] main 대기 시작
        23:02:52.032 [ thread-1] 작업 시작
        23:02:52.033 [ thread-2] 작업 시작
        23:02:54.046 [ thread-2] 작업 완료, result = 3775
        23:02:54.046 [ thread-1] 작업 완료, result = 1275
        23:02:54.046 [     main] main 대기 완료
        23:02:54.047 [     main] task1.result = 1275
        23:02:54.047 [     main] task2.result = 3775
        23:02:54.047 [     main] task1.result + task2.result = 5050
        23:02:54.048 [     main] end
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
