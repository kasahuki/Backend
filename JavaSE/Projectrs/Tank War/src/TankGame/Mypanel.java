package TankGame;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;

// 决定画什么内容
public class Mypanel extends JPanel implements KeyListener {
    // 实现键盘事件监听接口 --> 重写其方法（规范性）
    Hero hero = null;// 定义坦克
    Vector<enemyTank> enemyTanks = new Vector<>();
    // 创建一个集合
    int enemyTankSize = (int) (10 * (Math.random()));

    //变量 默认是包访问权限
    public Mypanel() {
        hero = new Hero(100, 100);
        // 初始化坦克对象
        hero.setDirection(2);
        // 没有必要封装到hero里 , 直接调用方法改变/获取值
        for (int i = 0; i < enemyTankSize; i++) {
            enemyTank e = new enemyTank(100 * (i + 1), 0);
            e.setDirection((int) (Math.random() * 4));// 随机方向
            enemyTanks.add(e);

        } // 初始化坦克集合2


    }

    // 将画坦克封装为一个方法
    // 传入 坐标  朝向  画笔  阵营
    public void drawTank(int x, int y, int direction, Graphics g, int bloc) {
        if (bloc == 0) {
            g.setColor(Color.green);
        } else {
            g.setColor(Color.red);
        }
        switch (direction) {
            case 0: // 向上
                g.fill3DRect(x, y, 10, 60, false); // 左
                g.fill3DRect(x + 30, y, 10, 60, false); // 右
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间
                g.fillOval(x + 10, y + 20, 20, 20); // 圆盖
                g.drawLine(x + 20, y + 30, x + 20, y); // 炮筒
                break;
            case 1: // 向右
                g.fill3DRect(x, y, 60, 10, false); // 上
                g.fill3DRect(x, y + 30, 60, 10, false); // 下
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 中间
                g.fillOval(x + 20, y + 10, 20, 20); // 圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20); // 炮筒
                break;
            case 2: // 向下
                g.fill3DRect(x, y, 10, 60, false); // 左
                g.fill3DRect(x + 30, y, 10, 60, false); // 右
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间
                g.fillOval(x + 10, y + 20, 20, 20); // 圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // 炮筒
                break;
            case 3: // 向左
                g.fill3DRect(x, y, 60, 10, false); // 上
                g.fill3DRect(x, y + 30, 60, 10, false); // 下
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 中间
                g.fillOval(x + 20, y + 10, 20, 20); // 圆盖
                g.drawLine(x + 30, y + 20, x, y + 20); // 炮筒
                break;
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // 对于画板(面板)界面：填充矩形 颜色默认为黑色
        drawTank(hero.getX(), hero.getY(), hero.getDirection(), g, 0);
        for (int i = 0; i < enemyTankSize; i++) {
            enemyTank e = enemyTanks.get(i);
            // 集合方法
            drawTank(e.getX(), e.getY(), e.getDirection(), g, 1);
        }
        // 把绘画部分封装成一个方法后调用即可
        // 注意·传递的参数
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if ((char) e.getKeyCode() == 'W') {
            hero.setDirection(0);
            hero.moveUp();
            // 继承了tank 就可以直接使用tank的方法了
        }
        if ((char) e.getKeyCode() == 'D') {
            hero.setDirection(1);
            hero.moveRight();

        }
        if ((char) e.getKeyCode() == 'S') {
            hero.setDirection(2);
            hero.moveDown();
        }
        if ((char) e.getKeyCode() == 'A') {
            hero.setDirection(3);
            hero.moveLeft();

        }

        this.repaint();
        // 重绘
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
