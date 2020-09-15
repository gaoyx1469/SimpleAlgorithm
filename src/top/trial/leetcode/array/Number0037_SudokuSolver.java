package top.trial.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/15
 * @Version 1.0
 */
public class Number0037_SudokuSolver {
    @Test
    public void solution() {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; ++i) {
            Arrays.fill(board[i], '.');
        }
        board[0][0] = '5';
        board[0][1] = '3';
        board[0][4] = '7';
        board[1][0] = '6';
        board[1][3] = '1';
        board[1][4] = '9';
        board[1][5] = '5';
        board[2][1] = '9';
        board[2][2] = '8';
        board[2][7] = '6';

        board[3][0] = '8';
        board[3][4] = '6';
        board[3][8] = '3';
        board[4][0] = '4';
        board[4][3] = '8';
        board[4][5] = '3';
        board[4][8] = '1';
        board[5][0] = '7';
        board[5][4] = '2';
        board[5][8] = '6';

        board[6][1] = '6';
        board[6][6] = '2';
        board[6][7] = '8';
        board[7][3] = '4';
        board[7][4] = '1';
        board[7][5] = '9';
        board[7][8] = '5';
        board[8][4] = '8';
        board[8][7] = '7';
        board[8][8] = '9';
        SudokuSolverOne(board);

        for (int i = 0; i < 9; ++i) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    //思路：组织三个map数组，分别存行/列/块中的数据
    Map<Character, Integer>[] rowsMap = new Map[9];
    Map<Character, Integer>[] columnsMap = new Map[9];
    Map<Character, Integer>[][] blocksMap = new Map[3][3];
    List<int[]> emptyQueue = new ArrayList<>();

    /**
     * 解数独
     *
     * @param board board
     */
    private void SudokuSolverOne(char[][] board) {

        for (int i = 0; i < 9; ++i) {
            rowsMap[i] = new HashMap<>();
            columnsMap[i] = new HashMap<>();
            blocksMap[i / 3][i % 3] = new HashMap<>();
        }


        //初始化数据
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] >= '1' && board[i][j] <= '9') {
                    rowsMap[i].put(board[i][j], 0);
                    columnsMap[j].put(board[i][j], 0);
                    blocksMap[i / 3][j / 3].put(board[i][j], 0);
                } else {
                    emptyQueue.add(new int[]{i, j});
                }
            }
        }

        //填写第一个值
        for (char c = '1'; c <= '9'; ++c) {
            if (!rowsMap[emptyQueue.get(0)[0]].containsKey(c) && !columnsMap[emptyQueue.get(0)[1]].containsKey(c) && !blocksMap[emptyQueue.get(0)[0] / 3][emptyQueue.get(0)[1] / 3].containsKey(c)) {
                //数据可填，尝试
                if (putNumberToArray(board, emptyQueue.get(0)[0], emptyQueue.get(0)[1], c, 1))
                    return;
            }
        }
    }

    //返回在board[i][j]中填c是否可以
    private boolean putNumberToArray(char[][] board, int i, int j, char c, int index) {
        //填好值
        board[i][j] = c;
        rowsMap[i].put(c, 0);
        columnsMap[j].put(c, 0);
        blocksMap[i / 3][j / 3].put(c, 0);

        //都填满了，没有下一个值了
        if (index == emptyQueue.size()) {
            return true;
        }

        //继续填下一个
        //填写第一个值
        for (char ch = '1'; ch <= '9'; ++ch) {
            if (!rowsMap[emptyQueue.get(index)[0]].containsKey(ch) && !columnsMap[emptyQueue.get(index)[1]].containsKey(ch) && !blocksMap[emptyQueue.get(index)[0] / 3][emptyQueue.get(index)[1] / 3].containsKey(ch)) {
                //数据可填，尝试
                if (putNumberToArray(board, emptyQueue.get(index)[0], emptyQueue.get(index)[1], ch, index + 1)) {
                    return true;
                }
            }
        }

        //下一个值填啥都不行，说明当前值不对，擦除并返回false
        board[i][j] = '.';
        rowsMap[i].remove(c);
        columnsMap[j].remove(c);
        blocksMap[i / 3][j / 3].remove(c);
        return false;
    }
}
