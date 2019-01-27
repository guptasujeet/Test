package com.mbean;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.CountDownLatch;

public class MBeanDemo {


    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {

        System.out.println("Program Started");


        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        SystemConfig systemConfig = new SystemConfig();

        ObjectName objectName = new ObjectName("com.mbean:type=SystemConfigMBean");
        ObjectInstance objectInstance = mBeanServer.registerMBean(systemConfig, objectName);


        System.out.println("Open JConsole and Connect to MBeanDemo ");
        System.out.println("Go to MBean tab and select SystemConfigMBean instance\n");


        CountDownLatch latch = new CountDownLatch(1);
        latch.await();


    }
}
