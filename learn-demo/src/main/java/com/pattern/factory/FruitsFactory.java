package com.pattern.factory;

public class FruitsFactory extends BaseFactory {

    public FruitsFactory(){
        registerAllHandler();
    }

    @Override
    protected void registerAllHandler() {
        this.registerHandler("apple", new Apple());
        this.registerHandler("orange", new Orange());
    }

}
