package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV1 {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService executorService = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
        printState(executorService);
        // 00:22:23.122 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 0

        executorService.execute(new RunnableTask("task1"));
        printState(executorService, "task1");
        executorService.execute(new RunnableTask("task2"));
        printState(executorService, "task2");
        // 00:22:23.163 [     main] task1 -> pool = 1, active = 1, queuedTasks = 0, completedTask = 0
        // 00:22:23.164 [     main] task2 -> pool = 2, active = 2, queuedTasks = 0, completedTask = 0

        executorService.execute(new RunnableTask("task3"));
        printState(executorService, "task3");
        executorService.execute(new RunnableTask("task4"));
        printState(executorService, "task4");
        // 00:22:23.165 [     main] task3 -> pool = 2, active = 2, queuedTasks = 1, completedTask = 0
        // 00:22:23.166 [     main] task4 -> pool = 2, active = 2, queuedTasks = 2, completedTask = 0

        executorService.execute(new RunnableTask("task5"));
        printState(executorService, "task5");
        executorService.execute(new RunnableTask("task6"));
        printState(executorService, "task6");
        // 00:22:23.168 [     main] task5 -> pool = 3, active = 3, queuedTasks = 2, completedTask = 0
        // 00:22:23.169 [     main] task6 -> pool = 4, active = 4, queuedTasks = 2, completedTask = 0

        try {
            executorService.execute(new RunnableTask("task7"));
        } catch (RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생 = " + e);
        }
        /*
        00:22:23.171 [     main] task7 실행 거절 예외 발생 = java.util.concurrent.RejectedExecutionException:
        Task thread.executor.RunnableTask@239963d8 rejected from java.util.concurrent.ThreadPoolExecutor@7f690630
        [Running, pool size = 4, active threads = 4, queued tasks = 2, completed tasks = 0]
        */

        sleep(3000);
        log("작업 수행 완료");
        printState(executorService);
        // 00:22:26.177 [     main] pool = 4, active = 0, queuedTasks = 0, completedTask = 6

        sleep(3000);
        log("maximumPoolSize 대기 시간 초과");
        printState(executorService);
        // 00:22:29.181 [     main] pool = 2, active = 0, queuedTasks = 0, completedTask = 6

        executorService.close();
        log("shutdown 완료");
        printState(executorService);
        // 00:22:29.182 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 6
    }
}
