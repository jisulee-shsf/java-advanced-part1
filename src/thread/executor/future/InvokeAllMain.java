package thread.executor.future;

import thread.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static util.MyLogger.log;

public class InvokeAllMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        List<Future<Integer>> futures = executorService.invokeAll(List.of(task1, task2, task3));
        for (Future<Integer> future : futures) {
            Integer value = future.get();
            log("value = " + value);
        }
        executorService.close();
        /*
        17:37:19.452 [pool-1-thread-2] task2 실행
        17:37:19.452 [pool-1-thread-1] task1 실행
        17:37:19.453 [pool-1-thread-3] task3 실행
        17:37:20.469 [pool-1-thread-1] task1 완료
        17:37:21.466 [pool-1-thread-2] task2 완료
        17:37:22.467 [pool-1-thread-3] task3 완료
        17:37:22.468 [     main] value = 1000
        17:37:22.469 [     main] value = 2000
        17:37:22.469 [     main] value = 3000
        */
    }
}
