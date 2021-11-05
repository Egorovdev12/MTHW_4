package HW;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private static final int MAX_QUEUE_SIZE = 100;

    public static void main(String[] args) {
        Queue<Call> incomingCalls = new LinkedBlockingQueue<>();
        ATS ats = new ATS(incomingCalls);
        Operator operator = new Operator(incomingCalls);

        ThreadGroup workingOperators = new ThreadGroup("active Operators");

        for (int i = 0; i < 4; i++) {
            new Thread(workingOperators, operator, "Оператор №" + (i+1)).start();
        }
        Thread atsThread = new Thread(null, ats, "Stream 1");
        atsThread.start();

        while (true) {
            if (incomingCalls.size() > MAX_QUEUE_SIZE) {
                workingOperators.interrupt();
                atsThread.interrupt();
                break;
            }
        }
    }
}
