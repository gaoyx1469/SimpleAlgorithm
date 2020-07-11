package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.*;

/**
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number0315_CountOfSmallerNumbersAfterSelf {

    private int[] c;
    private int[] a;

    @Test
    public void solution() {
        int[] nums = {5, 2, 6, 1};
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);
        expected.add(1);
        expected.add(0);
        List<Integer> result = countOfSmallerNumbersAfterSelfOne(nums);
        result = countOfSmallerNumbersAfterSelfTwo(nums);
        System.out.println(ArrayListUtil.getOneDimensionalStringLikeArray(expected));
        System.out.println(ArrayListUtil.getOneDimensionalStringLikeArray(result));
        Assert.assertEquals(ArrayListUtil.getOneDimensionalStringLikeArray(expected), ArrayListUtil.getOneDimensionalStringLikeArray(result));
    }

    /**
     * 试试别的方式？参考力扣官方题解
     * <p>
     * TODO 还要再看看，有点难
     *
     * @param nums 原始数组
     * @return List<Integer>
     */
    private List<Integer> countOfSmallerNumbersAfterSelfTwo(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        discretization(nums);//填充a数组，要求是去重并排序，时间复杂度n*log(n)
        c = new int[len + 5];//构造c数组，长度为len+5，值全为0，后续作为树状计数数组使用
        for (int i = nums.length - 1; i >= 0; --i) {//遍历nums
            int id = Arrays.binarySearch(a, nums[i]) + 1;//在a中二分查找nums[i]的位置
            result.add(query(id - 1));//查找前面遍历过的比当前值小的数值的个数，并存入result中
            update(id);//更新数组c
        }
        Collections.reverse(result);
        return result;
    }

    private int query(int pos) {//log(n)的时间复杂度
        int ret = 0;
        while (pos > 0) {
            ret += c[pos];
            pos -= lowBit(pos);
        }
        return ret;
    }

    private void update(int pos) {//log(n)的时间复杂度
        while (pos < c.length) {
            c[pos] += 1;
            pos += lowBit(pos);
        }
    }

    private int lowBit(int x) {//保留二进制下最后出现的1的位置
        return x & (-x);
    }

    /**
     * 生成一个有序的不重复的且包含numns内所有值的数组a
     *
     * @param nums 原始数组
     */
    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }

    /**
     * 暴力法？时间复杂度n^2,空间复杂度n，太慢了，不靠谱
     *
     * @param nums 原始整数数组
     * @return List<Integer>
     */
    private List<Integer> countOfSmallerNumbersAfterSelfOne(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int[] res = new int[len];
        for (int i = len - 2; i >= 0; --i) {
            int count = 0;
            for (int j = i + 1; j < len; ++j) {
                if (nums[i] > nums[j])
                    ++count;
            }
            res[i] = count;
        }
        for (int i = 0; i < len; i++) {
            result.add(res[i]);
        }
        return result;
    }
}
