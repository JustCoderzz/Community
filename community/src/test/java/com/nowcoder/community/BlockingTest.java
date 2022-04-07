package com.nowcoder.community;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lusir
 * @date 2022/3/4 - 12:57
 **/
public class BlockingTest {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(10);
        new Thread(new producer(queue)).start();
        new Thread(new consumer(queue)).start();
    }
}

class producer implements  Runnable{

    private BlockingQueue<Integer> queue;
    public producer(BlockingQueue<Integer> queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        try {
            for(int i=0;i<100;i++){
                Thread.sleep(20);
                queue.put(i);
                System.out.println(Thread.currentThread().getName()+"生成："+queue.size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class  consumer implements  Runnable{
    private BlockingQueue<Integer> queue;
    public consumer(BlockingQueue<Integer> queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(new Random().nextInt(1000));
                queue.take();
                System.out.println(Thread.currentThread().getName()+"消费："+queue.size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}