package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * <p>
 * 示例:
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-range-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/10
 * @Version 1.0
 */
public class Number0327_CountOfRangeSum {
    @Test
    public void solution() {
        int[] nums = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        int expected = 3;
        int result = countRangeSum(nums, lower, upper);
        Assert.assertEquals(expected, result);
    }

    /**
     * 怎么优化呢？前缀和？
     *
     * @param nums  int[]
     * @param lower int
     * @param upper int
     * @return int
     */
    private int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        int[] pre = new int[len + 1];
        pre[0] = nums[0];
        for (int i = 0; i < len; ++i) {
            pre[i + 1] = pre[i] + nums[i];
        }
        //问题归结为前缀和之差在lower和upper之间

        return 0;
    }

    private int countRangeSumOne(int[] nums, int lower, int upper) {
        //区间组合能有哪些？
        //暴力法
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            long num = 0;
            for (int j = i; j < nums.length; ++j) {
                num += nums[j];
                if (num <= upper && num >= lower) {
                    result++;
                }
            }
        }
        return result;
    }
}
