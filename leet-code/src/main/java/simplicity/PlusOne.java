package simplicity;

/**
 * @author Chen Xiao
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @since 2021-10-22 16:24
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] ints = new PlusOne().plusOne(new int[]{1, 2, 3});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
        System.out.println();
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            }

            digits[i] = 0;
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }
}
