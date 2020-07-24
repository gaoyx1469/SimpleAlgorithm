package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * <p>
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 * <p>
 * 例 1：
 * <p>
 * 输入: m = 3, n = 3, k = 5
 * 输出: 3
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * <p>
 * 第5小的数字是 3 (1, 2, 2, 3, 3).
 * 例 2：
 * <p>
 * 输入: m = 2, n = 3, k = 6
 * 输出: 6
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * <p>
 * 第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 * 注意：
 * <p>
 * m 和 n 的范围在 [1, 30000] 之间。
 * k 的范围在 [1, m * n] 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-number-in-multiplication-table
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/24
 * @Version 1.0
 */
public class Number0668_KthSmallestNumberInMultiplicationTable {

    @Test
    public void solution() {
        int[] m = {3, 2};
        int[] n = {3, 3};
        int[] k = {5, 6};
        int[] expected = {3, 6};
        int[] result = new int[m.length];
        for (int i = 0; i < m.length; ++i)
            result[i] = kthSmallestNumberInMultiplicationTable(m[i], n[i], k[i]);
        Assert.assertArrayEquals(expected, result);
    }

    /**
     * 参考官方题解
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int kthSmallestNumberInMultiplicationTable(int m, int n, int k) {
        int lo = 1, hi = m * n;//定义最小值和最大值
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;//二分法拿中间值
            if (!enough(mi, m, n, k))//小于mi的值的个数少于k
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }

    public boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {//计算m行n列中小于等于x的值的个数
            count += Math.min(x / i, n);
        }
        return count >= k;//m行n列中小于等于x的值的个数是否大于等于k
    }


}
