package com.dongkcs.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author dongkcs
 * @version 1.0
 * @date 2020/8/17 1:24
 * @description:
 */


public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if(props == null) {
            return null;
        }
        return props.get(key);
    }

    //int getInt(key)
    //getString(key)

    public static void main(String[] args) {
        PropertyMgr p= new PropertyMgr();
        System.out.println(PropertyMgr.get("initTankCount"));

    }
}
