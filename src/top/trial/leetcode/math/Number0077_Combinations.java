package top.trial.leetcode.math;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/8
 * @Version 1.0
 */
public class Number0077_Combinations {
    @Test
    public void solution() {
        int n = 4;
        int k = 2;
        ArrayListUtil.getTwoDimensionalSysoLikeArray(combinationsOne(n, k));
    }

    private final List<List<Integer>> results = new ArrayList<>();
    private List<Integer> temp;
    private int k;
    private int n;

    /**
     * 排除掉意外情况后，怎么排列组合
     *
     * @param n int
     * @param k int
     * @return List<List < Integer>>
     */
    private List<List<Integer>> combinationsOne(int n, int k) {
        if (k <= 0 || n < k)
            return results;
        this.k = k;
        this.n = n;
        temp = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            temp.add(0);
        }
        getCombinations(0, 1);

        return results;
    }

    /**
     * @param startIndex int
     * @param startValue int
     */
    private void getCombinations(int startIndex, int startValue) {
        if (startValue > n || n - startValue < k - startIndex - 1)//已越界
            return;
        for (int i = startValue; i <= n; ++i) {
            temp.set(startIndex, i);
            if (startIndex == k - 1) {
                results.add(new ArrayList<>(temp));
            } else {
                getCombinations(startIndex + 1, i + 1);
            }

        }
    }
}
