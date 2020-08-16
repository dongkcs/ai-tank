package com.dongkcs.tank;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 15:45
 * @description:
 */
@Data
@AllArgsConstructor
public class Bullet {
    private static final int SPEED=1;
    private static int WIDTH=30,HEIGHT=30;
    private int x,y;
    private Dir dir;

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();
    }

    private void move() {
        switch(dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            default:
                break;
        }
    }
}
