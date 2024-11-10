package TankGame;

public class Tanks {
    private int x;
    private int y;
    private int direction;
    private int speed =10;
    // 坦克的横纵坐标


    public Tanks(int x, int y) {
        this.x = x;
        this.y = y;

    }
    // 初始化
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void moveUp() {
        this.y -= speed;
    }
    public void moveDown() {
        this.y += speed;
    }
    public void moveLeft() {
        this.x -= speed;
    }
    public void moveRight() {
        this.x += speed;
    }

}
