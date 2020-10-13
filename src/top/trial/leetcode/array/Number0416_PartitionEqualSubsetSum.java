package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * <p>
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/11
 * @Version 1.0
 */
public class Number0416_PartitionEqualSubsetSum {
    @Test
    public void solution() {
        int[] nums = {1, 5, 11, 5};
        Assert.assertTrue(canPartitionOne(nums));
    }

    private boolean canPartitionOne(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return false;

        int max = nums[0];
        //求数组和，找到加和为总和一半的组合即可
        int sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % 2 == 1)
            return false;
        int target = sum / 2;
        if (max > target)
            return false;
        else if (max == target)
            return true;

        //此处找总和为sum/2的子集
        return canPartitionXX(nums, target);
    }

    /**
     * 将canPartitionX的二维数组改为一维数组
     *
     * @param nums   int[]
     * @param target target
     * @return boolean
     */
    private boolean canPartitionXX(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];

    }

    /**
     * 数组中找和为target的子集，找到返回true，没有返回false
     *
     * @param nums   int[]
     * @param target target
     * @return boolean
     */
    private boolean canPartitionX(int[] nums, int target) {
        int len = nums.length;
        boolean[][] dp = new boolean[len][target + 1];//二维数组，第一维是限定从nums的前多少个数中取值，第二位是要凑成的总和是多少，最终结果取dp[len-1][target]
        for (int i = 0; i < len; i++) {//第二维是0，即凑成总和是0的，肯定可以成功，因此全部赋值为true
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;//第一维是0，即只可以选nums[0]这一个数，因此当总结是nums[0]时，置为true
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len - 1][target];

    }
}
