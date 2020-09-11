package top.trial.leetcode.math;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/9/11
 * @Version 1.0
 */
public class Number0216_CombinationSum {
    @Test
    public void solution() {
        int k = 3;
        int n = 9;
        ArrayListUtil.getTwoDimensionalSysoLikeArray(combinationSumOne(k, n));
    }

    private final List<List<Integer>> results = new ArrayList<>();
    private final List<Integer> temp = new ArrayList<>();
    private int[] minVal;
    private int[] maxVal;

    private List<List<Integer>> combinationSumOne(int k, int n) {

        if (k >= 1 && k <= 9) {//k取值合理
            minVal = new int[k];
            maxVal = new int[k];
            minVal[0] = 1;
            maxVal[0] = 9;
            for (int i = 1; i < k; ++i) {
                minVal[i] = minVal[i - 1] + i + 1;
                maxVal[i] = maxVal[i - 1] - i + 9;
            }
            combinationSum(k, n);
        }
        return results;
    }

    private void combinationSum(int k, int n) {
        if (n < minVal[k - 1] || n > maxVal[k - 1])//n取值不合理
            return;
        if (k == 1 && n > 0 && n < 10) {//只剩一个可选值
            List<Integer> list = new ArrayList<>(temp);
            list.add(n);
            results.add(list);
            return;
        }
        int maxNum = 10;
        if (!temp.isEmpty()) {
            maxNum = temp.get(temp.size() - 1);//当前已选最小值
        }
        int maxVal = Math.min(n - minVal[k - 2], maxNum - 1);//最大可选取值

        for (int i = maxVal; i >= k && canChoose(i - 1, k - 1, n - i); --i) {
            temp.add(i);
            combinationSum(k - 1, n - i);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 计算最大为i的k个连续数，够不够组成n
     */
    private boolean canChoose(int i, int k, int n) {

        int start = i;
        int sum = 0;
        for (int x = 0; x < k; ++x, start--) {
            sum += start;
        }
        return sum >= n;
    }
}
