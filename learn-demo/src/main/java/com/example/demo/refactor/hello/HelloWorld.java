package com.example.demo.refactor.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-11-04 15:30.
 *
 * @author chenxiao
 */
public class HelloWorld {
    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        MessagetBody mb = new MessagetBody();
        mb.configure("hello world");
        AbstractStrategyFactory asf = DefaultFactory.getInstance();
        MessageStrategy strategy = asf.createStrategy(mb);
        mb.send(strategy);
    }
}
