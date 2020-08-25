package top.trial.leetcode.array;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.*;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * <p>
 * 示例:
 * <p>
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 * <p>
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 * 通过次数11,024提交次数21,519
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/25
 * @Version 1.0
 */
public class Number0491_IncreasingSubsequences {
    @Test
    public void solution() {

        int[] nums = {8, 9, 1, 1, 1, 1, 1};
        List<List<Integer>> result = findSubsequencesOne(nums);
        ArrayListUtil.getTwoDimensionalSysoLikeArray(result);

    }

    private List<List<Integer>> findSubsequencesOne(int[] nums) {

        if (nums.length < 2)
            return new ArrayList<>();


        List<List<Integer>>[] results = new ArrayList[nums.length - 1];


        for (int i = nums.length - 2; i >= 0; --i) {
            results[i] = getSubsequencesOne(i, nums, results);
        }

        return results[0];
    }

    /**
     * @param index   int
     * @param nums    int
     * @param results List<List<Integer>>[]
     * @return List<List < Integer>>
     */
    private List<List<Integer>> getSubsequencesOne(int index, int[] nums, List<List<Integer>>[] results) {

        //获取nums数组从i开始到最后的递增子序列
        //寻找从i+1开始，大于等于nums[i]的数
        int nextIndex = -1;
        for (int i = index + 1; i < nums.length; ++i) {
            if (nums[i] >= nums[index]) {
                nextIndex = i;
                break;
            }
        }
        if (nextIndex == -1) {//nums[i]后面没有大于等于nums[i]的数
            return index == nums.length - 2 ? new ArrayList<>() : results[index + 1];
        } else {//nums[i]后第一个比他大的是nums[nextIndex]
            List<List<Integer>> lists = new ArrayList<>();

            //拼接nums[index]与results[nextIndex]
            if (nextIndex < nums.length - 1 && results[nextIndex] != null && !results[nextIndex].isEmpty()) {
                for (List<Integer> l : results[nextIndex]) {
                    if (nums[index] <= l.get(0)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[index]);
                        list.addAll(l);
                        lists.add(list);
                    }
                }
            }

            if (index < nums.length - 2)
                lists.addAll(results[index + 1]);

            //单拼nums[index]与nums[nextIndex]及后面比nums[index]大的
            for (int i = nextIndex; i < nums.length; ++i) {

                if (nums[i] >= nums[index] && (i == index + 1 || nums[i] != nums[i - 1])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[index]);
                    list.add(nums[i]);
                    lists.add(list);
                }
            }

            //去重
            Set<List<Integer>> set = new TreeSet<>((l1, l2) -> {
                if (l1.size() == l2.size()) {
                    for (int i = 0; i < l1.size(); ++i) {
                        if (l1.get(i) != l2.get(i))
                            return l1.get(i) - l2.get(i);
                    }
                    return 0;
                } else
                    return l1.size() - l2.size();
            });

            set.addAll(lists);
            lists.clear();
            lists.addAll(set);

            return lists;
        }
    }
}
