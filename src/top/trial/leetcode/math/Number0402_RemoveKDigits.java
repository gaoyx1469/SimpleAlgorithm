package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * @Author gaoyx1469
 * @Date 2020/11/15
 * @Version 1.0
 */
public class Number0402_RemoveKDigits {
    @Test
    public void solution() {
        String num = "1432219";
        int k = 3;
        String expected = "1219";
        String result = removeKdigits(num, k);
        Assert.assertEquals(expected, result);
    }

    /**
     * 怎么删合适？本质上是从第一位开始，在可选位上挑选最小值即可
     *
     * @param num String
     * @param k   int
     * @return String
     */
    private String removeKdigits(String num, int k) {

        char[] nums = num.toCharArray();
        int len = nums.length;
        String nothing = "0";

        if (len <= k)
            return nothing;
        char[] result = new char[len - k];

        int numsIndex = -1;
        int resultIndex = 0;

        while (resultIndex < len - k) {
            numsIndex = getMinNum(nums, numsIndex + 1, k + resultIndex - numsIndex);
            result[resultIndex] = nums[numsIndex];
            resultIndex++;
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : result) {
            if (ch != '0' || sb.length() != 0)
                sb.append(ch);
        }

        if (sb.length() == 0)
            return nothing;
        else
            return sb.toString();
    }

    private int getMinNum(char[] nums, int startIndex, int num) {
        int min = nums[startIndex];
        int retValue = startIndex;
        for (int i = startIndex + 1; i < startIndex + num; ++i) {
            if (nums[i] < min) {
                min = nums[i];
                retValue = i;
            }
        }
        return retValue;
    }
}
