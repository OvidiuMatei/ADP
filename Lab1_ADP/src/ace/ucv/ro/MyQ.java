/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ace.ucv.ro;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author OvidiuM
 */
public class MyQ {
    private int capacity;
    private List<Integer> queue = new LinkedList<>();
    
    public MyQ(int capacity){
        this.capacity = capacity;
    }
     
    public synchronized void add_to_queue(int item) throws InterruptedException{
        while(queue.size() == this.capacity){
            wait();
        }
        
        System.out.println("Thread " + Thread.currentThread().getName() + "producing " + item);
        queue.add(item);
        
        if(queue.size() == 1){
            notifyAll();
        }
    }
    
    public synchronized int rem_from_queue() throws InterruptedException{
        int item;
        
        while(queue.size() == 0){
            wait();
        }
        
        item = queue.remove(0);
        System.out.println("Thread " + Thread.currentThread().getName() + "consuming " + item);
        
        if(queue.size() == (capacity - 1)){
            notifyAll();
        }
        
        return item;
    }
}
