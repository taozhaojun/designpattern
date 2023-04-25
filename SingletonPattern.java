import java.util.Random;

public class SingletonPattern {

    public static void main(String[] args) {
        new Thread(new DepositTask()).start();
        new Thread(new WithdrawTask()).start();


    }

}

class DepositTask implements Runnable {

    @Override
    public void run() {
        Random rnd = new Random();

        while (true) {
            int amount = rnd.nextInt(551) + 50;
            Bank.getInstance().update(amount);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}

class WithdrawTask implements Runnable {

    @Override
    public void run() {
        Random rnd = new Random();

        while (true) {
            int amount = rnd.nextInt(551) + 50;
            Bank.getInstance().update(amount * -1);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}

//singleton
class Bank {
    private static Bank bankInstance = null;
    public int amount = 2000;

//	private Bank() {
//	}

    public synchronized void update(int amount) {
        this.amount += amount;
        System.out.println("Amount update: " + amount + ", the current total is " + this.amount);
    }

    public static Bank getInstance() {

        if (bankInstance == null) {
            bankInstance = new Bank();
        }

        return bankInstance;
    }

}