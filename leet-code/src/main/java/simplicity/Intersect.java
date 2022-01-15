package simplicity;

import org.junit.Test;

import java.util.*;

/**
 * @author Chen Xiao
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @since 2021-10-20 12:1
 */
public class Intersect {

    public static void main(String[] args) {

        int[] ints = new Intersect().intersectMap(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        for (int anInt : ints) {
            System.out.print(anInt +" ");
        }
        System.out.println();
        ints = new Intersect().intersectMap(new int[]{4,9,5}, new int[]{9,4,9,8,4});
        for (int anInt : ints) {
            System.out.print(anInt +" ");
        }
    }


    public int[] intersectMap(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i : nums1) {
            Integer integer = map.get(i);
            map.put(i, integer == null ? 1 : integer + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int j : nums2) {
            Integer integer = map.get(j);
            if (integer != null && integer != 0) {
                list.add(j);

                map.put(j, integer - 1);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }


    public int[] intersectSort(int[] nums1, int[] nums2) {
        // 先对两个数组进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                // 如果i指向的值小于j指向的值，，说明i指向
                // 的值小了，i往后移一步
                i++;
            } else if (nums1[i] > nums2[j]) {
                // 如果i指向的值大于j指向的值，说明j指向的值
                // 小了，j往后移一步
                j++;
            } else {
                // 如果i和j指向的值相同，说明这两个值是重复的，
                // 把他加入到集合list中，然后i和j同时都往后移一步
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        //把list转化为数组
        int index = 0;
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[index++] = list.get(k);
        }

//        Arrays.copyOfRange()
        return res;
    }

    @Test
    public void test(){
        System.out.println(2<10);
    }
}
