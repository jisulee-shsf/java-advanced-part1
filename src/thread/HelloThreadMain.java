package thread;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " = main start");
        System.out.println(Thread.currentThread().getName() + " = run() 호출 전");

        HelloThread thread = new HelloThread();
        thread.start();
//        thread.run();

        System.out.println(Thread.currentThread().getName() + " = run() 호출 후");
        System.out.println(Thread.currentThread().getName() + " = main end");
        /*
        main = main 시작
        main = run() 호출 전
        main = run() 호출 후
        main = main 종료
        Thread-0 = run() 실행
        */
    }
}
