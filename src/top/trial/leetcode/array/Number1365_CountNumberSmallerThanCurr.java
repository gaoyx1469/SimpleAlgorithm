package top.trial.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * <p>
 * 提示：
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/26
 * @Version 1.0
 */
public class Number1365_CountNumberSmallerThanCurr {
    @Test
    public void solution() {
        int[] nums = {8, 1, 2, 2, 3};
        System.out.println(Arrays.toString(getCountOne(nums)));
    }

    /**
     * 简单算法：直接遍历拿到map，然后给key排序
     *
     * @param nums int[]
     * @return int[]
     */
    private int[] getCountOne(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Integer::compareTo);
        int sum = 0;
        for (Integer i : list) {
            int temp = map.get(i);
            map.put(i, sum);
            sum += temp;
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = map.get(nums[i]);
        }
        return result;
    }
}
