package com.example.demo.innerclass;

/**
 * Created in 2019-04-02 18:54.
 *
 * @author chenxiao
 */
public class TestInner {
    int i = 10;

    void init(){
        new Inner().print();
    }

    class Inner{
        void print(){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        TestInner ti = new TestInner();
        ti.init();
    }
}
