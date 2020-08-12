package top.trial.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/11
 * @Version 1.0
 */
public class Number0130_SurroundedRegions {
    @Test
    public void solution() {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * 思想：从外向内遍历，看是否有O相连，显然这个想法不正确，转变思路，使用集合记录可扩展点，
     *
     * @param board char[][]
     */
    private void solve(char[][] board) {

        int m = board.length;//行
        if (m <= 0)
            return;
        int n = board[0].length;//列

        if (m <= 2 || n <= 2)
            return;

        Queue<Integer[]> chars = new ArrayDeque<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'T';
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        Integer[] index = new Integer[2];
                        index[0] = i;
                        index[1] = j;
                        chars.add(index);
                    }
                }
            }
        }

        while (!chars.isEmpty()) {

            Integer[] index = chars.poll();//取出并从中移除
            if (board[index[0]][index[1]] == 'O')//已处理
                continue;
            board[index[0]][index[1]] = 'O';
            if (index[0] > 0 && board[index[0] - 1][index[1]] == 'T') {
                Integer[] integers = new Integer[2];
                integers[0] = index[0] - 1;
                integers[1] = index[1];
                chars.add(integers);
            }
            if (index[0] < m - 1 && board[index[0] + 1][index[1]] == 'T') {
                Integer[] integers = new Integer[2];
                integers[0] = index[0] + 1;
                integers[1] = index[1];
                chars.add(integers);
            }
            if (index[1] > 0 && board[index[0]][index[1] - 1] == 'T') {
                Integer[] integers = new Integer[2];
                integers[0] = index[0];
                integers[1] = index[1] - 1;
                chars.add(integers);
            }
            if (index[1] < n - 1 && board[index[0]][index[1] + 1] == 'T') {
                Integer[] integers = new Integer[2];
                integers[0] = index[0];
                integers[1] = index[1] + 1;
                chars.add(integers);
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'X';
                }
            }
        }

    }
}
