package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor threadPoolExecutor) {
            int pool = threadPoolExecutor.getPoolSize();
            int active = threadPoolExecutor.getActiveCount();
            int queuedTasks = threadPoolExecutor.getQueue().size();
            long completedTask = threadPoolExecutor.getCompletedTaskCount();
            log("pool = " + pool + ", active = " + active + ", queuedTasks = " + queuedTasks + ", completedTask = " + completedTask);
        } else {
            log(executorService);
        }
    }
}
