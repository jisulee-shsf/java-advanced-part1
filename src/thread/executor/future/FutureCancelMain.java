package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {
        private static final boolean mayInterruptIfRunning = true;
//    private static final boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new MyTask());
        log("Future state = " + future.state());

        sleep(3000);
        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("Future state = " + future.state());
        log("future.cancel(" + mayInterruptIfRunning + ") result = " + cancelResult);

        try {
            log("Future result = " + future.get());
        } catch (CancellationException e) {
            log("Future는 이미 취소되었습니다.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.close();

        /* mayInterruptIfRunning = true
        14:30:34.014 [     main] Future state = RUNNING
        14:30:34.014 [pool-1-thread-1] 작업 중 = 0
        14:30:35.027 [pool-1-thread-1] 작업 중 = 1
        14:30:36.032 [pool-1-thread-1] 작업 중 = 2
        14:30:37.027 [     main] future.cancel(true) 호출
        14:30:37.028 [pool-1-thread-1] 인터럽트 발생
        14:30:37.028 [     main] Future state = CANCELLED
        14:30:37.029 [     main] future.cancel(true) result = true
        14:30:37.030 [     main] Future는 이미 취소되었습니다.
        */

        /* mayInterruptIfRunning = false
        14:31:18.420 [     main] Future state = RUNNING
        14:31:18.420 [pool-1-thread-1] 작업 중 = 0
        14:31:19.431 [pool-1-thread-1] 작업 중 = 1
        14:31:20.433 [pool-1-thread-1] 작업 중 = 2
        14:31:21.433 [     main] future.cancel(false) 호출
        14:31:21.434 [     main] Future state = CANCELLED
        14:31:21.435 [pool-1-thread-1] 작업 중 = 3
        14:31:21.435 [     main] future.cancel(false) result = true
        14:31:21.436 [     main] Future는 이미 취소되었습니다.
        14:31:22.438 [pool-1-thread-1] 작업 중 = 4
        14:31:23.443 [pool-1-thread-1] 작업 중 = 5
        14:31:24.447 [pool-1-thread-1] 작업 중 = 6
        14:31:25.449 [pool-1-thread-1] 작업 중 = 7
        14:31:26.453 [pool-1-thread-1] 작업 중 = 8
        14:31:27.454 [pool-1-thread-1] 작업 중 = 9
        */
    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중 = " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "interrupted";
            }
            return "canceled";
        }
    }
}
