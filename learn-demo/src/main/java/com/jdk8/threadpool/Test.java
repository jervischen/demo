package com.jdk8.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2021-08-14 19:25
 */
public class Test {

    private static final ScheduledExecutorService delayExportExecutor = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        new Test().a();
    }

    public void a(){
        delayExportExecutor.schedule(this::doExport, 100, TimeUnit.MILLISECONDS);
    }

    private void doExport() {

    }

    @org.junit.Test
    public void b() throws ClassNotFoundException {
        Class.forName("com.jdk8.threadpool.Test");

    }


}
