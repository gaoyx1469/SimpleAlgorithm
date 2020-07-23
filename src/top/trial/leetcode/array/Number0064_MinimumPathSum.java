package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/23
 * @Version 1.0
 */
public class Number0064_MinimumPathSum {
    @Test
    public void solution() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int expected = 7;
        int result = getMinimumPathSumOne(grid);
        Assert.assertEquals(result, expected);
    }

    /**
     * 这不最经典的动态规划么？另外，二维数组可优化为一维数组
     *
     * @param grid int[][]
     * @return int
     */
    private int getMinimumPathSumOne(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] result = new int[m + 1][n + 1];

        for (int i = 2; i <= m; ++i) {
            result[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 2; i <= n; ++i) {
            result[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                result[i][j] = Math.min(result[i - 1][j], result[i][j - 1]) + grid[i - 1][j - 1];
            }
        }

        return result[m][n];
    }

}
