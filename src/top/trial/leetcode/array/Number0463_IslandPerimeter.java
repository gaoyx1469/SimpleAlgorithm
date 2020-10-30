package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 * 示例 :
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * 输出: 16
 * 解释: 它的周长是下面图片中的 16 个黄色的边
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/30
 * @Version 1.0
 */
public class Number0463_IslandPerimeter {
    @Test
    public void solution() {
        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int expected = 16;
        int result = islandPerimeter(grid);
        Assert.assertEquals(expected, result);
    }

    /**
     * 获取周长
     *
     * @param grid int[][]
     * @return int
     */
    private int islandPerimeter(int[][] grid) {
        int result = 0;
        if (grid.length < 1)
            return 0;
        int[][] edges = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        //遍历所有的1，数与其相连有多少0
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1)
                    result += getNum(i, j, grid, edges);
            }
        }
        return result;
    }

    private int getNum(int i, int j, int[][] grid, int[][] edges) {
        int val = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int[] edge : edges) {
            int x = i + edge[0];
            int y = j + edge[1];
            if (isEdge(m, n, x, y) || grid[x][y] == 0)
                val++;
        }
        return val;
    }

    private boolean isEdge(int m, int n, int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}
