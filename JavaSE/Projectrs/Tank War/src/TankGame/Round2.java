package TankGame;

import javax.swing.*;

public class Round2 extends JFrame {
    private Mypanel mp = null; // 从包中拿另一个类（public）
    public static void main(String[] args) {

        Round2 round2 = new Round2();

    }
    public Round2() {
        mp = new Mypanel(); // 实例化面板
        this.add(mp); // 窗口中填放画板
        this.addKeyListener(mp);
        this.setSize(1000, 750); // 设定窗口大小
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
