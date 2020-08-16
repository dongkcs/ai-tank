package com.dongkcs.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 18:06
 * @description:
 */
public class ResourceMgr {
    public   static BufferedImage tankL,tankU,tankR,tankD;
    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
