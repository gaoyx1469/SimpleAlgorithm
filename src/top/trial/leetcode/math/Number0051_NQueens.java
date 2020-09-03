package top.trial.leetcode.math;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.*;

/**
 * n皇后问题，这是算法经典题啊，应该都会吧
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 * <p>
 * 提示：
 * <p>
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/3
 * @Version 1.0
 */
public class Number0051_NQueens {
    @Test
    public void solution() {
        ArrayListUtil.getTwoDimensionalSysoLikeArray(solveNQueensOne(4));
    }

    Map<Integer, String> map = new HashMap<>();
    List<List<String>> results = new ArrayList<>();

    /**
     * n皇后问题
     * <p>
     * n*n数组
     *
     * @param n int
     * @return List<List < String>>
     */
    private List<List<String>> solveNQueensOne(int n) {
        for (int i = 0; i < n; ++i) {
            String s = ".".repeat(i) + "Q" + ".".repeat(n - i - 1);
            map.put(i, s);
        }
        int[] curr = new int[n];
        putQueen(0, curr, n);
        return results;
    }

    /**
     * 放置第n-1个皇后
     *
     * @param n        int
     * @param curr     int[]
     * @param queenNum int
     */
    private void putQueen(int n, int[] curr, int queenNum) {

        if (n == queenNum) {//已经放置了最后一个皇后
            List<String> list = new ArrayList<>();
            for (int i : curr)
                list.add(map.get(i));
            results.add(list);
            return;
        }
        //开始放置
        for (int i = 0; i < queenNum; ++i) {
            if (canPut(i, n, curr)) {
                curr[n] = i;
                putQueen(n + 1, curr, queenNum);
            }
        }
    }

    /**
     * 第n+1个皇后放在i位置，是否可以
     *
     * @param i    int  要放置的皇后的位置
     * @param n    int  要放置的皇后是第几个【第n+1个】，放置到curr[n]上
     * @param curr int[]
     * @return boolean
     */
    private boolean canPut(int i, int n, int[] curr) {
        //判断第n+1个皇后与前面n个是否冲突
        for (int x = 0; x < n; x++) {
            if (i == curr[x] || Math.abs(i - curr[x]) == n - x)
                return false;
        }
        return true;
    }
}
