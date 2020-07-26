package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;


/**
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 * <p>
 * 输入: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/26
 * @Version 1.0
 */
public class Number0329_LongestIncreasingPathInAMatrix {

    @Test
    public void solution() {

        int[][] nums = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int expected = 4;
        int result = longestIncreasingPathInAMatrixOne(nums);
        Assert.assertEquals(expected, result);
    }

    /**
     * 参考了官方题解，通过深度优先搜索得出答案，并采用矩阵记录已经计算过的值，避免重复计算
     *
     * @param nums 二维数组
     * @return 最长升序列
     */
    private int longestIncreasingPathInAMatrixOne(int[][] nums) {
        int m = nums.length;
        if (m == 0) {
            return 0;
        }
        int n = nums[0].length;
        if (n == 0) {
            return 0;
        }
        int retVal = 0;

        int[][] results = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (results[i][j] == 0) {//避免重复计算
                    //四周寻找比他大的，若有，继续找那个比比他大的更大的
                    fillTheResult(results, nums, i, j);
                }
            }
        }

        for (int[] line : results) {
            for (int num : line) {
                retVal = Math.max(retVal, num);
            }
        }

        return retVal;
    }

    private void fillTheResult(int[][] results, int[][] nums, int i, int j) {
        int temp = 0;

        if (i > 0 && nums[i][j] < nums[i - 1][j]) {//上
            if (results[i - 1][j] == 0)//避免重复计算
                fillTheResult(results, nums, i - 1, j);
            temp = Math.max(temp, results[i - 1][j]);
        }
        if (i < nums.length - 1 && nums[i][j] < nums[i + 1][j]) {//下
            if (results[i + 1][j] == 0)//避免重复计算
                fillTheResult(results, nums, i + 1, j);
            temp = Math.max(temp, results[i + 1][j]);
        }
        if (j > 0 && nums[i][j] < nums[i][j - 1]) {//左
            if (results[i][j - 1] == 0)//避免重复计算
                fillTheResult(results, nums, i, j - 1);
            temp = Math.max(temp, results[i][j - 1]);
        }
        if (j < nums[0].length - 1 && nums[i][j] < nums[i][j + 1]) {//右
            if (results[i][j + 1] == 0)//避免重复计算
                fillTheResult(results, nums, i, j + 1);
            temp = Math.max(temp, results[i][j + 1]);
        }

        results[i][j] = temp + 1;
    }
}
