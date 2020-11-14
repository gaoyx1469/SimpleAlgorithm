package top.trial.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * 给你两个数组，arr1 和 arr2，
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * <p>
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *  
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/14
 * @Version 1.0
 */
public class Number1122_RelativeSortArray {
    @Test
    public void solution() {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        System.out.println(Arrays.toString(sortArray(arr1, arr2)));
    }

    private int[] sortArray(int[] arr1, int[] arr2) {

        int len1 = arr1.length;
        int len2 = arr2.length;

        int[] result = new int[len1];

        Set<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map1 = new HashMap<>();//存arr1中各元素出现的次数
        for (int i : arr1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
            set.add(i);
        }
        Map<Integer, Integer> map2 = new HashMap<>();//存arr2，索引是key，值是value
        for (int i = 0; i < len2; ++i) {
            map2.put(i, arr2[i]);
            set.remove(arr2[i]);
        }


        int index = 0;

        //放arr2中有的
        for (int i = 0; i < len2; ++i) {
            int val = map2.get(i);
            int num = map1.get(val);
            while (num > 0) {
                result[index] = val;
                num--;
                index++;
            }
        }

        //放arr2中没有的
        for (int i : set) {
            int num = map1.get(i);
            while (num > 0) {
                result[index] = i;
                num--;
                index++;
            }
        }

        return result;
    }
}
