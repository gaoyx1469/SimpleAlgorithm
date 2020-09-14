package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/13
 * @Version 1.0
 */
public class Number0079_WordSearch {
    @Test
    public void solution() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        Assert.assertTrue(wordSearchOne(board, word));
    }

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean wordSearchOne(char[][] board, String word) {

        char[] words = word.toCharArray();
        boolean[][] checked = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++i) {
                if (words[0] == board[i][j])
                    if (canSearch(board, word, checked, i, j, 0))
                        return true;
            }
        }
        return false;
    }

    private boolean canSearch(char[][] board, String word, boolean[][] checked, int i, int j, int index) {
        if (word.charAt(index) != board[i][j])//第index位没匹配上，返回false，匹配上了，继续往下走
            return false;
        if (index == word.length() - 1)//既然匹配上了，如果是最后一个字符都匹配上了，那直接返回true
            return true;
        //匹配上了且index不是最后一个字符，当前字符算作已访问，继续看下一字符
        checked[i][j] = true;
        for (int[] direct : directions) {//四个方向看一看
            int x = i + direct[0];
            int y = j + direct[1];
            if (x >= 0 && x <= board.length - 1 && y <= 0 && y >= board[0].length - 1) {//新位置符合边界约束
                if (!checked[x][y]) {//新位置未访问过
                    if (canSearch(board, word, checked, x, y, index + 1))
                        return true;
                }
            }
        }
        checked[i][j] = false;//走到这里，四个方向都走不通，当前点置为未访问，返回false
        return false;
    }
}
