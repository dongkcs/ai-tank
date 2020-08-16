package com.dongkcs.tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.awt.*;
import java.util.Random;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 15:14
 * @description:
 */
@Data
@AllArgsConstructor
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 1;

    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private boolean moving = true;
    private boolean living = true;
    private Group group= Group.BAD;
    private TankFrame tf = null;
    private Random random=new Random();

    public Tank(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf = tf;

    }

    public void paint(Graphics g) {
        if (!living) {
            tf.enemy.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }



    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if(random.nextInt()>8){
            this.fire();
        }
    }

    public void fire() {

        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group,this.tf));
    }

    public void die() {
        this.living = false;
    }
}
