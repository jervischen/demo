package com.example.thriftdemo;


import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService.Iface {
    public HelloWorldImpl() {
    }

    @Override
    public String sayHello(String name) throws TException {
        return "Hello, " + name;
    }
}