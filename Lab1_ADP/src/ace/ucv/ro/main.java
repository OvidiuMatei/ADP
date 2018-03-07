/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ace.ucv.ro;

/**
 *
 * @author OvidiuM
 */
public class main {
    
    public static void main(String[] args) throws InterruptedException{
        MyQ queue = new MyQ(10);
        
        new Thread(new Producer(queue), "Producer ").start();
        new Thread(new Consumer(queue), "Consumer ").start();
        
        new Thread(new Producer(queue), "Producer ").join();
        new Thread(new Consumer(queue), "Consumer ").join();
    }
}
