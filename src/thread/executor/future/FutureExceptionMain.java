package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureExceptionMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        log("작업 전달");
        Future<Integer> future = executorService.submit(new ExCallable());
        sleep(1000);

        try {
            log("future.get() 호출 시도 -> future.state() = " + future.state());
            Integer result = future.get();
            log("result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log("e = " + e);
            Throwable cause = e.getCause();
            log("cause = " + cause);
        }

        executorService.close();
        /*
        16:54:47.903 [     main] 작업 전달
        16:54:47.914 [pool-1-thread-1] Callable 실행 -> 예외 발생
        16:54:48.922 [     main] future.get() 호출 시도 -> future.state() = FAILED
        16:54:48.923 [     main] e = java.util.concurrent.ExecutionException: java.lang.IllegalStateException: ex
        16:54:48.924 [     main] cause = java.lang.IllegalStateException: ex
        */
    }

    static class ExCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log("Callable 실행 -> 예외 발생");
            throw new IllegalStateException("ex");
        }
    }
}
