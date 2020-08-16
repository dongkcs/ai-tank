package com.dongkcs.tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.awt.*;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 15:14
 * @description:
 */
@Data
@AllArgsConstructor
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;
    private TankFrame tf= null;

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);
        move();
    }

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    private void move() {
        if(!moving){return;}
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

    public void fire() {
        tf.b= new Bullet(x,y,dir);
    }
}
