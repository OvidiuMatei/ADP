import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CVSemaphore{
    private int allow;
    private Lock lock;
    private Condition condition;



    public CVSemaphore(int allow, Boolean bool){
        this.allow = allow;
        this.lock = new ReentrantLock(bool);
        this.condition = lock.newCondition();
    }

    public void down(){
        lock.lock();
        while (allow == 0){
            try{
                condition.wait();
            }catch (InterruptedException e){
            }
        }
        allow--;
        lock.unlock();
    }

    public void up(){
        lock.lock();
        allow++;
        condition.notify();
        lock.unlock();
    }
}