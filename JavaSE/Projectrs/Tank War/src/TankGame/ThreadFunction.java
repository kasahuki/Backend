package TankGame;

public class ThreadFunction {
    public static void main(String[] args) {

        Thread T1 = new Thread(new thread1());
        T1.setName("Luna Freya");// 线程改名
        System.out.println(T1.getName());
//        T1.start();
//        T1.setPriority(10);
        System.out.println(T1.getPriority());
        T1.interrupt();
    }
}
class thread1 implements Runnable {
    int n=10;
    @Override
    public void run() {
        while(n-->0)
        {
            System.out.println(n);
        }
    }
}
