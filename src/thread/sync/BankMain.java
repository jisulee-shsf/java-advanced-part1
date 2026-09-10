package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {
//        BankAccount account = new BankAccountV1(1000);
//        BankAccount account = new BankAccountV2(1000);
//        BankAccount account = new BankAccountV3(1000);
        BankAccount account = new BankAccountV4(1000);

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
        15:20:38.195 [       t1] 거래 시작 = BankAccountV4
        15:20:38.195 [       t2] 거래 시작 = BankAccountV4
        15:20:38.218 [       t1] [검증 시작] 출금액 - 800, 잔액 - 1000
        15:20:38.218 [       t1] [검증 완료] 출금액 - 800, 잔액 - 1000
        15:20:38.620 [     main] t1 상태 = TIMED_WAITING
        15:20:38.620 [     main] t2 상태 = WAITING -> BLOCKED X
        15:20:39.223 [       t1] [출금 완료] 출금액 - 800, 잔액 - 200
        15:20:39.223 [       t1] 거래 종료
        15:20:39.223 [       t2] [검증 시작] 출금액 - 800, 잔액 - 200
        15:20:39.225 [       t2] [검증 실패] 출금액 - 800, 잔액 - 200
        15:20:39.230 [     main] 최종 잔액 = 200
        */
    }
}
