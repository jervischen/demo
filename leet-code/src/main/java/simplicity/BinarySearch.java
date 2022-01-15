package simplicity;

/**
 * @author Chen Xiao
 * 二分法查找
 * https://leetcode-cn.com/problems/binary-search/
 * @since 2021-09-15 11:36
 */
public class BinarySearch {


    public static void main(String[] args) {
        //除数
//        System.out.println(5 >> 1);
//        //余数
//        System.out.println(12 & (2 - 1));
//
//
//        System.out.println(500 >> 5);
//        System.out.println(15 << 5);
//        System.out.println(500 - 480);
//        System.out.println(500 % 32);
//


        int search = new BinarySearch().search(new int[]{-1, 0, 3, 5, 9, 12}, 9);
        System.out.println(search);

        int insert = new BinarySearch().searchAndInsert(new int[]{-1, 0, 3, 5, 9, 12}, 10);
        System.out.println(insert);
    }

    /**
     * 解法：
     *      左右各一个指针为中间移动
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0){
            return -1;
        }
//        int mid = nums.length >> 1;
//        int remainder = nums.length & (2 - 1);

        int left = 0; int right = nums.length - 1;
        while (left < right){
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                //说明 目标数 在左边
                right = mid-1;
            }else if(nums[mid] < target){
                //说明 目标数 在右边
                left = mid + 1;
            }
        }

        return -1;
    }


    /**
     * 解法：
     *     https://leetcode-cn.com/problems/search-insert-position/
     * @param nums
     * @param target
     * @return
     */
    public int searchAndInsert(int[] nums, int target) {
        if (nums.length == 0){
            return -1;
        }

        int left = 0; int right = nums.length - 1;
        while (left <= right){
            int mid = (right - left)/2 + left;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                //说明 目标数 在左边
                right = mid-1;
            }else if(nums[mid] < target){
                //说明 目标数 在右边
                left = mid + 1;
            }
        }

        return right + 1;
    }

}
