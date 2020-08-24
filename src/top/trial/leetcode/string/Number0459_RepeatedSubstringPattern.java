package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abab"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * <p>
 * 输入: "aba"
 * <p>
 * 输出: False
 * 示例 3:
 * <p>
 * 输入: "abcabcabcabc"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/24
 * @Version 1.0
 */
public class Number0459_RepeatedSubstringPattern {
    @Test
    public void solution() {
        String s = "ababab";
        boolean result = repeatedSubstringPatternTwo(s);
        Assert.assertTrue(result);
    }

    /**
     * 官方变态题解
     *
     * @param s String
     * @return boolean
     */
    private boolean repeatedSubstringPatternTwo(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    /**
     * 求取字符串长度的因数？做对了，但是没新意嘛
     *
     * @param s String
     * @return String
     */
    private boolean repeatedSubstringPatternOne(String s) {

        int len = s.length();
        if (len == 1)
            return false;
        int n = (int) Math.floor(Math.sqrt(len));
        System.out.println(n);
        //判断子串大于1的情况
        for (int i = n; i > 1; --i) {
            if (len % i == 0 && (isRepeated(s, i) || isRepeated(s, len / i)))
                return true;
        }
        //判断子串就一位的情况
        char ch = s.charAt(0);
        for (int i = 1; i < len; ++i) {
            if (s.charAt(i) != ch)
                return false;
        }
        return true;
    }

    /**
     * 看s是否是由子串重复i次组成
     *
     * @param s String
     * @param n int
     * @return boolean
     */
    private boolean isRepeated(String s, int n) {
        int subLen = s.length() / n;
        for (int i = 1; i < n; ++i) {
            if (!s.substring(subLen * (i - 1), subLen * i).equals(s.substring(subLen * i, subLen * (i + 1)))) {
                return false;
            }
        }
        return true;
    }
}
