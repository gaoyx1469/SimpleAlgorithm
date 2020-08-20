package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 让我们一起来玩扫雷游戏！
 * <p>
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * <p>
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 * <p>
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * <p>
 * [['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'M', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E']]
 * <p>
 * Click : [3,0]
 * <p>
 * 输出:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * 示例 2：
 * <p>
 * 输入:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * Click : [1,2]
 * <p>
 * 输出:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'X', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * <p>
 * 注意：
 * <p>
 * 输入矩阵的宽和高的范围为 [1,50]。
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
 * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
 * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/20
 * @Version 1.0
 */
public class Number0529_Minesweeper {
    @Test
    public void solution() {
        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        int[] click = {3, 0};
        char[][] newBoard = updateBoardOne(board, click);
        System.out.println(Arrays.deepToString(newBoard));
    }

    /**
     * 思路一致，但是官方题解有优化：1、八个方向的坐标变化定义成两个类成员变量数组；2、由于会存在同一个还没遍历到的点多次进入队列，因此可以建立一个二维数组，记录是否遍历到
     *
     * @param board char[][]
     * @param click int[]
     * @return char[][]
     */
    private char[][] updateBoardOne(char[][] board, int[] click) {

        if (board[click[0]][click[1]] == 'M') {//点到雷
            board[click[0]][click[1]] = 'X';
        } else if (board[click[0]][click[1]] == 'E') {//点到非雷可点处
            Deque<int[]> list = new LinkedList<>();
            list.push(click);
            while (!list.isEmpty()) {
                int[] curr = list.poll();
                //判断周围有几个雷
                int num = countMine(board, curr);
                board[curr[0]][curr[1]] = num == 0 ? 'B' : (char) (num + '0');

                if (num == 0) {
                    //周围的E，全部入list
                    if (curr[0] > 0 && curr[1] > 0 && board[curr[0] - 1][curr[1] - 1] == 'E') {
                        list.push(new int[]{curr[0] - 1, curr[1] - 1});
                    }
                    if (curr[0] > 0 && board[curr[0] - 1][curr[1]] == 'E') {
                        list.push(new int[]{curr[0] - 1, curr[1]});
                    }
                    if (curr[0] > 0 && curr[1] < board[0].length - 1 && board[curr[0] - 1][curr[1] + 1] == 'E') {
                        list.push(new int[]{curr[0] - 1, curr[1] + 1});
                    }
                    if (curr[1] > 0 && board[curr[0]][curr[1] - 1] == 'E') {
                        list.push(new int[]{curr[0], curr[1] - 1});
                    }
                    if (curr[1] < board[0].length - 1 && board[curr[0]][curr[1] + 1] == 'E') {
                        list.push(new int[]{curr[0], curr[1] + 1});
                    }
                    if (curr[0] < board.length - 1 && curr[1] > 0 && board[curr[0] + 1][curr[1] - 1] == 'E') {
                        list.push(new int[]{curr[0] + 1, curr[1] - 1});
                    }
                    if (curr[0] < board.length - 1 && board[curr[0] + 1][curr[1]] == 'E') {
                        list.push(new int[]{curr[0] + 1, curr[1]});
                    }
                    if (curr[0] < board.length - 1 && curr[1] < board[0].length - 1 && board[curr[0] + 1][curr[1] + 1] == 'E') {
                        list.push(new int[]{curr[0] + 1, curr[1] + 1});
                    }
                }
            }
        }
        //点到其它处，忽略
        return board;
    }

    private int countMine(char[][] board, int[] curr) {
        int count = 0;
        if (curr[0] > 0 && curr[1] > 0 && board[curr[0] - 1][curr[1] - 1] == 'M') {
            count++;
        }
        if (curr[0] > 0 && board[curr[0] - 1][curr[1]] == 'M') {
            count++;
        }
        if (curr[0] > 0 && curr[1] < board[0].length - 1 && board[curr[0] - 1][curr[1] + 1] == 'M') {
            count++;
        }
        if (curr[1] > 0 && board[curr[0]][curr[1] - 1] == 'M') {
            count++;
        }
        if (curr[1] < board[0].length - 1 && board[curr[0]][curr[1] + 1] == 'M') {
            count++;
        }
        if (curr[0] < board.length - 1 && curr[1] > 0 && board[curr[0] + 1][curr[1] - 1] == 'M') {
            count++;
        }
        if (curr[0] < board.length - 1 && board[curr[0] + 1][curr[1]] == 'M') {
            count++;
        }
        if (curr[0] < board.length - 1 && curr[1] < board[0].length - 1 && board[curr[0] + 1][curr[1] + 1] == 'M') {
            count++;
        }
        return count;
    }
}
