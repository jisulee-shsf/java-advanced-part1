package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L, MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        log("pool 생성");
        printState(executorService);
        // 09:25:21.424 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 0

        for (int i = 1; i <= 6; i++) {
            String threadName = "thread" + i;
            executorService.execute(new RunnableTask(threadName));
            printState(executorService, threadName);
        }
        // 09:25:21.515 [     main] thread1 -> pool = 1, active = 1, queuedTasks = 0, completedTask = 0
        // 09:25:21.519 [     main] thread2 -> pool = 2, active = 2, queuedTasks = 0, completedTask = 0

        // 09:25:21.519 [     main] thread3 -> pool = 2, active = 2, queuedTasks = 1, completedTask = 0
        // 09:25:21.525 [     main] thread4 -> pool = 2, active = 2, queuedTasks = 2, completedTask = 0
        // 09:25:21.528 [     main] thread5 -> pool = 2, active = 2, queuedTasks = 3, completedTask = 0
        // 09:25:21.529 [     main] thread6 -> pool = 2, active = 2, queuedTasks = 4, completedTask = 0

        executorService.close();
        log("shutdown 완료");
    }
}
