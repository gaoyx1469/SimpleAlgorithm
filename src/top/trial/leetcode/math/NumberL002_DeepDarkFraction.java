package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？
 * 在本题中，所有系数都是大于等于0的整数。
 * 输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。
 * <p>
 * 示例 1：
 * 输入：cont = [3, 2, 0, 2]
 * 输出：[13, 4]
 * 解释：原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8], [-13, -4]都不是正确答案。
 * <p>
 * 示例 2：
 * 输入：cont = [0, 0, 3]
 * 输出：[3, 1]
 * 解释：如果答案是整数，令分母为1即可。
 * <p>
 * 限制：
 * cont[i] >= 0
 * 1 <= cont的长度 <= 10
 * cont最后一个元素不等于0
 * 答案的n, m的取值都能被32位int整型存下（即不超过2 ^ 31 - 1）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/deep-dark-fraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/24
 * @Version 1.0
 */
public class NumberL002_DeepDarkFraction {
    @Test
    public void solution() {
        int[][] cont = {{3, 2, 0, 2}, {0, 0, 3}};
        int[][] expected = {{13, 4}, {3, 1}};
        int[][] result = new int[cont.length][2];
        for (int i = 0; i < cont.length; ++i)
            result[i] = deepDarkFraction(cont[i]);
        Assert.assertArrayEquals(expected, result);
    }

    private int[] deepDarkFraction(int[] cont) {
        int len = cont.length;
        int n = cont[len - 1];
        int m = 1;
        int tempM;

        for (int i = len - 2; i >= 0; --i) {
            tempM = m;
            m = n;
            n = cont[i] * n + tempM;
        }
        return new int[]{n, m};
    }

}
