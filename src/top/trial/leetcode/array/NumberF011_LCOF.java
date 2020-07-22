package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/22
 * @Version 1.0
 */
public class NumberF011_LCOF {
    @Test
    public void solution() {
        int[][] numbers = {{3, 4, 5, 1, 2}, {2, 2, 2, 0, 1}};
        int[] expected = {1, 0};
        int[] results = new int[2];
        for (int i = 0; i < numbers.length; ++i) {
            //results[i] = minArrayOne(numbers[i]);
            results[i] = minArrayTwo(numbers[i]);
        }
        Assert.assertArrayEquals(expected, results);
    }

    /**
     * 参考官方题解，使用二分法
     *
     * @param numbers int[]
     * @return numbers[low]9
     */
    private int minArrayTwo(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;//取中点
            if (numbers[pivot] < numbers[high]) {//中点的值比最右点小，说明最小点在中点或中点左边
                high = pivot;//
            } else if (numbers[pivot] > numbers[high]) {//中点的值比最右点大，说明最小点在中点右边
                low = pivot + 1;
            } else {//中点值跟最右点一样，最右点往前挪一位
                high -= 1;
            }
        }
        return numbers[low];
    }

    /**
     * 从头遍历是不是过于简单，复杂度O(n)
     *
     * @param numbers int[]
     * @return result
     */
    private int minArrayOne(int[] numbers) {
        int result = numbers[0];

        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] < result) {
                result = numbers[i];
                break;
            }
        }

        return result;
    }
}
