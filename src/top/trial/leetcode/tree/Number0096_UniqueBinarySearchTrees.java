package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * 通过次数55,735提交次数83,552
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/15
 * @Version 1.0
 */
public class Number0096_UniqueBinarySearchTrees {

    @Test
    public void solution() {
        int n = 6;
        int expected = 132;
        //int result = uniqueBinarySearchTreesOne(n);//题解有误
        //int result = uniqueBinarySearchTreesTwo(n);
        int result = uniqueBinarySearchTreesThree(n);
        Assert.assertEquals(expected, result);
    }

    /**
     * 参考官方题解，有算式的。。。
     *
     * @param n
     * @return
     */
    private int uniqueBinarySearchTreesThree(int n) {

        int result = 1;
        for (int i = 1; i < n; ++i) {
            result = 2 * result * (2 * i + 1) / (i + 2);
        }
        return result;
    }

    /**
     * 这回用动态规划
     *
     * @param n
     * @return
     */
    private int uniqueBinarySearchTreesTwo(int n) {

        int[] results = new int[n + 1];
        results[0] = 1;
        results[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                results[i] += results[i - j] * results[j - 1];
            }
        }
        return results[n];
    }

    /**
     * 动态规划？
     * 题解有误！！！！！！！！！！！！！！！！
     *
     * @param n 整数数量
     * @return
     */
    private int uniqueBinarySearchTreesOne(int n) {

        int lastLastMid = 0;
        int lastLastResult = 0;
        int lastMid = 0;
        int result = 1;
        int thisMid;

        for (int i = 1; i < n; i++) {
            //i+1个数时
            thisMid = lastLastResult + 2 * lastMid + lastLastMid;

            lastLastMid = lastMid;
            lastMid = thisMid;
            lastLastResult = result;

            result = thisMid + 2 * result;
        }
        return result;
    }
}
