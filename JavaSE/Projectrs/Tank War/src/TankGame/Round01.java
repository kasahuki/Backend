package TankGame;

import javax.swing.*;

public class Round01 extends JFrame { // 框架放置画板的
    private Mypanel mp = null; // 从包中拿另一个类（public）
    public static void main(String[] args) {

        Round01 round01 = new Round01();

    }
    public Round01() {
        mp = new Mypanel(); // 实例化面板
        this.add(mp); // 窗口中填放画板
        this.addKeyListener(mp);// 在窗口设置监听器而不是画板
//        addKeyListener 是 JFrame 的方法，用于为 JFrame 注册键盘事件监听器。
//
//        mp 是 KeyListener 的实现类（假设 Mypanel 实现了 KeyListener 接口），用于处理键盘事件。
        // 也就是说事件源是窗口 --> 监听器是画板 mp实现监听器接口 编写处理逻辑
        // 事件源是窗口 --> 监听器是画板
        this.setSize(1000, 750); // 设定窗口大小
        this.setVisible(true);// 可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗口不可视化后结束程序

    }

}
