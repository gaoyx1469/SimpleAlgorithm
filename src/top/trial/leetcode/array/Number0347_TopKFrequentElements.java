package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/7
 * @Version 1.0
 */
public class Number0347_TopKFrequentElements {
    @Test
    public void solution() {
        int[] nums = {3, 0, 1, 0};
        int k = 1;
        System.out.println(Arrays.toString(topKFrequentOne(nums, k)));
    }

    /**
     * 有时间复杂度要求-O(n log n)
     *
     * @param nums int[]
     * @param k    int
     * @return int[]
     */
    private int[] topKFrequentOne(int[] nums, int k) {
        // 顺序遍历，拿到次数
        Map<Integer, Integer> map = new HashMap<>();// 存出现次数，nums值为key，出现次数为value
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        Integer[] values = new Integer[map.size()];// 存出现次数的数组
        map.values().toArray(values);// 将出现次数存入数组
        Arrays.sort(values);//出现次数排序（从小到大）
        int n = values[values.length - k];//拿到最小次数
        int index = 0;
        int[] results = new int[k];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= n)
                results[index++] = entry.getKey();
        }
        return results;
    }
}
