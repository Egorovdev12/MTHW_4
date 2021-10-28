package HW;

import java.util.Random;

public class Call {
    private String number;

    public Call() {
        this.number = "+7(812)" + new Random().nextInt(1000) + "-" + new Random().nextInt(100) + "-" + new Random().nextInt(100);
    }

    public String getNumber() {
        return number;
    }
}