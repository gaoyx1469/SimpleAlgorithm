package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * 示例 1：
 * 输入：[2,1]
 * 输出：false
 * 示例 2：
 * 输入：[3,5,5]
 * 输出：false
 * 示例 3：
 * 输入：[0,3,2,1]
 * 输出：true
 * <p>
 * 提示：
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/3
 * @Version 1.0
 */
public class Number0941_ValidMountainArray {
    @Test
    public void solution() {
        int[] A = {0, 3, 2, 1};
        Assert.assertTrue(validMountainArrayOne(A));
    }

    private boolean validMountainArrayOne(int[] A) {
        int len = A.length;
        if (len < 3)
            return false;
        int start = 0;
        int end = len - 1;
        while (start < len - 1 && A[start + 1] > A[start]) {
            start++;
        }
        while (end > 0 && A[end] < A[end - 1]) {
            end--;
        }
        return (start != 0) && (end != len - 1) && (start == end);
    }
}
