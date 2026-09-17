package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;
import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV4 {
    private static final int TASK_SIZE = 1100;
//    private static final int TASK_SIZE = 1200;
//    private static final int TASK_SIZE = 1201;

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(100, 200,
                60, SECONDS, new ArrayBlockingQueue<>(1000));
        printState(executorService);

        long startMs = System.currentTimeMillis();
        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                executorService.execute(new RunnableTask(taskName));
                printState(executorService, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        executorService.close();
        long endMs = System.currentTimeMillis();
        log("time = " + (endMs - startMs));
    }

    /* [일반] TASK_SIZE = 1100 -> time = 11133
    11:22:07.447 [     main] task1 -> pool = 1, active = 1, queuedTasks = 0, completedTask = 0
    11:22:07.518 [     main] task100 -> pool = 100, active = 100, queuedTasks = 0, completedTask = 0

    11:22:07.519 [     main] task101 -> pool = 100, active = 100, queuedTasks = 1, completedTask = 0
    11:22:07.648 [     main] task1100 -> pool = 100, active = 100, queuedTasks = 1000, completedTask = 0
    */

    /* [긴급] TASK_SIZE = 1200 -> time = 6297
    11:23:28.935 [     main] task1 -> pool = 1, active = 1, queuedTasks = 0, completedTask = 0
    11:23:29.030 [     main] task100 -> pool = 100, active = 100, queuedTasks = 0, completedTask = 0

    11:23:29.030 [     main] task101 -> pool = 100, active = 100, queuedTasks = 1, completedTask = 0
    11:23:29.200 [     main] task1100 -> pool = 100, active = 100, queuedTasks = 1000, completedTask = 0

    11:23:29.201 [     main] task1101 -> pool = 101, active = 101, queuedTasks = 1000, completedTask = 0
    11:23:29.243 [     main] task1200 -> pool = 200, active = 200, queuedTasks = 1000, completedTask = 0
    */


    /* [거절] TASK_SIZE = 1201 -> time = 6327
    11:30:49.359 [     main] task1 -> pool = 1, active = 1, queuedTasks = 0, completedTask = 0
    11:30:49.425 [     main] task100 -> pool = 100, active = 100, queuedTasks = 0, completedTask = 0

    11:30:49.426 [     main] task101 -> pool = 100, active = 100, queuedTasks = 1, completedTask = 0
    11:30:49.581 [     main] task1100 -> pool = 100, active = 100, queuedTasks = 1000, completedTask = 0

    11:30:49.581 [     main] task1101 -> pool = 101, active = 101, queuedTasks = 1000, completedTask = 0
    11:30:49.631 [     main] task1200 -> pool = 200, active = 200, queuedTasks = 1000, completedTask = 0

    11:30:49.644 [     main] task1201 -> java.util.concurrent.RejectedExecutionException:
    Task thread.executor.RunnableTask@71bbf57e rejected from java.util.concurrent.ThreadPoolExecutor@7f690630[Running, pool size = 200, active threads = 200, queued tasks = 1000, completed tasks = 0]
    */
}
