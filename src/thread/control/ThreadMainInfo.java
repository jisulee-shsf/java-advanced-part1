package thread.control;

import thread.start.HelloThread;

import static util.MyLogger.log;

public class ThreadMainInfo {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId() = " + mainThread.threadId());
        log("mainThread.getName() = " + mainThread.getName());
        log("mainThread.getPriority() = " + mainThread.getPriority());
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
        log("mainThread.getState() = " + mainThread.getState());
        /*
        21:50:16.986 [     main] mainThread = Thread[#1,main,5,main]
        21:50:17.002 [     main] mainThread.threadId() = 1
        21:50:17.003 [     main] mainThread.getName() = main
        21:50:17.009 [     main] mainThread.getPriority() = 5
        21:50:17.010 [     main] mainThread.getThreadGroup() = java.lang.ThreadGroup[name=main,maxpri=10]
        21:50:17.010 [     main] mainThread.getState() = RUNNABLE
        */

        Thread myThread = new Thread(new HelloThread(), "myThread");
        log("myThread = " + myThread);
        log("myThread.threadId() = " + myThread.threadId());
        log("myThread.getName() = " + myThread.getName());
        log("myThread.getPriority() = " + myThread.getPriority());
        log("myThread.getThreadGroup() = " + myThread.getThreadGroup());
        log("myThread.getState() = " + myThread.getState());
        /*
        21:50:17.012 [     main] mainThread = Thread[#22,myThread,5,main]
        21:50:17.012 [     main] mainThread.threadId() = 22
        21:50:17.012 [     main] mainThread.getName() = myThread
        21:50:17.013 [     main] mainThread.getPriority() = 5
        21:50:17.014 [     main] mainThread.getThreadGroup() = java.lang.ThreadGroup[name=main,maxpri=10]
        21:50:17.014 [     main] mainThread.getState() = NEW
        */
    }
}
