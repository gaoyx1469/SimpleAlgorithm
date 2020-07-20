package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * <p>
 * 示例 1: (From the famous "Die Hard" example)
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 * @Author gaoyx1469
 * @Date 2020/7/20
 * @Version 1.0
 */
public class Number0365_WaterAndJugProblem {
    @Test
    public void solution() {
        int[] x = {3, 2, 4};
        int[] y = {5, 6, 6};
        int[] z = {4, 5, 8};
        boolean[] expected = {true, false, true};
        boolean[] results = new boolean[x.length];
        for (int i = 0; i < x.length; ++i) {
            results[i] = waterAndJugProblemOne(x[i], y[i], z[i]);
        }
        Assert.assertArrayEquals(expected, results);
    }

    /**
     * 参考官方题解，纯数学问题，每次操作都是两个壶之间的倒腾（两壶总水量不变）或者两壶中某一空壶加水或某一满壶倒水（两壶总水量增加或减少x或y）
     * 所以，最终总水量一定是ax+by，且小于等于x+y，大于等于0
     * 而如果z = ax+by，【z一定是x和y的最大公约数的整数倍】是其充要条件
     *
     * @param x int
     * @param y int
     * @param z int
     * @return boolean
     */
    private boolean waterAndJugProblemOne(int x, int y, int z) {
        if (x + y < z)
            return false;
        if (x == 0 || y == 0)
            return z == 0 || z == x + y;
        //return z % (new BigInteger(String.valueOf(x)).gcd(new BigInteger(String.valueOf(y))).intValue()) == 0;
        return z % gcd(x, y) == 0;
    }

    private int gcd(int x, int y) {
        if (x < y)
            return gcd(y, x);
        if (x % y == 0)
            return y;
        return gcd(y, x % y);
    }
}
