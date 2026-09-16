package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;
import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new RunnableTask("task1"));
        executorService.execute(new RunnableTask("task2"));
        executorService.execute(new RunnableTask("task3"));
        executorService.execute(new RunnableTask("longTask", 100_000));
        printState(executorService);

        log("shutdown 시작");
        shutdownAndAwaitTermination(executorService);
        log("shutdown 완료");
        printState(executorService);
        /*
        23:08:06.499 [pool-1-thread-2] task2 시작
        23:08:06.499 [pool-1-thread-1] task1 시작
        23:08:06.499 [     main] pool = 2, active = 2, queuedTasks = 2, completedTask = 0
        23:08:06.508 [     main] shutdown 시작
        23:08:07.512 [pool-1-thread-1] task1 종료
        23:08:07.512 [pool-1-thread-2] task2 종료
        23:08:07.513 [pool-1-thread-1] task3 시작
        23:08:07.513 [pool-1-thread-2] longTask 시작
        23:08:08.515 [pool-1-thread-1] task3 종료
        23:08:16.513 [     main] 서비스 정상 종료 실패 -> 강제 종료 시도
        23:08:16.514 [pool-1-thread-2] 인터럽트 발생 = sleep interrupted
        23:08:16.514 [     main] shutdown 완료
        23:08:16.515 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 4
        Exception in thread "pool-1-thread-2" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
            at util.ThreadUtils.sleep(ThreadUtils.java:10)
            at thread.executor.RunnableTask.run(RunnableTask.java:23)
            at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
            at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
            at java.base/java.lang.Thread.run(Thread.java:1583)
        Caused by: java.lang.InterruptedException: sleep interrupted
            at java.base/java.lang.Thread.sleep0(Native Method)
            at java.base/java.lang.Thread.sleep(Thread.java:509)
            at util.ThreadUtils.sleep(ThreadUtils.java:7)
            ... 4 more
        */
    }

    private static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, SECONDS)) {
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                executorService.shutdownNow();
                if (!executorService.awaitTermination(10, SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
