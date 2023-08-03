package coll;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TestQueue {

    public static void main(String[] args) {
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        pq.add(10);
//        pq.add(11);
//        pq.add(12);
//        pq.add(13);
//
//        while (pq.size()>0){
//            System.out.println(pq.poll());
//        }
    }

    @Test
    public void testLinkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("2", "b");
        map.put("3", "c");
        map.put("1", "a");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
    }

    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue(3);
        q.put("a");
        q.put("b");
        q.put("c");
        q.put("d");

        for (String s : q) {
            System.out.println(s);
        }

    }

    @Test
    public void testConcurrentHashMap() throws InterruptedException {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        map.put("a","b");

    }
}
