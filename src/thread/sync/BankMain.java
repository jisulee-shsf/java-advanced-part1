package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {
//        BankAccount account = new BankAccountV1(1000);
//        BankAccount account = new BankAccountV2(1000);
//        BankAccount account = new BankAccountV3(1000);
//        BankAccount account = new BankAccountV4(1000);
//        BankAccount account = new BankAccountV5(1000);
        BankAccount account = new BankAccountV6(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        sleep(500);
        log("t1 상태 = " + t1.getState());
        log("t2 상태 = " + t2.getState());

        t1.join();
        t2.join();

        log("최종 잔액 = " + account.getBalance());
        /*
        16:09:06.924 [       t1] 거래 시작 = BankAccountV6
        16:09:06.924 [       t2] 거래 시작 = BankAccountV6
        16:09:06.955 [       t1] [검증 시작] 출금액 - 800, 잔액 - 1000
        16:09:06.957 [       t1] [검증 완료] 출금액 - 800, 잔액 - 1000
        16:09:07.356 [     main] t1 상태 = TIMED_WAITING -> sleep(1000)
        16:09:07.358 [     main] t2 상태 = TIMED_WAITING -> tryLock(500)
        16:09:07.442 [       t2] [진입 실패] 이미 처리중인 작업이 있습니다.
        16:09:07.963 [       t1] [출금 완료] 출금액 - 800, 잔액 - 200
        16:09:07.963 [       t1] 거래 종료
        16:09:07.969 [     main] 최종 잔액 = 200
        */
    }
}
