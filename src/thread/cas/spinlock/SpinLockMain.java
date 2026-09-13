package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {

    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) {
        SpinLockBad spinLock = new SpinLockBad();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    log("비즈니스 로직 실행"); // critical section
                } finally {
                    spinLock.unlock();
                }
            }
        };

        for (int i = 1; i <= THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable, "thread-" + i);
            thread.start();
        }
        /*
        14:17:16.202 [ thread-1] 락 획득 시도
        14:17:16.202 [ thread-2] 락 획득 시도
        14:17:17.213 [ thread-1] 락 획득 완료
        14:17:17.213 [ thread-2] 락 획득 완료
        14:17:17.213 [ thread-1] 비즈니스 로직 실행
        14:17:17.213 [ thread-2] 비즈니스 로직 실행
        14:17:17.214 [ thread-1] 락 반납 완료
        14:17:17.214 [ thread-2] 락 반납 완료
        */
    }
}
