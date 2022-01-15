package simplicity;

/**
 * @author Chen Xiao
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * @since 2021-10-20 12:11
 */
public class SingleNumber {


    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[]{4,1,2,1,2}));
    }

    public int singleNumber(int[] nums) {
        int reduce = 0;
        for (int num : nums) {
            reduce =  reduce ^ num;
        }
        return reduce;
    }

}
