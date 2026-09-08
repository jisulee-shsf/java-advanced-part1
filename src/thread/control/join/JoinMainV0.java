package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV0 {

    public static void main(String[] args) {
        log("start");
        Thread thread1 = new Thread(new Job(), "thread-1");
        Thread thread2 = new Thread(new Job(), "thread-2");

        thread1.start();
        thread2.start();
        log("end");
        /*
        13:35:03.164 [     main] start
        13:35:03.173 [ thread-1] 작업 시작
        13:35:03.173 [     main] end
        13:35:03.173 [ thread-2] 작업 시작
        13:35:05.178 [ thread-2] 작업 종료
        13:35:05.178 [ thread-1] 작업 종료
        */
    }

    static class Job implements Runnable {
        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            log("작업 종료");
        }
    }
}
