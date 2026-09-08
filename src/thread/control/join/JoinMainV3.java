package thread.control.join;

import util.ThreadUtils;

import static util.MyLogger.log;

public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {
        log("start");

        SumTask task = new SumTask(1, 50);
        Thread thread = new Thread(task, "thread-1");

        thread.start();

        log("main 대기 시작");
        thread.join(1000);
        log("main 대기 완료");

        log("task.result = " + task.result);

        log("end");
        /*
        23:20:20.846 [     main] start
        23:20:20.859 [     main] main 대기 시작
        23:20:20.859 [ thread-1] 작업 시작
        23:20:21.860 [     main] main 대기 완료
        23:20:21.871 [     main] task.result = 0
        23:20:21.872 [     main] end
        23:20:22.867 [ thread-1] 작업 완료, result = 1275
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
