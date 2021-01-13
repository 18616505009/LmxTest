package datastructure;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lmx
 * @date 2020/11/27 2:18 下午
 * 数据结构-队列
 * 参考资料 https://www.cnblogs.com/lemon-flm/p/7877898.html
 * <p>
 * Queue： 基本上，一个队列就是一个先入先出（FIFO）的数据结构
 * Queue接口与List、Set同一级别，都是继承了Collection接口。LinkedList实现了Deque接口。
 */
public class Queue {

    public static void main(String[] args) {
//        testPriorityQueue();

        testBasket();
    }

    /**
     * 测试一个非阻塞队列
     */
    public static void testPriorityQueue() {
        PriorityQueue<Object> priorityQueue = new PriorityQueue();
        priorityQueue.add(1);
        priorityQueue.add(2);
        priorityQueue.add(3);

        System.out.println("out -> " + priorityQueue.poll());
        System.out.println("out -> " + priorityQueue.poll());
        System.out.println("out -> " + priorityQueue.poll());
        System.out.println("out -> " + priorityQueue.poll());
    }


    /**
     * 定义装苹果的篮子
     */
    public static class Basket {
        // 篮子，能够容纳3个苹果
        BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

        // 生产苹果，放入篮子
        public void produce() throws InterruptedException {
            // put方法放入一个苹果，若basket满了，等到basket有位置
            basket.put("An apple");
        }

        // 消费苹果，从篮子中取走
        public String consume() throws InterruptedException {
            // get方法取出一个苹果，若basket为空，等到basket有苹果为止
            String apple = basket.take();
            return apple;
        }

        public int getAppleNumber() {
            return basket.size();
        }

    }

    //　测试方法
    public static void testBasket() {
        // 建立一个装苹果的篮子
        final Basket basket = new Basket();
        // 定义苹果生产者
        class Producer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 生产苹果
                        System.out.println("生产者准备生产苹果："
                                + System.currentTimeMillis());
                        basket.produce();
                        System.out.println("生产者生产苹果完毕："
                                + System.currentTimeMillis());
                        System.out.println("生产完后有苹果：" + basket.getAppleNumber() + "个");
                        // 休眠300ms
                        Thread.sleep(300);
                    }
                } catch (InterruptedException ex) {
                }
            }
        }
        // 定义苹果消费者
        class Consumer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 消费苹果
                        System.out.println("消费者准备消费苹果："
                                + System.currentTimeMillis());
                        basket.consume();
                        System.out.println("消费者消费苹果完毕："
                                + System.currentTimeMillis());
                        System.out.println("消费完后有苹果：" + basket.getAppleNumber() + "个");
                        // 休眠1000ms
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex) {
                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        service.submit(producer);
        service.submit(consumer);
        // 程序运行10s后，所有任务停止
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        service.shutdownNow();
    }

}
