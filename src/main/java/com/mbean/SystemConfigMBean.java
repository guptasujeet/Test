package com.mbean;

public interface SystemConfigMBean {


    //Attributes

    int getNumberOfThread();

    void setNumberOfThread(int numberOfThread);

    String getConfigName();

    void setConfigName(String configName);


    //Operations

    void printName(String name);


}
