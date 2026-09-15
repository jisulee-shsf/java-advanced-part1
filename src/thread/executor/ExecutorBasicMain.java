package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        log("초기 상태");
        printState(executorService);

        executorService.execute(new RunnableTask("task1"));
        executorService.execute(new RunnableTask("task2"));
        executorService.execute(new RunnableTask("task3"));
        executorService.execute(new RunnableTask("task4"));
        log("작업 수행 중");
        printState(executorService);

        sleep(3000);
        log("작업 수행 종료");
        printState(executorService);

        executorService.close();
        log("shutdown 완료");
        printState(executorService);
        /*
        22:13:56.399 [     main] 초기 상태
        22:13:56.442 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 0
        22:13:56.445 [     main] 작업 수행 중
        22:13:56.446 [     main] pool = 2, active = 2, queuedTasks = 2, completedTask = 0
        22:13:56.446 [pool-1-thread-1] task1 시작
        22:13:56.446 [pool-1-thread-2] task2 시작
        22:13:57.451 [pool-1-thread-1] task1 종료
        22:13:57.451 [pool-1-thread-2] task2 종료
        22:13:57.451 [pool-1-thread-1] task3 시작
        22:13:57.451 [pool-1-thread-2] task4 시작
        22:13:58.457 [pool-1-thread-2] task4 종료
        22:13:58.457 [pool-1-thread-1] task3 종료
        22:13:59.452 [     main] 작업 수행 종료
        22:13:59.453 [     main] pool = 2, active = 0, queuedTasks = 0, completedTask = 4
        22:13:59.454 [     main] shutdown 완료
        22:13:59.455 [     main] pool = 0, active = 0, queuedTasks = 0, completedTask = 4
        */
    }
}
