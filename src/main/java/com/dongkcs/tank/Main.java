package com.dongkcs.tank;

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
        while (true){
            Thread.sleep(30);
            tf.repaint();
        }
    }
}
