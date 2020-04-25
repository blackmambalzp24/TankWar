package com.lzp;

import java.awt.*;
import java.util.Random;

public class Tank {

    public static final int WIDTH = ResourceMgr.badTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.badTankD.getHeight();
    private Rectangle rectangle = new Rectangle();
    private int x, y;
    public static final int SPEED = 5;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    private Dir dir = Dir.DOWN;
    private boolean isMoving = true;//是否可以移动了
    private TankFrame tf = null;
    private boolean isLive = true;
    private Group group;
    private Random random = new Random();

    public void setX(int x) {
        this.x = x;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rectangle = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!isLive) {
            tf.tanks.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

        move();

    }

    private void move() {
        if (!this.isMoving) return;
        switch (dir) {
            case UP:
                y -=SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        boundCheck();

        rectangle.x = this.x;
        rectangle.y = this.y;

    }

    private void boundCheck() {
        if (this.x < 0) x = 2;
        if (this.y < 30) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.HEIGHT) this.x = TankFrame.GAME_WIDTH - Tank.HEIGHT - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf, this.group));

        if (this.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        isLive = false;
    }
}
