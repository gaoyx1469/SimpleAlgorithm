package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * <p>
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * <p>
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * <p>
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * <p>
 *  
 * <p>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * <p>
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * <p>
 * -2 (K)	-3	    3
 * -5	    -10	    1
 * 10	    30	    -5 (P)
 *  
 * <p>
 * 说明:
 * <p>
 * 骑士的健康点数没有上限。
 * <p>
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number0174_DungeonGame {

    @Test
    public void solution() {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int expected = 7;
        int result = dungeonGameOne(dungeon);
        Assert.assertEquals(expected, result);
    }

    /**
     * 获得路径过程中最小值最大的那个路径，以及最大的那个最小值+1\
     * <p>
     * 动态规划，记录路径上最小值和当前值？
     *
     * @param dungeon 二维数组
     * @return 获得各个路径过程中最小值最大的那个
     */
    private int dungeonGameOne(int[][] dungeon) {

        //公主存在，总不能是空数组了吧
        int m = dungeon.length;//列数
        int n = dungeon[0].length;//行数

        //第三维两个值，一个记录当前值，一个记录路径上的最小值
        int[][] result = new int[m][n];


        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i < m - 1 && j < n - 1) {//两个方向
                    result[i][j] = Math.max(result[i + 1][j], result[i][j + 1]) == 0 ? (dungeon[i][j] > 0 ? 0 : dungeon[i][j]) : (Math.max(result[i + 1][j], result[i][j + 1]) + dungeon[i][j]) > 0 ? 0 : Math.max(result[i + 1][j], result[i][j + 1]) + dungeon[i][j];
                } else if (i < m - 1) {//最右一列
                    result[i][j] = dungeon[i][j] + result[i + 1][j] > 0 ? 0 : dungeon[i][j] + result[i + 1][j];
                } else if (j < n - 1) {//最下一行
                    result[i][j] = dungeon[i][j] + result[i][j + 1] > 0 ? 0 : dungeon[i][j] + result[i][j + 1];
                } else {//右下角
                    result[i][j] = dungeon[i][j] > 0 ? 0 : dungeon[i][j];
                }
            }
        }

        return Math.abs(result[0][0]) + 1;
    }
}
