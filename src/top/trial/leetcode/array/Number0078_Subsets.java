package top.trial.leetcode.array;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/20
 * @Version 1.0
 */
public class Number0078_Subsets {
    @Test
    public void solution() {
        int[] nums = {1, 2, 3};
        ArrayListUtil.getTwoDimensionalSysoLikeArray(getSubsets(nums));
    }

    private List<List<Integer>> getSubsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        int len = nums.length;
        int max = 1 << len;

        for (int i = 0; i < max; ++i) {
            List<Integer> tempList = new ArrayList<>();
            for (int j = 0; j < len; ++j) {
                if ((i & (1 << j)) != 0) {
                    tempList.add(nums[j]);
                }
            }
            results.add(tempList);
        }

        return results;
    }
}
