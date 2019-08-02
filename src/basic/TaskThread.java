package basic;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TaskThread {

    private static Account account = new Account();

    public static void testThread(){
        Runnable printA = new TaskThread.PrintChar('a', 100);
        Runnable printB = new TaskThread.PrintChar('b', 100);
        Runnable printC = new TaskThread.PrintChar('c', 100);

        Thread threadA = new Thread(printA);
        Thread threadB = new Thread(printB);
        Thread threadC = new Thread(printC);

        threadA.start();
        threadB.start();
        threadC.start();

    }

    public static void testExecutor(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new PrintChar('A', 100));
        executorService.execute(new PrintChar('B', 100));
        executorService.execute(new PrintChar('C', 100));

        executorService.shutdown();
    }

    public static void testWithSync(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0; i<100; i++) {
            executorService.execute(new AddPennyTask());
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){
        }
        System.out.println("Balance=" + account.getBalance());
    }

    private static class PrintChar implements Runnable {
        private char charToPrint;
        private int num;

        public PrintChar(char c, int n){
            charToPrint = c;
            num = n;
        }

        @Override
        public void run(){
            for (int i=0; i < num; i++){
                System.out.print(charToPrint);
                Thread.yield();
            }
        }
    }

    private static class AddPennyTask implements Runnable{
        public void run(){
            account.deposit(1);
        }
    }

    private static class Account{
        private int balance = 0;
        private static Lock lock = new ReentrantLock();

        public int getBalance(){
            return balance;
        }

        public void deposit(int amount){
            lock.lock();
            try{
                int newBalance = balance + amount;
                Thread.sleep(1);
                balance = newBalance;
            } catch (InterruptedException ex){
            }
            finally {
                lock.unlock();
            }
        }
    }
}
