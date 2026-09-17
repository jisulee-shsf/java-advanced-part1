package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;
import static util.MyLogger.log;

public class RejectMainV1 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0, SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        executorService.submit(new RunnableTask("task1"));

        try {
            executorService.submit(new RunnableTask("task1"));
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            log(e);
        }
        executorService.close();

        /*
        12:51:58.159 [     main] 요청 초과
        12:51:58.159 [pool-1-thread-1] task1 시작
        12:51:58.167 [     main] java.util.concurrent.RejectedExecutionException:
        Task java.util.concurrent.FutureTask@73f792cf[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@6b2fad11[Wrapped task = thread.executor.RunnableTask@79698539]]
        rejected from java.util.concurrent.ThreadPoolExecutor@566776ad[Running, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
        12:51:59.172 [pool-1-thread-1] task1 종료
        */
    }
}
