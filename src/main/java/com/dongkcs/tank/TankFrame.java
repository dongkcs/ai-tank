package com.dongkcs.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 4:25
 * @description:
 */
public class TankFrame extends Frame {
    int x=200,y=200;
    public TankFrame() {
        setSize(800, 600);
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

    @Override
    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
        x+=50;
        y+=50;
    }
    class MyKeyListener extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("press");;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("release");
        }
    }
}

