package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/18
 * @Version 1.0
 */
public class Number0097_InterleavingString {
    @Test
    public void solution() {
        String[] s1 = {"aabcc", "aabcc"};
        String[] s2 = {"dbbca", "dbbca"};
        String[] s3 = {"aadbbcbcac", "aadbbbaccc"};
        boolean[] expected = {true, false};
        boolean[] results = new boolean[2];
        //results[0] = interleavingStringOne(s1[0], s2[0], s3[0]);
        //results[1] = interleavingStringOne(s1[1], s2[1], s3[1]);
        results[0] = interleavingStringTwo(s1[0], s2[0], s3[0]);
        results[1] = interleavingStringTwo(s1[1], s2[1], s3[1]);
        Assert.assertArrayEquals(expected, results);
    }

    /**
     * 官方题解优化：由于二维数组的第i行只参考了i-1行的数据，因此不用使用二维数组，一维就够了
     *
     * @param s1 String
     * @param s2 String
     * @param s3 String
     * @return boolean
     */
    private boolean interleavingStringTwo(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if (l3 != l1 + l2)
            return false;

        boolean[] results = new boolean[l2 + 1];
        results[0] = true;
        for (int i = 0; i <= l1; ++i) {
            for (int j = 0; j <= l2; ++j) {
                if (i > 0) {
                    results[j] = results[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
                if (j > 0) {
                    results[j] = results[j] || (results[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return results[l2];
    }

    /**
     * 似乎可以动态规划
     *
     * @param s1 String
     * @param s2 String
     * @param s3 String
     * @return boolean
     */
    private boolean interleavingStringOne(String s1, String s2, String s3) {

        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if (l3 != l1 + l2)
            return false;

        boolean[][] results = new boolean[l1 + 1][l2 + 1];
        results[0][0] = true;
        for (int i = 1; i <= l1; ++i) {
            results[i][0] = results[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int j = 1; j <= l2; ++j) {
            results[0][j] = results[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= l1; ++i) {
            for (int j = 1; j <= l2; ++j) {
                results[i][j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && results[i - 1][j]) || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && results[i][j - 1]);
            }
        }
        return results[l1][l2];
    }
}
