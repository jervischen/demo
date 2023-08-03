package jmx;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXExample2 {
    public static void main(String[] args) throws Exception {
        // 启用 JMX
//        System.setProperty("com.sun.management.jmxremote", "true");
//        System.setProperty("com.sun.management.jmxremote.authenticate", "false");
//        System.setProperty("com.sun.management.jmxremote.port", "9999");
//        System.setProperty("java.rmi.server.hostname", "localhost");

        // 使用 JMX 客户端连接到本地 MBeanServer
        JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi"));
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        // 获取所有垃圾回收器的 ObjectName
        ObjectName gcObjectName = new ObjectName("java.lang:type=GarbageCollector,*");
        for (ObjectName name : connection.queryNames(gcObjectName, null)) {
            System.out.println("Garbage Collector Name: " + name);

            // 获取垃圾回收器的执行次数属性
            long totalGarbageCollections = (long) connection.getAttribute(name, "CollectionCount");
            System.out.println("  Total Garbage Collections: " + totalGarbageCollections);


            // 获取垃圾回收器的总执行时间属性
            long garbageCollectionTime = (long) connection.getAttribute(name, "CollectionTime");
            System.out.println("  Garbage Collection Time: " + garbageCollectionTime + " ms");
        }

        connector.close();
    }
}
