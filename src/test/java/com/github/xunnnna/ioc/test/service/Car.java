package com.github.xunnnna.ioc.test.service;

/**
 * Created by zhutingxuan on 2020/9/2.
 */
public class Car {
    private String color;

    private Wheel wheel;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "com.github.xunnnna.ioc.test.service.Car{" +
                "color='" + color + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
