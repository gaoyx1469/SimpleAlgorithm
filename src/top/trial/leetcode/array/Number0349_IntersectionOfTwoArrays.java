package top.trial.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *  
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/2
 * @Version 1.0
 */
public class Number0349_IntersectionOfTwoArrays {
    @Test
    public void solution() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersectionOne(nums1, nums2)));
    }

    /**
     * @param nums1 int[]
     * @param nums2 int[]
     * @return int[]
     */
    private int[] intersectionOne(int[] nums1, int[] nums2) {
        Set<Integer> result = new HashSet<>();

        if (nums1.length > nums2.length)
            return intersectionOne(nums2, nums1);

        Set<Integer> set2 = new HashSet<>();
        for (int n : nums2)
            set2.add(n);

        for (int n : nums1) {
            if (set2.contains(n))
                result.add(n);
        }

        return result.stream().mapToInt(Number::intValue).toArray();
    }
}
