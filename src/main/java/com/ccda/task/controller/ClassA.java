package com.ccda.task.controller;

public class ClassA {
    public static void main(String[] args) {
        // 创建ClassB的对象
        HelloController hello = new HelloController();

        // 调用ClassB的printMessage方法
        hello.hello();
    }
}