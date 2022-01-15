package simplicity;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Chen Xiao
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @since 2021-10-19 16:49
 */
public class Rotate {

    /**
     * 答案： https://leetcode-cn.com/problems/rotate-array/solution/
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        new Rotate().rotate(ints, 4);

        for (int anInt : ints) {
            System.out.print(anInt);
            System.out.print(" ");
        }
    }

    /**
     * 数组反转
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
//        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    /**
     * 做不出来
     *
     * @param nums [1,2,3,4,5,6,7],
     * @param k    k = 3 右移位数
     */
    public void myRotate(int[] nums, int k) {
        int hold = nums[0];
        int index = 0;
        int length = nums.length;
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            index = (index + k) % length;
            //把当前值保存在下一个位置，保存之前要把下一个位置的
            //值给记录下来
            visited[index] = true;
            int temp = nums[index];
            nums[index] = hold;
            hold = temp;
        }
    }

    @Test
    public void test() {
        System.out.println(3 % 7);
    }

    @Test
    public void sysArryTest() {
        int[] nums = {1, 2, 3, 4, 5};

        int[] deNums = new int[8];
        System.arraycopy(nums, 0, deNums, 1, 5);
        for (int deNum : deNums) {
            System.out.print(deNum + " ");
        }

        System.out.println();
        int[] rightpart = Arrays.copyOfRange(nums, 1, nums.length);
        for (int i : rightpart) {
            System.out.print(i + " ");
        }
    }
}
