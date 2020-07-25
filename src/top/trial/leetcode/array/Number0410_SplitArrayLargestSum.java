package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 * <p>
 * 注意:
 * 数组长度 n 满足以下条件:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 * <p>
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * 输出:
 * 18
 * <p>
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/25
 * @Version 1.0
 */
public class Number0410_SplitArrayLargestSum {
    @Test
    public void solution() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        int expected = 18;
        //int result = splitArrayLargestSumOne(nums, m);
        int result = splitArrayLargestSumTwo(nums, m);
        Assert.assertEquals(expected, result);
    }

    /**
     * 参考官方题解，二分法查找
     *
     * @param nums
     * @param m
     * @return
     */
    private int splitArrayLargestSumTwo(int[] nums, int m) {

        int start = 0;
        int end = 0;
        for (int value : nums) {
            end += value;
            start = Math.max(start, value);
        }

        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 1;

            int sum = 0;
            for (int num : nums) {
                if (sum + num > mid) {
                    count++;
                    sum = num;
                } else {
                    sum += num;
                }
            }

            if (count > m) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    /**
     * 就是将nums数组分成m块，使得每块之和的最大值最小
     * 暴力法先不考虑
     * 根据官方题解：
     * 动态规划思路--dp[i][j]表示前i个数要分成j段，各子数组的最大值；则枚举k从1到i-1，MAX(dp[k][j-1],sum(k+1,i))的最小值就是dp[i][j]
     *
     * @param nums
     * @param m
     * @return
     */
    private int splitArrayLargestSumOne(int[] nums, int m) {

        int n = nums.length;
        int[][] result = new int[n + 1][m + 1];
        int[] sum = new int[n + 1];
        for (int i = 1; i < n + 1; ++i)
            sum[i] = nums[i - 1] + sum[i - 1];

        //全部填充最大值
        for (int i = 0; i < n + 1; ++i)
            Arrays.fill(result[i], Integer.MAX_VALUE);
        //填充边界值
        result[0][0] = 0;

        result[1][1] = nums[0];
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 1; j <= Math.min(i, m); ++j) {//超过m越界，超过i无法分割
                for (int k = 0; k < i; ++k) {
                    result[i][j] = Math.min(result[i][j], Math.max(result[k][j - 1], sum[i] - sum[k]));
                }
            }
        }
        return result[n][m];
    }
}
