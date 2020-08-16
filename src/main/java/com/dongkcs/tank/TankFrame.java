package com.dongkcs.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 4:25
 * @description:
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200,400,Dir.DOWN,this);
    List <Bullet> bullets =new ArrayList<>();
    List<Tank> enemy = new ArrayList<>();
    Bullet b= new Bullet(300, 300,Dir.DOWN,this);
    static final int GAME_WIDTH=800,GAME_HEIGHT=600;
    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
    }
    Image offScreenImage=null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量"+bullets.size(),10,60);
        g.drawString("敌人的数量"+enemy.size(),10,80);
        g.setColor(c);
        myTank.paint(g);
        for(int i = 0;i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }
        for(int i = 0;i < enemy.size(); i++){
            enemy.get(i).paint(g);
        }
        for(int i = 0;i < bullets.size(); i++){
            for (int j = 0; j < enemy.size(); j++) {
                bullets.get(i).collideWith(enemy.get(j));
            }
        }
//        for(Iterator<Bullet>it = bullets.iterator();it.hasNext();){
//            Bullet b = it.next();
//            if(!b.isLive()){
//                it.remove();
//            }
//        }
    }
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTrankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    setMainTrankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    setMainTrankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    setMainTrankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    setMainTrankDir();
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTrankDir();
        }
        private void setMainTrankDir() {

            if(!bL && !bU && !bR && !bD){
                myTank.setMoving(false);

            }else {

                if(bL)
                {
                    myTank.setDir(Dir.LEFT);
                }
                if(bU)
                {
                    myTank.setDir(Dir.UP);
                }
                if(bR)
                {
                    myTank.setDir(Dir.RIGHT);
                }
                if(bD)
                {
                    myTank.setDir(Dir.DOWN);
                }
                myTank.setMoving(true);
            }
        }
    }


}

