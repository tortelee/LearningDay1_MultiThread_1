package com.chapter1_3;

import com.sun.corba.se.spi.activation.BadServerDefinitionHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class CountExample {
    private static int threadTotal = 1; // control the number of max thread numbers.
    private static int clientTotal = 5000; // number of thread

    private static  long  count =0;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for(int index=0;index<clientTotal;index++){
            exec.execute(()->{
                try {
                    semaphore.acquire(); // up the count
                    add();
                    semaphore.release(); // down the count
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        exec.shutdown();
        System.out.println(count);

    }
    private static void add(){
        count++;
    }

}
