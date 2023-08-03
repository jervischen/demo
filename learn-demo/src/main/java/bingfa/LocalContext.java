package bingfa;

public class LocalContext {

    public final static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void set(String str){
        tl.set(str);
    }

    public static String get(){

        return tl.get();
    }
}
