package jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;

public class JMXExample {
    public static void main(String[] args) throws Exception {
        // 启用 JMX
//        System.setProperty("com.sun.management.jmxremote", "true");
//        System.setProperty("com.sun.management.jmxremote.authenticate", "false");
//        System.setProperty("com.sun.management.jmxremote.port", "9999");
//        System.setProperty("java.rmi.server.hostname", "localhost");


        // 创建 MBeanServer
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        // 创建 MBean
        HelloMBean helloMBean = new Hello();
        ObjectName objectName = new ObjectName("jmx:type=Hello");

        // 注册 MBean
        mBeanServer.registerMBean(helloMBean, objectName);

        // 启动一个线程，每秒钟修改 MBean 的属性值
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    helloMBean.setMessage("Hello, " + System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 使用 JMX 客户端获取和设置 MBean 的属性
        JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi"));
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        // 在一个循环中获取并打印 MBean 的属性值
        while (true) {
            try {
                Object val = connection.getAttribute(objectName, "Message");
                System.out.println("Mbean Message: " + val);
                System.out.println("java bean: " + helloMBean.getMessage());
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public interface HelloMBean {
        void setMessage(String message);

        String getMessage();
    }

    public static class Hello implements HelloMBean {
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
