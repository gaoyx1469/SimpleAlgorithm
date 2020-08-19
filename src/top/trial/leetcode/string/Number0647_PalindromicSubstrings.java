package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/19
 * @Version 1.0
 */
public class Number0647_PalindromicSubstrings {
    @Test
    public void solution() {
        String s = "aaa";
        int expected = 6;
        int result = countSubstringsOne(s);
        // TODO 根据官方题解，还有Manacher马拉车算法=_=
        Assert.assertEquals(expected, result);
    }

    /**
     * 统计回文子串的个数，中心拓展法
     *
     * @param s String
     * @return int
     */
    private int countSubstringsOne(String s) {

        if (s == null)
            return 0;
        int len = s.length();
        if (len < 2)
            return len;
        //以第一个字符开始，包括字符和字符中间，依次以其为回文中心。
        int result = 0;
        for (int i = 0; i < len - 1; ++i) {//计算以i为中心和以i和i+1中间为中心的回文数
            result += countSubstringsByCenter(i, s, true);
            result += countSubstringsByCenter(i, s, false);
        }
        return ++result;
    }


    private int countSubstringsByCenter(int i, String s, boolean flag) {
        int returnVal = flag ? 1 : 0;//以i为中心，自身不用比了
        int start = flag ? i : i + 1;
        int end = i;
        while (start > 0 && end < s.length() - 1) {
            start--;
            end++;
            if (s.charAt(start) == s.charAt(end))
                returnVal++;
            else
                break;
        }
        return returnVal;
    }
}
