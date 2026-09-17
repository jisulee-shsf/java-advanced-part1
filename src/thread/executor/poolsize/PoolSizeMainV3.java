package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;
import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV3 {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, SECONDS, new SynchronousQueue<Runnable>());
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, SECONDS, new SynchronousQueue<>());

        log("pool 생성");
        printState(executorService);
        // 10:04:23.571 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 0

        for (int i = 1; i <= 4; i++) {
            String threadName = "thread" + i;
            executorService.execute(new RunnableTask(threadName));
            printState(executorService, threadName);
        }
        // 10:04:23.597 [     main] thread1 -> pool = 1, active = 1, queuedTasks = 0, completedTask = 0
        // 10:04:23.597 [     main] thread2 -> pool = 2, active = 2, queuedTasks = 0, completedTask = 0
        // 10:04:23.598 [     main] thread3 -> pool = 3, active = 3, queuedTasks = 0, completedTask = 0
        // 10:04:23.599 [     main] thread4 -> pool = 4, active = 4, queuedTasks = 0, completedTask = 0

        sleep(3000);
        log("작업 수행 완료");
        printState(executorService);
        // 10:04:26.605 [     main] pool = 4, active = 0, queuedTasks = 0, completedTask = 4

        sleep(3000);
        log("maximumPoolSize 대기 시간 초과");
        printState(executorService);
        // 10:04:29.612 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 4

        executorService.close();
        log("shutdown 완료");
        printState(executorService);
        // 10:04:29.613 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 4
    }
}
