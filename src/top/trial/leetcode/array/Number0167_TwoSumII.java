package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/20
 * @Version 1.0
 */
public class Number0167_TwoSumII {
    @Test
    public void solution() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {1, 2};
        int[] result = twoSumII(nums, target);
        Assert.assertArrayEquals(expected, result);
    }

    /**
     * 双指针？过于简单了吧
     *
     * @param nums   原始数组
     * @param target 目标值
     * @return 下标
     */
    private int[] twoSumII(int[] nums, int target) {

        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            if (nums[start] + nums[end] > target)
                --end;
            else if (nums[start] + nums[end] < target)
                ++start;
            else
                break;
        }

        return new int[]{start + 1, end + 1};
    }
}
