package com.example.modelmapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.modelmapper.model.Customer;
import com.example.modelmapper.model.Name;
import com.example.modelmapper.model.Order;
import com.example.modelmapper.model2.BaoFactory;
import com.example.modelmapper.model2.BenFactory;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * @author Chen Xiao
 * @since 2020-10-09 14:38
 */
public class MainTest {

    public static void main(String[] args) {
        Order order = new Order();

        ModelMapper modelMapper = new ModelMapper();
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        System.out.println(orderDTO);


        //*********model2***********//
        BenFactory benFactory = new BenFactory();
        BaoFactory baoFactory = modelMapper.map(benFactory, BaoFactory.class);
        System.out.println("奔驰的参数赋值给宝马  " + baoFactory);

        System.out.println(JSON.toJSONString(Lists.newArrayList()));

        for (Integer integer : new MainTest().a()) {
            System.out.println(integer);
        }
    }


    public int[] a() {
        return new int[]{1, 2, 3};
    }

    @Test
    public void b() {
        final MutablePair<Integer, Integer> result = MutablePair.of(0, 0);
        System.out.println(result.getLeft() + "_" + result.getRight());

        result.setLeft(1);
        result.setRight(1);
        System.out.println(result.getLeft() + "_" + result.getRight());
        cc("AA", 1, 2, 3);
    }

    private static void cc(String a, Object... obs) {
        System.out.println(a + "  " + obs);

        System.out.println(obs[0]);
        System.out.println(obs[1]);
        String join = Joiner.on("_").skipNulls().join(a, null, obs);
        System.out.println(join);


        JSONObject jsonObject = JSONObject.parseObject("{\"userIds\":[1,2,3],\"courseCode\":\"FINALS\"}");

        JSONArray userIds = jsonObject.getJSONArray("userIds");
        System.out.println(userIds);
    }

    @Test
    public void jj() {
        JSONObject jsonObject = JSONObject.parseObject("{\"userIds\":[\"1\",\"2\",\"3\"],\"courseCode\":\"FINALS\"}");
        System.out.println(jsonObject.containsKey("userIds"));
        JSONArray userIds = jsonObject.getJSONArray("userIds");

        System.out.println(userIds.getLong(0));
        System.out.println(userIds.contains(1));


    }

    @Test
    public void test() {

        List<OrderDTO> dtos = Lists.newArrayList();
        dtos.add(new OrderDTO().setScore(1).setCount(1));
        dtos.add(new OrderDTO().setScore(4).setCount(3));
        dtos.add(new OrderDTO().setScore(4).setCount(4));
        dtos.add(new OrderDTO().setScore(4).setCount(5));

        dtos = new Ordering<OrderDTO>() {
            @Override
            public int compare(OrderDTO left, OrderDTO right) {
                int value = right.getScore() - left.getScore();
                return value == 0 ? right.getCount() - left.getCount() : value;
            }
        }.immutableSortedCopy(dtos);

        System.out.println(dtos);

        System.out.println(Joiner.on("_").join(new Object[]{"A", 1}));
    }


    @Test
    public void test1() {
        ModelMapper modelMapper = new ModelMapper();
        Customer map = modelMapper.map(new Name(), Customer.class);
        System.out.println(map);
        int i = 100_111;
        System.out.println(i);
        System.out.println(2_014);

        System.out.println(new Name(){{getA();getB();}});
    }
}
