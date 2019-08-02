package basic;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class ThreadCooperation {
    private static Account account = new Account();
    public static void testCooperation(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new DepositTask());
        executorService.execute(new WithdrawTask());
        executorService.shutdown();


    }

    private static class DepositTask implements Runnable {
        @Override
        public void run(){
            try {
                while (true) {
                    account.deposit((int) (Math.random() * 10 + 1));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex){
            }
        }
    }

    private static class WithdrawTask implements Runnable {
        @Override
        public void run(){
            try{
                while(true){
                    account.withdraw((int)(Math.random() * 10 + 5));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex){
            }
        }
    }

    private static class Account {
        private int balance;
        private static Lock lock = new ReentrantLock();
        private static Condition condition = lock.newCondition();

        public Account(){
            balance = 0;
        }

        public void deposit(int amount){
            lock.lock();
            try{
                balance = balance + amount;
                System.out.println("Deposit " + amount + " " + balance);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void withdraw(int amount){
            lock.lock();
            try{
                while(balance < amount){
                    System.out.println("Wait for deposit");
                    condition.await();
                }
                balance = balance - amount;
                System.out.println("Withdraw " + amount + " " + balance);
            } catch (InterruptedException ex){

            } finally {
                lock.unlock();
            }
        }

    }
}
