package unsafe;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Slf4j
public class TestUnsafe {


    public static void main(String[] args) {
        Unsafe unsafe = reflectGetUnsafe();
        System.out.println(unsafe);

//        Unsafe.getUnsafe();
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Test
    public void staticTest() throws Exception {
        Unsafe unsafe = reflectGetUnsafe();
//        User user=new User();
        System.out.println(unsafe.shouldBeInitialized(User.class));
        Field sexField = User.class.getDeclaredField("name");
        long fieldOffset = unsafe.staticFieldOffset(sexField);
        Object fieldBase = unsafe.staticFieldBase(sexField);
        Object object = unsafe.getObject(fieldBase, fieldOffset);
        System.out.println(object);
    }

    @Test
    public void staticClass() throws Exception {
//        Unsafe unsafe = reflectGetUnsafe();
//        User user = (User)unsafe.allocateInstance(User.class);
//        System.out.println(user.getAge());


    }
}
