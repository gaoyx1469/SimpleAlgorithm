package top.trial.leetcode.string;

import org.junit.Test;

import java.util.*;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * <p>
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/22
 * @Version 1.0
 */
public class Number0763_PartitionLabels {
    @Test
    public void solution() {
        String S = "ababcbacadefegdehijhklij";
        List<Integer> result = partitionLabelsTwo(S);
        System.out.println(result);

    }

    /**
     * 参考官方那个题解，使用一维数组记录结束位置，并使用贪心算法
     *
     * @param S String
     * @return List<Integer>
     */
    private List<Integer> partitionLabelsTwo(String S) {
        int[] endIndexs = new int[26];
        int SLen = S.length();
        for (int i = 0; i < SLen; ++i) {
            endIndexs[S.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < SLen; ++i) {
            end = Math.max(end, endIndexs[S.charAt(i) - 'a']);//拿到阶段内最大end
            if (i == end) {//已走到阶段最大
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }

    /**
     * 自己写的方法，使用哈希表记录开始结束位置
     *
     * @param S String
     * @return List<Integer>
     */
    private List<Integer> partitionLabelsOne(String S) {
        Map<Character, Integer[]> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int SLen = S.length();
        for (int i = 0; i < SLen; ++i) {
            if (map.containsKey(S.charAt(i))) {
                map.get(S.charAt(i))[1] = i;
            } else {
                Integer[] indexs = new Integer[2];
                indexs[0] = i;
                indexs[1] = i;
                map.put(S.charAt(i), indexs);
            }
        }
        List<Integer[]> list = new LinkedList<>(map.values());
        list.sort(Comparator.comparingInt(a -> a[0]));
        Integer[] range = list.remove(0);
        while (!list.isEmpty()) {
            Integer[] temp = list.remove(0);
            if (range[1] < temp[0]) {
                result.add(range[1] - range[0] + 1);
                range[0] = temp[0];
                range[1] = temp[1];
            } else if (range[1] < temp[1]) {
                range[1] = temp[1];
            }
        }
        result.add(range[1] - range[0] + 1);
        return result;
    }
}
