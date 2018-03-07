/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ace.ucv.ro;

import java.util.Random;

/**
 *
 * @author OvidiuM
 */
public class Consumer implements Runnable{
    private MyQ queue;
    private Random random = new Random();
    
    public Consumer(MyQ queue){
        this.queue = queue;
    }
    
    @Override
    public void run(){
        try{
            for(;;){
                queue.rem_from_queue();
                Thread.sleep((int)(Math.random() * 5000) + 3000);
            }
        }catch(InterruptedException ex){
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }
}
