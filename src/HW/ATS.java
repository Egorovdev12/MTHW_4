package HW;

import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ATS implements Runnable{

    private static final int LOAD_TIME = 1000;
    private static final int CALLS_PER_TIME = 5;
    private Queue<Call> callQueue;

    public ATS(Queue<Call> queue) {
        this.callQueue = queue;
    }

    @Override
    public void run() {
        Lock atsLocker = new ReentrantLock();
        while (!Thread.currentThread().isInterrupted()) {
            atsLocker.lock();
            try {
                Thread.sleep(LOAD_TIME);
                for (int i = 0; i < CALLS_PER_TIME; i++){
                    callQueue.offer(new Call());
                }
                System.out.println("Поуступило 5 новых звонков, размер очереди: " + callQueue.size() + " человек");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ": прерывает своё выполнение");
                Thread.currentThread().interrupt();
            }
            finally {
                atsLocker.unlock();
            }
        }
    }
}