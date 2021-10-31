package HW;

import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Operator implements Runnable {

    private Queue<Call> callQueue;
    private static final int PROCESSING_TIME = 3000;

    public Operator(Queue<Call> queue) {
        this.callQueue = queue;
    }

    @Override
    public void run() {
        Lock operatorLocker = new ReentrantLock();

        while (!Thread.currentThread().isInterrupted()) {
            operatorLocker.lock();
            try {
                if (callQueue.peek() == null) {
                    // делать что-то если звонков в очереди нет
                }
                else {
                    System.out.println(Thread.currentThread().getName() + " начал обработку звонка от " + callQueue.peek().getNumber());
                    Thread.sleep(PROCESSING_TIME);
                    System.out.println(Thread.currentThread().getName() + " успешно обработал звонок от " + callQueue.poll().getNumber());
                    System.out.println("Размер очереди: " + callQueue.size() + " человек");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ": прерывает своё выполнение");
                Thread.currentThread().interrupt();
            } finally {
                operatorLocker.unlock();
            }
        }
    }
}