package bug;

import java.io.Serializable;

/**
 * @author chenxiao
 */
public class Evil implements Serializable {
    public String cmd;
    public Evil(){
        System.out.println(11);
    }
    private void readObject(java.io.ObjectInputStream stream) throws Exception {
        stream.defaultReadObject();
//        Runtime.getRuntime().exec(cmd);
        System.out.println(cmd);
    }
}
