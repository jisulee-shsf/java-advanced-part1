package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RejectMainV3 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0, SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        executorService.submit(new RunnableTask("task1"));
        executorService.submit(new RunnableTask("task2"));
        executorService.submit(new RunnableTask("task3"));
        executorService.close();
        /*
        13:34:37.221 [pool-1-thread-1] task1 시작
        13:34:37.221 [     main] task2 시작
        13:34:38.234 [     main] task2 종료
        13:34:38.234 [pool-1-thread-1] task1 종료
        13:34:38.235 [     main] task3 시작
        13:34:39.239 [     main] task3 종료
        */
    }
}
