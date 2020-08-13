package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/13
 * @Version 1.0
 */
public class Number0043_MultiplyStrings {
    @Test
    public void solution() {
        String num1 = "123";
        String num2 = "456";
        String expected = "56088";
        String result = multiplyStringsArray(num1, num2);
        Assert.assertEquals(expected, result);
    }

    /**
     * 可以得到解，但是，考虑到num1和num2长度有限，因此可以用数组代替解法中的map，数组长度定义成num1.len+num2.len+2足够用，遍历数组也比map简单
     *
     * @param num1 String
     * @param num2 String
     * @return String
     */
    private String multiplyStrings(String num1, String num2) {
        if ((num1.length() == 1 && num1.charAt(0) == '0') || (num2.length() == 1 && num2.charAt(0) == '0'))
            return "0";

        Map<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < num1.length(); ++i) {
            for (int j = 0; j < num2.length(); ++j) {
                int temp = (num1.charAt(num1.length() - i - 1) - '0') * (num2.charAt(num2.length() - j - 1) - '0');
                map.put(i + j, map.getOrDefault(i + j, 0) + temp % 10);
                if (temp / 10 != 0)
                    map.put(i + j + 1, map.getOrDefault(i + j + 1, 0) + temp / 10);
            }
        }


        int n = map.size();
        for (int i = 0; i < n + 1; ++i) {
            if (map.containsKey(i) && map.get(i) >= 10) {
                map.put(i + 1, map.getOrDefault(i + 1, 0) + map.get(i) / 10);
                map.put(i, map.get(i) % 10);
            }
        }
        n = map.size();
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; --i)
            sb.append(map.get(i));


        return sb.toString();
    }

    /**
     * 使用数组替代map
     *
     * @param num1 String
     * @param num2 String
     * @return String
     */
    private String multiplyStringsArray(String num1, String num2) {
        if ((num1.length() == 1 && num1.charAt(0) == '0') || (num2.length() == 1 && num2.charAt(0) == '0'))
            return "0";
        int[] result = new int[num1.length() + num2.length() + 2];
        for (int i = 0; i < num1.length(); ++i) {
            for (int j = 0; j < num2.length(); ++j) {
                int temp = (num1.charAt(num1.length() - i - 1) - '0') * (num2.charAt(num2.length() - j - 1) - '0');
                result[i + j] += temp % 10;
                if (temp / 10 != 0)
                    result[i + j + 1] += temp / 10;
            }
        }

        for (int i = 0; i < result.length; ++i) {
            if (result[i] >= 10) {
                result[i + 1] += result[i] / 10;
                result[i] = result[i] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = result.length - 1; i >= 0; --i) {
            if (result[i] != 0 || flag) {
                sb.append(result[i]);
                flag = true;
            }
        }
        return sb.toString();
    }
}
