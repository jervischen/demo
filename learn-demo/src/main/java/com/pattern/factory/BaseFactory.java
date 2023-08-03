package com.pattern.factory;

import com.beust.jcommander.internal.Maps;

import java.util.Map;

public abstract class BaseFactory {
    Map<String, Fruits> HANDLERS = Maps.newLinkedHashMap();

    /**
     * 注册所有handler
     */
    protected abstract void registerAllHandler();

    /**
     * 注册handler
     */
    protected void registerHandler(String t, Fruits h) {
        HANDLERS.put(t, h);
    }

    public Fruits getHandler(String t) {
        return HANDLERS.get(t);
    }
}