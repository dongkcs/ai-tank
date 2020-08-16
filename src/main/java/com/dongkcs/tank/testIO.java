package com.dongkcs.tank;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/16 18:34
 * @description:
 */
public class testIO {

    @Test
   public void test(){
        try {
//            BufferedImage image = ImageIO.read(new File("E:\\test2\\tankL.gif"));
            BufferedImage image = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            System.out.println("111");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
