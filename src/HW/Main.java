package HW;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {
        Queue<Call> incomingCalls = new ConcurrentLinkedQueue<>();
        ATS ats = new ATS(incomingCalls);
        Operator operator = new Operator(incomingCalls);

        ThreadGroup workingOperators = new ThreadGroup("active Operators");

        for (int i = 0; i < 4; i++) {
            new Thread(workingOperators, operator, "Оператор №" + (i+1)).start();
        }
        Thread atsThread = new Thread(null, ats, "Stream 1"); atsThread.start();

        while (true) {
            if (incomingCalls.size() > 100) {
                workingOperators.interrupt();
                atsThread.interrupt();
                break;
            }
        }
    }
}
