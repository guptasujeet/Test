package com.design.pattern.proxy.jdk;

/**
 * Created by Sujeet on 16/06/18.
 */
public class ProxyDemo {

    public static void main(String[] args) {

        MyConfig config = ConfigFactory.getConfig(MyConfig.class);
        System.out.println(config);

        config = ConfigFactory.getConfig(MyConfig.class);
        System.out.println(config);

        YourConfig yourConfig = ConfigFactory.getConfig(YourConfig.class);
        System.out.println(yourConfig);

        yourConfig = ConfigFactory.getConfig(YourConfig.class);
        System.out.println(yourConfig);


    }

}
