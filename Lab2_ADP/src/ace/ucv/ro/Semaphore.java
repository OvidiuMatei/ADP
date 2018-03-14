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
public class Semaphore {
    int capacity;
    
    public Semaphore(int initialCapacity){
        this.capacity = initialCapacity;
    }
    
    public synchronized void down(){
        while (capacity <= 0){
            try{
                this.wait();
            }catch (InterruptedException e){
                }
        }
        capacity--;
    }
    
    public synchronized void up(){
        capacity++;
        this.notify();
    }
}
