package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * 返回 true.
 * <p>
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * <p>
 * 返回 false.
 * <p>
 * 后续挑战 :
 * <p>
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/27
 * @Version 1.0
 */
public class Number0392_IsSubsequence {

    @Test
    public void solution() {
        String[] ss = {"abc", "axc"};
        String[] ts = {"ahbgdc", "ahbgdc"};
        boolean[] expected = {true, false};
        boolean[] result = new boolean[ss.length];
        for (int i = 0; i < ss.length; ++i)
            result[i] = isSubsequence(ss[i], ts[i]);
        Assert.assertArrayEquals(expected, result);
    }

    /**
     * 从头到尾遍历，是不是过于简单.....
     * 对于后续挑战，由于s数量级太大，因此要优化匹配速度，先使用动态规划一次性优化t【使得比较过程优化到正好比较s.length次】
     *
     * @param s String
     * @param t String
     * @return boolean
     */
    private boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                ++i;
            }
            ++j;
        }
        return i == m;
    }
}
