package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/3
 * @Version 1.0
 */
public class Number0415_AddStrings {
    @Test
    public void solution() {
        String num1 = "54321";
        String num2 = "67890";

        String expected = "122211";
        String result = addStrings(num1, num2);
        System.out.println(result);

        Assert.assertTrue(expected.equals(result));
    }

    /**
     * 还能再优化，用StringBuffer替换String进行拼接
     *
     * @param num1 String
     * @param num2 String
     * @return String
     */
    private String addStrings(String num1, String num2) {

        if (num1.length() > num2.length()) {
            return addStrings(num2, num1);
        }

        int shortLen = num1.length();
        int longLen = num2.length();
        for (int i = 0; i < longLen - shortLen; ++i) {
            num1 = "0" + num1;
        }

        int m = 0;
        int n = 0;
        String result = "";
        for (int i = longLen - 1; i >= 0; --i) {
            int num = num1.charAt(i) + num2.charAt(i) - '0' - '0' + m;
            m = num / 10;
            n = num % 10;
            result = n + result;
        }

        return m == 0 ? result : m + result;
    }
}
