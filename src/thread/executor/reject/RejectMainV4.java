package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.SECONDS;
import static util.MyLogger.log;

public class RejectMainV4 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0, SECONDS, new SynchronousQueue<>(), new MyRejectedExecutionHandler());

        executorService.submit(new RunnableTask("task1"));
        executorService.submit(new RunnableTask("task2"));
        executorService.submit(new RunnableTask("task3"));
        executorService.close();
        /*
        13:57:28.318 [pool-1-thread-1] task1 시작
        13:57:28.318 [     main] [경고] 거절 작업 수 = 1
        13:57:28.324 [     main] [경고] 거절 작업 수 = 2
        13:57:29.328 [pool-1-thread-1] task1 종료
        */
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        static AtomicInteger count = new AtomicInteger(0);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int result = count.incrementAndGet();
            log("[경고] 거절 작업 수 = " + result);
        }
    }
}
