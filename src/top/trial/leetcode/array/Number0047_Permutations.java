package top.trial.leetcode.array;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/18
 * @Version 1.0
 */
public class Number0047_Permutations {
    @Test
    public void solution() {
        int[] nums = {1, 1, 2};
        ArrayListUtil.getTwoDimensionalSysoLikeArray(permuteUniqueOne(nums));
    }

    List<List<Integer>> result = new ArrayList<>();
    Map<Integer, Integer> numMap = new HashMap<>();
    List<Integer> tempList = new ArrayList<>();
    Set<Integer> keySet;

    private List<List<Integer>> permuteUniqueOne(int[] nums) {
        //数据入map
        for (int num : nums) {
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }
        //System.out.println(numMap);
        keySet = numMap.keySet();
        permuteUniqueX(nums);
        return result;
    }

    private void permuteUniqueX(int[] nums) {
        if (tempList.size() == nums.length) {
            List<Integer> list = new ArrayList<>(tempList);
            result.add(list);
            return;
        }
        for (Integer i : keySet) {
            if (numMap.getOrDefault(i, 0) > 0) {//可选
                tempList.add(i);
                numMap.put(i, numMap.get(i) - 1);
                permuteUniqueX(nums);
                tempList.remove(tempList.size() - 1);
                numMap.put(i, numMap.get(i) + 1);
            }
        }
    }
}
