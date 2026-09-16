package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;

public class SumTaskMainV2_BadV1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = executorService.submit(task1);
        Integer sum1 = future1.get();

        Future<Integer> future2 = executorService.submit(task2);
        Integer sum2 = future2.get();

        log("sum1 = " + sum1);
        log("sum2 = " + sum2);

        int sumAll = sum1 + sum2;
        log("sum1 + sum2 = " + sumAll);
        log("end");

        executorService.close();
        /*
        12:41:45.187 [pool-1-thread-1] 작업 시작
        12:41:47.203 [pool-1-thread-1] 작업 완료 / sum = 1275
        12:41:47.204 [pool-1-thread-2] 작업 시작
        12:41:49.205 [pool-1-thread-2] 작업 완료 / sum = 3775
        12:41:49.206 [     main] sum1 = 1275
        12:41:49.206 [     main] sum2 = 3775
        12:41:49.206 [     main] sum1 + sum2 = 5050
        12:41:49.207 [     main] end
        */
    }

    static class SumTask implements Callable<Integer> {
        private int startValue;
        private int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws Exception {
            log("작업 시작");

            Thread.sleep(2000);

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            log("작업 완료 / sum = " + sum);
            return sum;
        }
    }
}
