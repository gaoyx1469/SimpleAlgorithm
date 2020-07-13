package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * <p>
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/13
 * @Version 1.0
 */
public class Number0350_IntersectionofTwoArraysII {

    @Test
    public void solution() {
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};
        int[] expected = new int[]{4, 9};
        //int[] result = IntersectionofTwoArraysIIOne(nums1, nums2);
        int[] result = IntersectionofTwoArraysIITwo(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 参考官方题解，使用hash表，不用排序
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] IntersectionofTwoArraysIITwo(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 || len2 == 0) {
            return new int[]{};
        }

        //保证nums1的长度不大于nums2的长度
        if (len1 > len2) {
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }

        //存表操作
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        int resultLen = 0;
        //查表操作
        for (int i = 0; i < nums2.length && !map.isEmpty(); ++i) {
            if (map.containsKey(nums2[i])) {
                nums2[resultLen] = nums2[i];
                ++resultLen;
                if (map.get(nums2[i]) > 1)
                    map.put(nums2[i], map.get(nums2[i]) - 1);
                else
                    map.remove(nums2[i]);
            }
        }


        return Arrays.copyOfRange(nums2, 0, resultLen);
    }

    /**
     * 不考虑输出顺序？
     * 直接排序，然后遍历？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] IntersectionofTwoArraysIIOne(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 || len2 == 0) {
            return new int[]{};
        }

        //保证nums1的长度不大于nums2的长度
        if (len1 > len2) {
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }

        int resultLen = 0;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j])
                ++i;
            else if (nums1[i] > nums2[j])
                ++j;
            else {
                nums1[resultLen] = nums1[i];
                ++resultLen;
                ++i;
                ++j;
            }
        }

        return Arrays.copyOfRange(nums1, 0, resultLen);
    }
}
