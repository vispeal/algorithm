package basic;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConsumerProducer {
    private static Buffer buffer = new Buffer();

    public static void testConsumerProducer(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            try {
                while (true) {
                    int value = buffer.read();
                    System.out.println("Read " + value);
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex){

            }
        });
        executorService.execute(()->{
            try {
                while (true) {
                    int value = (int)(Math.random() * 10000);
                    System.out.println("Write " + value);
                    buffer.write(value);
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex){

            }
        });
        executorService.shutdown();
    }

    private static class Buffer {
        private final int CAPACITY = 1;
        private LinkedList<Integer> list = new LinkedList<>();
        private static Lock lock = new ReentrantLock();
        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();

        public Buffer(){
        }

        public int read(){
            lock.lock();
            int value = 0;
            try{
                while(list.isEmpty()){
                    System.out.println("Wait not empty");
                    notEmpty.await();
                }
                value = list.remove();
                notFull.signal();
                return value;
            } catch (InterruptedException ex){
            } finally {
                lock.unlock();
                return value;
            }
        }

        public void write(int value){
            lock.lock();
            try{
                while(list.size() == CAPACITY){
                    System.out.println("Wait not full");
                    notFull.await();
                }
                list.offer(value);
                notEmpty.signal();
            } catch(InterruptedException ex){

            } finally {
                lock.unlock();
            }
        }


    }
}
