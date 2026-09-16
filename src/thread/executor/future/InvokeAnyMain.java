package thread.executor.future;

import thread.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

public class InvokeAnyMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        Integer value = executorService.invokeAny(List.of(task1, task2, task3));
        log("value = " + value);

        executorService.close();
        /*
        17:39:15.135 [pool-1-thread-1] task1 실행
        17:39:15.135 [pool-1-thread-2] task2 실행
        17:39:15.135 [pool-1-thread-3] task3 실행
        17:39:16.149 [pool-1-thread-1] task1 완료
        17:39:16.150 [     main] value = 1000
        17:39:16.150 [pool-1-thread-2] 인터럽트 발생 = sleep interrupted
        17:39:16.150 [pool-1-thread-3] 인터럽트 발생 = sleep interrupted
        */
    }
}
