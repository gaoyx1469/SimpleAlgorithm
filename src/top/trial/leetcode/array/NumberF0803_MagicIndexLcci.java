package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 * <p>
 * 示例1:
 * <p>
 * 输入：nums = [0, 2, 3, 4, 5]
 * 输出：0
 * 说明: 0下标的元素为0
 * 示例2:
 * <p>
 * 输入：nums = [1, 1, 1]
 * 输出：1
 * 提示:
 * <p>
 * nums长度在[1, 1000000]之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/magic-index-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/31
 * @Version 1.0
 */
public class NumberF0803_MagicIndexLcci {
    @Test
    public void solution() {
        int[] nums = {0, 2, 3, 4, 5};
        int expected = 0;
        int result = magicIndexLcciOne(nums);
        Assert.assertEquals(expected, result);
    }

    /**
     * 暴力法也挺好啊，官方还提供了二分剪枝法，取中间值，若是魔术索引，则最小魔术索引在左边或中间，继续搜左边；若不是，先搜左边，没有再搜右边
     *
     * @param nums int[]
     * @return int
     */
    private int magicIndexLcciOne(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums[i])
                return i;
        }
        return -1;
    }
}
