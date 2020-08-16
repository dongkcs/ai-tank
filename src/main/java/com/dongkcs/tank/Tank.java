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
    private Dir dir=Dir.DOWN;
    private static final int SPEED=10;

    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
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
