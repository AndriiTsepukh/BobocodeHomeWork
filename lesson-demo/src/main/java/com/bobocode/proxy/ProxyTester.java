package com.bobocode.proxy;

public class ProxyTester {

    @LogInvocation
    public void test() {
        System.out.println("Test method called");
    }

    public void test2() {
        System.out.println("Test2 method called");
    }
}
