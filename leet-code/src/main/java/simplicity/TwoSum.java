package simplicity;

import java.util.HashMap;

/**
 * @author Chen Xiao
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2jrse/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @since 2021-10-23 22:41
 */
public class TwoSum {


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        for (int num : new TwoSum().twoSum(nums, 9)) {
            System.out.print(num + " ");
        }
    }

    /**
     * a+b = c
     * a = c -b
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer integer = map.get(target - nums[i]);
            if (integer != null) {
                return new int[]{i, integer};
            }
            map.put(nums[i], i);

        }

        return null;
    }
}
