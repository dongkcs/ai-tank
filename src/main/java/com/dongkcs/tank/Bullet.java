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
    private static final int SPEED=10;
    public static int WIDTH=ResourceMgr.bulletD.getWidth();
    public static int HEIGHT=ResourceMgr.bulletD.getHeight();

    Rectangle rect=new Rectangle();
    private int x,y;
    private Dir dir= Dir.DOWN;
    private TankFrame tf= null;
    private  boolean living = true;
    private Group group= Group.BAD;

    public Bullet(int x, int y, Dir dir,Group group, TankFrame tf) {
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
        if(!living){
            tf.bullets.remove(this);
        }

        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            default:
                break;
        }
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIDTH,HEIGHT);
//        g.setColor(c);
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
        rect.x=this.x;
        rect.y=this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group== tank.getGroup()){
            return;
        }
        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();

            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(new Explode(eX,eY,tf));
//            new Thread(()->new Audio("audio/explode.wav").play()).start();
        }

    }

    private void die() {
        this.living=false;
    }
}
