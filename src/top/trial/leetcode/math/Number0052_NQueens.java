package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * <p>
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * <p>
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * <p>
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/17
 * @Version 1.0
 */
public class Number0052_NQueens {
    @Test
    public void solution() {
        int n = 4;
        int expected = 2;
        int result = totalNQueensOne(n);
        Assert.assertEquals(expected, result);
    }

    int result = 0;

    private int totalNQueensOne(int n) {
        int[] temp = new int[n];
        putQueen(0, temp, n);
        return result;
    }

    /**
     * 放置第pos个位置的王后，temp为当前王后放置情况，int为总共放置几个王后
     *
     * @param pos  int
     * @param temp int[]
     * @param n    int
     */
    private void putQueen(int pos, int[] temp, int n) {
        //放置结束
        if (pos == n) {
            result++;
            return;
        }
        //放置第i个值
        for (int i = 0; i < n; ++i) {
            if (canPut(i, pos, temp, n)) {
                temp[pos] = i;
                putQueen(pos + 1, temp, n);
            }
        }

    }

    /**
     * 判断在第pos位置上放置i，是否与前面的值冲突
     *
     * @param i    int
     * @param pos  int
     * @param temp int[]
     * @param n    int
     * @return boolean
     */
    private boolean canPut(int i, int pos, int[] temp, int n) {
        for (int k = 0; k < pos; ++k) {
            if (temp[k] == i || Math.abs(temp[k] - i) == pos - k)
                return false;
        }
        return true;
    }
}
