package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * 示例 2：
 * 输入：arr = [1,2]
 * 输出：false
 * 示例 3：
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 * <p>
 * 提示：
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/28
 * @Version 1.0
 */
public class Number1207_UniqueNumberOfOccurrences {
    @Test
    public void solution() {
        int[] arr = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        Assert.assertTrue(isUniqueOne(arr));
    }

    private boolean isUniqueOne(int[] arr) {
        Map<Integer, Integer> occurNum = new HashMap<>();
        for (int num : arr) {
            occurNum.put(num, occurNum.getOrDefault(num, 0) + 1);
        }
        /*Map<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : occurNum.entrySet()) {
            if (map.containsKey(entry.getValue()))
                return false;
            else
                map.put(entry.getValue(), entry.getKey());
        }*/
        //有空研究下，为何用set更慢了
        Set<Integer> set = new HashSet<>();
        for (int i : occurNum.values()) {
            if (!set.add(i))
                return false;
        }
        return true;
    }
}
