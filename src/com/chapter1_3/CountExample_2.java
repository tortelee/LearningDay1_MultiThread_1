package com.chapter1_3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class CountExample_2 {
    private static int threadTotal = 500; // control the number of max thread numbers.
    private static int clientTotal = 5000; // number of thread

    private static  long  count =0;

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for(int index=0;index<clientTotal;index++){
            final int threadNum = index;
            exec.execute(()->{
                try {
                    semaphore.acquire(); // up the count
                    func(threadNum);
                    semaphore.release(); // down the count
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        exec.shutdown();
        System.out.println(map.size());

    }
    private static void add(){
        count++;
    }
    public static void  func(int threadNum){
        map.put(threadNum,threadNum);
    }

}
