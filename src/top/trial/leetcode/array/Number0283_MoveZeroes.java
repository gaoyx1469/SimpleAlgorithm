package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/19
 * @Version 1.0
 */
public class Number0283_MoveZeroes {
    @Test
    public void solution() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeros(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * @param nums int[]
     */
    private void moveZeros(int[] nums) {
        int len = nums.length;
        int startIndex = -1;//0的起始位置，-1代表没有

        for (int i = 0; i < len; ++i) {
            if (nums[i] != 0 && startIndex != -1) {
                nums[startIndex++] = nums[i];
                nums[i] = 0;
            } else if (nums[i] == 0 && startIndex == -1) {
                startIndex = i;
            }
        }
    }
}
