package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {

    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) {
//        SpinLockBad spinLock = new SpinLockBad();
        SpinLock spinLock = new SpinLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    log("비즈니스 로직 실행"); // critical section
//                    sleep(1);
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
        15:31:12.587 [ thread-1] 락 획득 시도
        15:31:12.587 [ thread-2] 락 획득 시도

        15:31:12.595 [ thread-1] 락 획득 완료
        15:31:12.596 [ thread-2] 락 획득 실패 - 스핀 대기

        15:31:12.596 [ thread-1] 비즈니스 로직 실행
        15:31:12.596 [ thread-2] 락 획득 실패 - 스핀 대기

        15:31:12.596 [ thread-1] 락 반납 완료
        15:31:12.597 [ thread-2] 락 획득 완료

        15:31:12.597 [ thread-2] 비즈니스 로직 실행
        15:31:12.598 [ thread-2] 락 반납 완료
        */
    }
}
