package com.lzp;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = new TankFrame();

        for (int i = 0; i < 10; i++) {
            tankFrame.tanks.add(new Tank(20 + i * 2 + 50 * i, 20, Dir.DOWN, tankFrame, Group.BAD));
        }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
