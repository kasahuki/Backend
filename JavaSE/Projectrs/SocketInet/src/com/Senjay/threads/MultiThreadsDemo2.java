package com.Senjay.threads;

public class MultiThreadsDemo2 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread); // 把实现了Runnable接口的mythread 对象放入thread的构造函数中
        thread.start(); // thread 代理对象 myThread 表示被代理的对象

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

}
class MyThread implements Runnable { // 实现接口
    @Override
    public void run() {
        int cnt = 0;
        while(true)
        {
            System.out.println(Thread.currentThread().getName() + "第" + ++cnt  + "次调用"  );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
