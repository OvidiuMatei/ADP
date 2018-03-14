/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ace.ucv.ro;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author OvidiuM
 */
public class ProducerConsumer {
    private static final int SIZE = 10;
    public volatile static LinkedList<Integer> queue = new LinkedList<>();
    
    public static void main(String[] args){
        
        Semaphore semFull = new Semaphore(0);
        Semaphore semFree = new Semaphore(SIZE);
        Semaphore mutex   = new Semaphore(1);
        
        
        Thread pThread = new Thread(new Runnable() {
            private Random random = new Random();
            
                
            @Override
            public void run() {
                
                while(true){
                    Integer item = random.nextInt(100);
                    semFree.down();
                    mutex.down();
                    System.out.println(Thread.currentThread().getName() + " is producing item " + item);
                    queue.add(item);
                    mutex.up();
                    semFull.up();
                } 
            }
        });
        
        Thread cThread = new Thread(new Runnable() {
           
            @Override
            public void run() {
                
                while(true) { 
                    semFull.down();
                    mutex.down();
                    Integer item = queue.removeFirst();
                    System.out.println(Thread.currentThread().getName() + " consuming item " + item);
                    mutex.up();
                    semFree.up();
                }
            }
        });
        
        pThread.setName("Producer");
        cThread.setName("Consumer");
        
        pThread.start();
        cThread.start();
    }
}
