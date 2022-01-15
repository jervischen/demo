package com.example.demo.refactor.hello;

/**
 * Created in 2018-11-04 15:25.
 *
 * @author chenxiao
 */
public class DefaultFactory extends AbstractStrategyFactory {

    static DefaultFactory instance;

    private DefaultFactory() {

    }


    public static AbstractStrategyFactory getInstance() {
        if (instance == null) {
            instance = new DefaultFactory();
        }
        return instance;
    }

    @Override
    public MessageStrategy createStrategy(final MessagetBody mb) {
        return new MessageStrategy() {
            MessagetBody body = mb;

            @Override
            public void sendMessage() {
                Object obj = body.getPayLoad();
                System.out.println(obj);
            }
        };
    }


}
