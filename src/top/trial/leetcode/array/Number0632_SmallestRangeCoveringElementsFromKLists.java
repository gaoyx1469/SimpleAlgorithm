package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * <p>
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 * <p>
 * 示例 1:
 * <p>
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出: [20,24]
 * 解释:
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 注意:
 * <p>
 * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 * 1 <= k <= 3500
 * -10^5 <= 元素的值 <= 10^5
 * 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/1
 * @Version 1.0
 */
public class Number0632_SmallestRangeCoveringElementsFromKLists {
    @Test
    public void solution() {
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> num;
        int[][] in = {{11, 38, 83, 84, 84, 85, 88, 89, 89, 92}, {28, 61, 89}, {52, 77, 79, 80, 81}, {1, 25, 26, 26, 26, 27}, {9, 83, 85, 90}, {84, 85, 87}, {26, 68, 70, 71}, {36, 40, 41, 42, 45}, {-34, 21}, {-28, -28, -23, 1, 13, 21, 28, 37, 37, 38}, {-74, 1, 2, 22, 33, 35, 43, 45}, {54, 96, 98, 98, 99}, {43, 54, 60, 65, 71, 75}, {43, 46}, {50, 50, 58, 67, 69}, {7, 14, 15}, {78, 80, 89, 89, 90}, {35, 47, 63, 69, 77, 92, 94}};

        for (int[] is : in) {
            num = new ArrayList<>();
            for (int i : is)
                num.add(i);
            nums.add(num);
        }

        int[] expected = {15, 84};
        int[] result = smallestRange(nums);
        Assert.assertArrayEquals(expected, result);
    }

    /**
     * 我的思路：先全部排序，然后从后向前遍历，维护一个数组，记录出现了哪些个k及数的最小坐标，当k全出现，记录坐标差值，继续遍历知道找出最小坐标差值
     * <p>
     * 官方题解有另两种解法：堆|哈希表+滑动窗口
     *
     * @param nums List<List<Integer>>
     * @return int[]
     */
    private int[] smallestRange(List<List<Integer>> nums) {

        //分子操作有：排序、遍历、判断k全出现、计算出现的k的最大坐标差
        int kLen = nums.size();
        //排序
        Map<Integer, LinkedList<Integer>> sortedNums = new HashMap<>();
        while (true) {
            //找到最大值max
            int max = Integer.MIN_VALUE;
            for (List<Integer> num : nums) {
                if (!num.isEmpty())
                    max = Math.max(max, num.get(num.size() - 1));
            }
            if (max == Integer.MIN_VALUE)
                break;
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < nums.size(); ++i) {
                if (!nums.get(i).isEmpty() && nums.get(i).get(nums.get(i).size() - 1) == max) {
                    nums.get(i).remove(nums.get(i).size() - 1);
                    list.add(i);
                }
            }
            if (sortedNums.containsKey(max))
                list.addAll(sortedNums.get(max));
            sortedNums.put(max, list);
        }

        int[] index = new int[kLen];
        Arrays.fill(index, Integer.MIN_VALUE);

        int[] result = new int[2];
        result[0] = 1;
        result[1] = Integer.MAX_VALUE;

        Integer[] allNum = new Integer[sortedNums.size()];
        sortedNums.keySet().toArray(allNum);
        Arrays.sort(allNum);

        for (int n : allNum) {
            for (int i : sortedNums.get(n)) {
                index[i] = n;
            }

            //判断是否都填充完
            if (isFull(index)) {
                int[] range = getMaxRange(index);
                if (range[1] - range[0] < result[1] - result[0])
                    result = range;
            }
        }

        return result;
    }

    private int[] getMaxRange(int[] index) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i : index) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        return new int[]{min, max};
    }

    private boolean isFull(int[] index) {
        for (int i : index) {
            if (i == Integer.MIN_VALUE)
                return false;
        }
        return true;
    }
}
