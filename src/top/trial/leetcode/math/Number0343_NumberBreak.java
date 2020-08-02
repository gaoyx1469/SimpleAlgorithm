package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/30
 * @Version 1.0
 */
public class Number0343_NumberBreak {
    @Test
    public void solution() {
        int n = 10;
        int expected = 36;
        int result = numberBreak(n);
        Assert.assertEquals(expected, result);
    }

    /**
     * 易知，当一个数字大于4之后，就应该继续拆分，而不是使用自身，因此，将数拆分为2、3、4即可，而4又和2个2没区别，因此其实是看怎么拆分能拿到最多的3，且余下的是2
     * <p>
     * 思考动态规划的路上，想到了数学的方式，哈
     *
     * @param n int
     * @return int
     */
    private int numberBreak(int n) {

        if (n == 2)
            return 1;
        if (n == 3)
            return 2;

        int i = n % 3;
        int j = n / 3;

        if (i == 0)
            return (int) Math.pow(3, j);
        if (i == 1)
            return (int) Math.pow(3, j - 1) * 4;
        else
            return (int) Math.pow(3, j) * 2;
    }
}
