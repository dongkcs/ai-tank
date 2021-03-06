package com.dongkcs.tank;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    private static final int SPEED = 2;

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    Rectangle rect=new Rectangle();

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

        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;

    }

    public void paint(Graphics g) {
        if (!living) {
            tf.enemy.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD, x, y, null);
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

        if(this.group==Group.BAD && random.nextInt(100)>98){
            this.fire();
        }
        if(this.group==Group.BAD && random.nextInt(100)>98) {
            randomDir();
        }
        boundsCheck();
        //update rect
        rect.x=this.x;
        rect.y=this.y;
    }

    private void boundsCheck() {
        if(this.x <2) {x=2;}
        if(this.y <28){y=28;}
        if(this.x >TankFrame.GAME_WIDTH-Tank.WIDTH-2){y=TankFrame.GAME_WIDTH-Tank.WIDTH-2;}
        if(this.y >TankFrame.GAME_HEIGHT-Tank.HEIGHT-2){y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;}
    }

    private void randomDir() {
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public void fire() {

        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group,this.tf));
        if(this.group==Group.GOOD){
            new Thread(()->new Audio("audio/tankFire.wav").play()).start();
        }
    }

    public void die() {
        this.living = false;
    }
}
