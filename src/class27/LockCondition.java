package class27;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lizihao
 * @date 2021/12/22 23:30
 */
public class LockCondition {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t1").start();
        new Thread(()->{
            try {
                lock.lock();
                for (char c : aI) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
