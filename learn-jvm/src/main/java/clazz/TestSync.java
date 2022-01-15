package clazz;

/**
 * @author Chen Xiao
 * @since 2021-09-27 13:22
 */
public class TestSync {

    private final Object obj = new Object();

    public static void main(String[] args) {

        new TestSync().syncObj();

    }

    public void syncObj() {
        synchronized (this.obj) {
            System.out.println(1);
        }
    }
}
