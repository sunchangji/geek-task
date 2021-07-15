package com.suncj.geektask.week04;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 思考有多少种方式，在main函数启动一个新线程，运行一个方法，拿到这 个方法的返回值后，退出主线程?
 *
 * @Classname Thread01
 * @Description
 * @Date 2021/7/14 1:40 下午
 * @Created by sunchangji
 */
public class Homework {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //实现方式1
        way01();
        //实现方式2
        way02();
        //实现方式3
        way03();
        //实现方式4
        way04();
        //实现方式5
        way05();
        //实现方式6
        way06();
        //实现方式7
        way07();
        //实现方式8
        way08();
        //实现方式9
        way09();
        //实现方式10
        way10();
        // 然后退出main线程
    }


    private static void way10(){
        long start = System.currentTimeMillis();
        CompletableFuture.supplyAsync(Homework::sum).thenAccept(v -> {
            System.out.println("way10异步计算结果为: " + v);
        }).join();
        System.out.println("way10主线程执行完成");
        System.out.println("way10使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static void way09() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Integer extResult = CompletableFuture.supplyAsync(Homework::sum).get();
        System.out.println("way09异步计算结果为：" + extResult);
        System.out.println("way09使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * 使用阻塞队列实现
     */
    private static void way08() {
        long start = System.currentTimeMillis();
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
        new Thread(() -> {
            try {
                queue.put(sum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 确保  拿到result 并输出
        try {
            System.out.println("way08异步计算结果为：" + queue.take());
            System.out.println("way08使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void way07() throws InterruptedException {
        long start = System.currentTimeMillis();
        CopyOnWriteArrayList<Integer> results = new CopyOnWriteArrayList<>();
        new Thread(() -> results.add(sum())).start();
        while (results.size() == 0) {
            Thread.sleep(40);
            System.out.println("way07主线程等待子线程执行结果");
        }
        // 确保  拿到result 并输出
        System.out.println("way07异步计算结果为：" + results.get(0));
        System.out.println("way07使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static void way06() throws InterruptedException {
        long start = System.currentTimeMillis();
        Object obj = new Object();
        CopyOnWriteArrayList<Integer> results = new CopyOnWriteArrayList<>();
        //启动一个线程
        new Thread(() -> {
            synchronized (obj){
                results.add(sum());
                obj.notifyAll();
            }
        }).start();

        synchronized (obj){
            if(results.size() == 0){
                obj.wait();
                System.out.println("way06主线程等待结果");
            }
            System.out.println("way06子线程执行完成,获取结果");
        }
        // 确保  拿到result 并输出
        System.out.println("way06异步计算结果为：" + results.get(0));
        System.out.println("way06使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static void way05() {
        long start = System.currentTimeMillis();
        CopyOnWriteArrayList<Integer> results = new CopyOnWriteArrayList<>();
        final Lock lock = new ReentrantLock();
        //子线程执行完成
        final Condition subTreadExeOver = lock.newCondition();
        //启动一个线程
        new Thread(() -> {
            lock.lock();
            try {
                results.add(sum());
                subTreadExeOver.signal();
            } finally {
                lock.unlock();
            }
        }).start();

        //主线程
        lock.lock();
        try {
            if (results.size() == 0) {
                subTreadExeOver.await();
                System.out.println("way05主线程等待子线程执行完成");
            }
            System.out.println("way05子线程执行完了,取结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        // 确保  拿到result 并输出
        System.out.println("way05异步计算结果为：" + results.get(0));
        System.out.println("way05使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static void way04() throws InterruptedException {
        long start = System.currentTimeMillis();
        CopyOnWriteArrayList<Integer> results = new CopyOnWriteArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                results.add(sum());
            }
        });
        thread.start();
        Thread.sleep(100);

        // 确保  拿到result 并输出
        System.out.println("way04异步计算结果为：" + results.get(0));
        System.out.println("way04使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }


    private static void way03() throws InterruptedException {
        long start = System.currentTimeMillis();
        CopyOnWriteArrayList<Integer> results = new CopyOnWriteArrayList<>();
        Thread thread = new Thread(() -> results.add(sum()));
        thread.start();
        thread.join();
        // 确保  拿到result 并输出
        System.out.println("way03异步计算结果为：" + results.get(0));
        System.out.println("way03使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * 第一种实现方式
     *
     * @return
     */
    private static void way01() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTask<>(Homework::sum);
        task.run();
        int result = task.get(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("way01异步计算结果为：" + result);
        System.out.println("way01使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static void way02() throws InterruptedException {
        long start = System.currentTimeMillis();
        CopyOnWriteArrayList<Integer> results = new CopyOnWriteArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            results.add(sum());
            latch.countDown();
        }).start();
        latch.await();
        // 确保  拿到result 并输出
        System.out.println("way02异步计算结果为：" + results.get(0));
        System.out.println("way02使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
