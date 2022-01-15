package com.example.mock;


import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Chen Xiao
 * @since 2021-05-23 13:21
 */
public class MockTest {

    @Test
    public void mockA() throws IOException {
        //创建mock对象，mock一个List接口
        //List mockedList = mock(List.class);
        //如果不使用静态导入,则必须使用Mockito调用
        LinkedList mockedList = mock(LinkedList.class);



        mockedList.add("one");
        //验证
        verify(mockedList).add("one");

        when(mockedList.get(0)).thenReturn("11");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        System.out.println("aa");
        System.in.read();
    }

}
