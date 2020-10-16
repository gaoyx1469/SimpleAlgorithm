package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/16
 * @Version 1.0
 */
public class Number0977_SquaresOfASortedArray {
    @Test
    public void solution() {
        int[] A = {-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(sortedSquareOne(A)));
    }

    /**
     * 从两端开始双指针，比较平方和大小，大的直接填到结果数组尾部。
     *
     * @param A int[]
     * @return int[]
     */
    private int[] sortedSquareOne(int[] A) {
        int len = A.length;
        int[] result = new int[len];
        int front = 0;
        int behind = len - 1;
        int index = len - 1;
        while (front <= behind) {
            if (Math.abs(A[front]) > Math.abs(A[behind])) {
                result[index] = A[front] * A[front];
                front++;
            } else {
                result[index] = A[behind] * A[behind];
                behind--;
            }
            index--;
        }
        return result;
    }
}
