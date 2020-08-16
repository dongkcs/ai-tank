package com.dongkcs.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 4:10
 * @description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.valueOf((String) PropertyMgr.get("initTankCount"));
        for (int i=0;i<initTankCount;i++){
            tf.enemy.add(new Tank(50+i*80,200,Dir.DOWN,Group.BAD,tf));
        }

        while (true){
            Thread.sleep(25);
            tf.repaint();
        }
    }
}
