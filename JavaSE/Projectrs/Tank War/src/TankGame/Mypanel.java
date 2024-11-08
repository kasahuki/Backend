package TankGame;
import java.awt.*;
import javax.swing.*;
// 决定画什么内容
public class Mypanel extends JPanel {
    Hero hero = null;// 定义坦克
    public Mypanel() {
        hero = new Hero(100,100); // 初始化坦克对象

    }
    // 将画坦克封装为一个方法
    // 传入 坐标  朝向  画笔  阵营
    public void drawTank(int x, int y, int direction, Graphics g, int bloc) {
        if( bloc == 0) {
            g.setColor(Color.green);
        }
        else {
            g.setColor(Color.red);
        }
        switch (direction) {
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            default:
                System.out.println("暂时没有处理");
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750); // 对于画板(面板)界面：填充矩形 颜色默认为黑色
        drawTank(hero.getX(),hero.getY(),0, g,0);
        drawTank(hero.getX(),hero.getY()+100,0, g,1);
        // 注意·传递的参数



//        g.setColor(Color.white);
//        g.setFont(new Font("Times New Roman",Font.BOLD,50));
//        g.drawString("Game Over",350,100);
// paint 方法会自动执行

    }
}
