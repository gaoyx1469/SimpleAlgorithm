package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 * 提示：
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/12
 * @Version 1.0
 */
public class Number0922_SortArrayByParity {
    @Test
    public void solution() {
        int[] A = {4, 2, 5, 7};
        System.out.println(Arrays.toString(sortArrayByParity(A)));
    }

    private int[] sortArrayByParity(int[] A) {
        int len = A.length;
        if (len <= 1)
            return A;
        int[] result = new int[len];
        int index1 = 0;
        int index2 = 1;
        for (int i : A) {
            if (i % 2 == 0) {
                result[index1] = i;
                index1 = index1 + 2;
            } else {
                result[index2] = i;
                index2 = index2 + 2;
            }
        }
        return result;
    }
}
