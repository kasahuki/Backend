package draw;
import javax.swing.*;
import java.awt.*;
// 导包和包路径 放置顺序注意！！！

public class DrawCircle extends JFrame{
    private Mypanel mp = null;// 定义一个画板
    public DrawCircle(){// 构造器
        mp = new Mypanel();
        this.add(mp);//把画板放到框架窗口中
        this.setSize(1000,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口后结束程序的执行
        // 设置框架的属性
    }
    public static void main(String[] args) { // 主方法
        new DrawCircle(); // 一new （创建）就会调用构造方法

    }
}
class Mypanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(10,10,100,100); // 起始点从圆的外切正方形的左上角顶点开始！
        g.drawLine(10,10,100,100);
        g.setColor(Color.red); //设置画笔颜色
//        g.setFont(new Font("隶书",Font.BOLD,50)); // 设置画笔字体

//        g.drawString("lunaFreya",100,100);
//        Image img = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("image/Freya.png")); src:引入图片写法
//        g.drawImage(img, 10, 10, 500,500,this);



    }
}