package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * <p>
 * 示例 1: 
 * <p>
 * 输入: [5,7]
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: [0,1]
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/23
 * @Version 1.0
 */
public class Number0201_BitwiseAndOfNumbersRange {
    @Test
    public void solution() {
        int m = 5;
        int n = 7;
        int expected = 4;
        int result = rangeBitwiseAndThree(m, n);
        Assert.assertEquals(expected, result);
    }

    /**
     * 参考官方题解，求出m，n的公共前缀即可，还有更牛皮的求公共前缀的方式Brian Kernighan算法
     *
     * @param m int
     * @param n int
     * @return int
     */
    private int rangeBitwiseAndThree(int m, int n) {
        //n & (n - 1)是去除n最右位的1，若n与m的1起始位大（不存在公共前缀），则一直删到n=0；若存在公共前缀，由于n比m大（排除相等情况后），则一定有一位n是1，m是0，使得n>m。
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }

    /**
     * 参考官方题解，求出m，n的公共前缀即可
     *
     * @param m int
     * @param n int
     * @return int
     */
    private int rangeBitwiseAndTwo(int m, int n) {

        int bite = 0;//记为右移的位数，等最后右移完再左移回来

        while (m != n) {
            m >>= 1;
            n >>= 1;
            bite++;
        }
        return m << bite;
    }


    /**
     * m到n所有数字按位与，按位与的特点，必须所有数全是1，那一位才是1。m和n的取值范围为int范围内的自然数且m不大于n
     *
     * @param m int
     * @param n int
     * @return int
     */
    private int rangeBitwiseAndOne(int m, int n) {

        if (m == n)
            return m;

        //拿到m和n的最高位，若不同，直接返回0即可
        int bite = 0;
        //求取n的位数
        while (Math.pow(2, bite) - 1 < n) {
            bite++;
        }
        if (bite == 0) {
            return m;
        }
        bite--;
        int result = 0;
        int newBite;
        //当m和n最高位相同，减去最高位的值，继续比较，结果加上最高位的值
        while ((newBite = isSameBite(m, n, bite)) != -1) {
            result += Math.pow(2, bite);
            m -= Math.pow(2, bite);
            n -= Math.pow(2, bite);
            bite = newBite;
        }
        return result;
    }

    private int isSameBite(int m, int n, int bite) {

        if (m - Math.pow(2, bite) < 0) {//最高位不同
            return -1;
        }
        //最高位相同，需要返回n的下一最高位
        n -= Math.pow(2, bite);
        bite--;
        while (bite >= 0 && n - Math.pow(2, bite) < 0) {
            bite--;
        }
        return bite;
    }
}
