package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RejectMainV2 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0, SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        executorService.submit(new RunnableTask("task1"));
        executorService.submit(new RunnableTask("task2"));
        executorService.submit(new RunnableTask("task3"));
        executorService.close();
        /*
        12:57:36.007 [pool-1-thread-1] task1 시작
        12:57:37.022 [pool-1-thread-1] task1 종료
        */
    }
}
