package com.mbean;

public class SystemConfig implements SystemConfigMBean {

    private int numberOfThread;
    private String name = "DefaultConfig";


    @Override
    public int getNumberOfThread() {
        System.out.println("Returned NumberOfThread : " + numberOfThread);
        return numberOfThread;
    }

    @Override
    public void setNumberOfThread(int numberOfThread) {
        System.out.println("Set NumberOfThread : " + numberOfThread);
        this.numberOfThread = numberOfThread;
    }

    @Override
    public String getConfigName() {
        System.out.println("Returned ConfigName : " + name);
        return name;
    }

    @Override
    public void setConfigName(String configName) {
        System.out.println("Set ConfigName : " + configName);
        name = configName;
    }

    @Override
    public void printName(String name) {
        System.out.println("Received Input : , Hello " + name);
    }
}
