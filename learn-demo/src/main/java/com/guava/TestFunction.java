package com.guava;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.function.BinaryOperator;

/**
 * @author Chen Xiao
 * @since 2021-10-12 16:07
 */
public class TestFunction {
    public static void main(String[] args) {

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;

        Long apply = addExplicit.apply(1L, 2L);
        System.out.println(apply);


        String join = Joiner.on("_").join(1, 2, 3, 4, 5);
        System.out.println(join);
    }


    @Test
    public void testBossService(){
        // 1.无参无返回值
        IBossService boss = () -> System.out.println(" invoke m3");
        boss.m3();
        System.out.println(boss.m1());
        System.out.println(IBossService.m2());


        IBossService3<String,Integer> boss3 = (e)->"boss3返回m3" + e;
//        System.out.println(boss3.m3());

        System.out.println(boss3.m4(88));

    }
}
