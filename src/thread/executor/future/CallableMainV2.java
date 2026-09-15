package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        log("submit() 호출");
        Future<Integer> future = executorService.submit(new MyCallable());
        log("future 즉시 반환 - future = " + future);

        log("future.get() [블로킹] 메서드 호출 시작 - main 스레드 WAITING");
        Integer result = future.get();
        log("future.get() [블로킹] 메서드 호출 완료 - main 스레드 RUNNABLE");

        log("result = " + result);
        log("future 완료 - future = " + future);
        executorService.close();
        /*
        01:34:31.957 [     main] submit() 호출
        01:34:31.969 [pool-1-thread-1] Callable 시작
        01:34:31.970 [     main] future 즉시 반환 - future = java.util.concurrent.FutureTask@14acaea5[Not completed, task = thread.executor.future.CallableMainV2$MyCallable@224edc67]
        01:34:31.971 [     main] future.get() [블로킹] 메서드 호출 시작 - main 스레드 WAITING
        01:34:33.987 [pool-1-thread-1] value = 6
        01:34:33.988 [pool-1-thread-1] Callable 종료
        01:34:33.988 [     main] future.get() [블로킹] 메서드 호출 완료 - main 스레드 RUNNABLE
        01:34:33.989 [     main] result = 6
        01:34:33.991 [     main] future 완료 - future = java.util.concurrent.FutureTask@14acaea5[Completed normally]
        */
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("value = " + value);
            log("Callable 종료");
            return value;
        }
    }
}
