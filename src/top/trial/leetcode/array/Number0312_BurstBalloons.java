package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 说明:
 * <p>
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <p>
 * 示例:
 * <p>
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/19
 * @Version 1.0
 */
public class Number0312_BurstBalloons {
    @Test
    public void solution() {
        int[] nums = {3, 1, 5, 8};
        int expected = 167;
        //int result = burstBalloonsOne(nums);
        int result = burstBalloonsTwo(nums);
        Assert.assertEquals(expected, result);
    }

    /**
     * 动态规划
     * 变戳气球为填气球，去除重复计算？
     *
     * @param nums 气球数组
     * @return int
     */
    private int burstBalloonsTwo(int[] nums) {

        int n = nums.length;
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1;
        for (int i = 0; i < n; i++)
            newNums[i + 1] = nums[i];
        //results[i][j]代表往（i,j）之间填充数字，i< j-1，能获得的最大硬币数量，约束方程是dp[i][j] = max(dp[i][k]+dp[k][j]+newNums[k]*newNums[i]*newNums[j])
        int[][] results = new int[n + 2][n + 2];
        //dp[i][j]与dp[i][k]和dp[k][j]有关，k的范围是i+1到j-1，k比i大，k比j小，因此，i从后往前遍历，j从前往后遍历
        for (int i = n - 1; i >= 0; --i) {//i从n-1到0
            for (int j = i + 2; j < n + 2; ++j) {//j需要比i+1大，需要比n+2小
                for (int k = i + 1; k < j; k++) {
                    results[i][j] = Math.max(results[i][j], results[i][k] + results[k][j] + newNums[k] * newNums[i] * newNums[j]);
                }
            }
        }

        return results[0][n + 1];
    }


    /**
     * 动态规划？我先递归,显然，超时了，阶乘级时间复杂度啊
     *
     * @param nums 气球数组
     * @return int
     */
    private int burstBalloonsOne(int[] nums) {

        List<Integer> list = new LinkedList<>();
        list.add(1);
        for (int i : nums) {
            list.add(i);
        }
        list.add(1);
        return getMaxVal(list);
    }

    private int getMaxVal(List<Integer> list) {
        if (list.size() == 3) {
            return list.get(1);
        }
        int maxVal = 0;
        for (int i = 1; i < list.size() - 1; ++i) {
            List<Integer> list1 = new LinkedList<>(list);
            list1.remove(i);
            maxVal = Math.max(maxVal, getMaxVal(list1) + list.get(i - 1) * list.get(i) * list.get(i + 1));
        }

        return maxVal;
    }


}
