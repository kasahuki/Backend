package com.Senjay.threads;

public class MultiThreadDemo1 {
    public static void main(String[] args) {
        Dog cast1 = new Dog();
        System.out.println(Runtime.getRuntime().availableProcessors()); // 16核处理器
        cast1.start();// 开启线程 不会阻塞

       // 操作系统会交替执行这两个线程，具体的执行顺序和时间片分配由操作系统决定。
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
class Dog extends Thread {
    // 继承线程就表明是一个线程了

    @Override
    public void run() {
        int cnt =0;
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);// ms单位
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(cnt==20){
                break;
            }
        }


    }
}