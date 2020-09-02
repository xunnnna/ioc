package com.github.xunnnna.ioc.test.service;

/**
 * Created by zhutingxuan on 2020/9/2.
 */
public class Wheel {
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "com.github.xunnnna.ioc.test.service.Wheel{" +
                "amount=" + amount +
                '}';
    }
}
