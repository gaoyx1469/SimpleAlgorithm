package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/23
 * @Version 1.0
 */
public class Number1248_CountNumberOfNiceSubarrays {
    @Test
    public void solution() {
        int[][] nums = {{1, 1, 2, 1, 1}, {2, 4, 6}, {2, 2, 2, 1, 2, 2, 1, 2, 2, 2}};
        int[] k = {3, 1, 2};
        int[] expected = {2, 0, 16};
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; ++i)
            result[i] = countNumberOfNiceSubarrays(nums[i], k[i]);
        Assert.assertArrayEquals(expected, result);
    }

    /**
     * 包含k个奇数的连续子数组
     *
     * @param nums int[]
     * @param k    int
     * @return int
     */
    private int countNumberOfNiceSubarrays(int[] nums, int k) {
        int result = 0;
        Deque<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int num : nums) {
            if (num % 2 == 0)//偶数
                ++count;
            else {//奇数
                queue.addFirst(count);
                count = 0;
                if (queue.size() == k + 1) {
                    result += (queue.peekFirst() + 1) * (queue.pollLast() + 1);
                }
            }
        }
        queue.addFirst(count);
        if (queue.size() == k + 1) {
            result += (queue.peekFirst() + 1) * (queue.pollLast() + 1);
        }
        return result;
    }
}
