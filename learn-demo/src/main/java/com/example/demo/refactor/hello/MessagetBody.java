package com.example.demo.refactor.hello;

/**
 * Created in 2018-11-04 15:23.
 *
 * @author chenxiao
 */
public class MessagetBody {
    Object payLoad;

    public Object getPayLoad() {
        return payLoad;
    }

    public void configure(Object obj){
        this.payLoad = obj;
    }

    public void send(MessageStrategy ms){
        ms.sendMessage();
    }
}
