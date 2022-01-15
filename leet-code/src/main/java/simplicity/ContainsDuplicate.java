package simplicity;

import java.util.HashSet;

/**
 * @author Chen Xiao
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/
 * @since 2021-10-19 21:08
 */
public class ContainsDuplicate {

    public static void main(String[] args) {

    }


    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set  = new HashSet<>();
        for (int num : nums) {

            boolean add = set.add(num);
            if (!add){
                return true;
            }
        }

        return false;
    }
}
