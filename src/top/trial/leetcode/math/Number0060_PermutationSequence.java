package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/5
 * @Version 1.0
 */
public class Number0060_PermutationSequence {

    @Test
    public void solution() {
        int n = 4;
        int k = 9;
        String expected = "2314";
        String result = permutationSequenceOne(n, k);
        Assert.assertEquals(expected, result);
    }

    /**
     * 找到第k个排序
     *
     * @param n int
     * @param k int
     * @return String
     */
    private String permutationSequenceOne(int n, int k) {

        if (n == 1)
            return "1";

        int[] temp = new int[n - 1];//存阶乘信息
        temp[0] = 1;//阶乘初始补值
        LinkedList<Integer> list = new LinkedList<>();//存数值序列
        for (int i = 1; i < n - 1; ++i) {
            temp[i] = temp[i - 1] * (i + 1);
            list.add(i);
        }
        list.add(n - 1);//数值序列补值
        list.add(n);

        int[] result = new int[n];//存结果序列
        for (int i = 0; i < n - 1; ++i) {
            int index = (k - 1) / temp[n - i - 2];
            result[i] = list.get(index);
            list.remove(index);
            k = k - index * temp[n - i - 2];
        }
        result[n - 1] = list.remove();//结果序列补值
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(result[i]);
        }

        return sb.toString();
    }
}
