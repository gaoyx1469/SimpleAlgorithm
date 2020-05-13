package top.trial.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */

public class Number0042_TrappingRainWater {

	@Test
	public void solution() {
		// int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		// int expected = 6;

		// int[] height = { 2, 0, 2 };
		// int expected = 2;

		int[] height = { 6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3 };
		int expected = 83;

		// int result = getTrappingRainWaterOne(height);
		int result = getTrappingRainWaterTwo(height);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 参考力扣官方题解双指针法
	 * 
	 * @param height
	 * @return
	 */
	private int getTrappingRainWaterTwo(int[] height) {

		// 每一个沟都从升变降一直到下个升变降，两个变化点取小的一个为水位线，计算沟中所有小于水位线的点与水位线的差值的和
		int rtValue = 0;
		// 储水效果是中间高，两边低
		int left = 0;
		int right = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;

		while (left < right) {

			// 双指针，右比左大时，移动左指针，否则移动右指针
			if (height[left] < height[right]) {

				if (height[left] < leftMax) {// 左指针现在的位置小于左侧最高位
					rtValue += leftMax - height[left];
				} else {// 左指针现在的位置大于左侧最高位，更新左侧最高位
					leftMax = height[left];
				}
				left++;
			} else {
				if (height[right] < rightMax) {// 右指针现在的位置小于右侧最高位
					rtValue += rightMax - height[right];
				} else {// 右指针现在的位置大于右侧最高位，更新右侧最高位
					rightMax = height[right];
				}
				right--;
			}

		}

		return rtValue;
	}

	/**
	 * 没有时间复杂度和空间复杂度的限制，此思路是错误的，改变方法
	 * 
	 * @param height
	 * @return
	 */
	@SuppressWarnings("unused")
	private int getTrappingRainWaterOne(int[] height) {

		// 每一个沟都从升变降一直到下个升变降，两个变化点取小的一个为水位线，计算沟中所有小于水位线的点与水位线的差值的和
		int heightLength = height.length;
		int rtValue = 0;

		// 小于3个长度的数组无法储水
		if (heightLength < 3) {
			return rtValue;
		}

		// 用于储存所有顶点
		Map<Integer, Integer> points = new HashMap<Integer, Integer>();

		// 1.找到所有升变降
		for (int i = 0; i < heightLength; i++) {

			if (i == 0) {// 第一个点特殊处理
				if (height[i] > height[i + 1]) {
					points.put(i, height[i]);
				}
			} else if (i == heightLength - 1) {
				if (height[i] > height[i - 1]) {
					points.put(i, height[i]);
				}
			} else if (height[i - 1] <= height[i] && height[i + 1] < height[i]) {// 得到顶点，如果多个等高点在一起，取最后一个
				points.put(i, height[i]);
			}
		}

		// 一个峰值无法储水
		if (points.size() == 1) {
			return rtValue;
		}

		// 2.若一个升降点比其前一个小，比其后一个也小，忽略它
		// 3.两个升变降间的雨水求和
		Boolean firstFlag = true;
		Entry<Integer, Integer> lastEntry = null;
		Entry<Integer, Integer> lastlastEntry = null;
		Set<Entry<Integer, Integer>> set = points.entrySet();
		for (Entry<Integer, Integer> entry : set) {

			if (firstFlag) {// 第一个
				firstFlag = false;
				lastEntry = entry;
				continue;
			}

			if (lastlastEntry == null) {// 第二个
				lastlastEntry = lastEntry;
				lastEntry = entry;
				continue;
			}

			if (lastEntry.getValue() < lastlastEntry.getValue() && lastEntry.getValue() < entry.getValue()) {// lastEntry比左右低，无效
				lastEntry = entry;
			} else {
				rtValue = rtValue + getRainWater(height, lastlastEntry, lastEntry);
				lastlastEntry = lastEntry;
				lastEntry = entry;
			}

		}
		rtValue = rtValue + getRainWater(height, lastlastEntry, lastEntry);
		return rtValue;
	}

	private int getRainWater(int[] height, Entry<Integer, Integer> front, Entry<Integer, Integer> behind) {

		int level = Math.min(front.getValue(), behind.getValue());
		int sum = 0;

		for (int i = front.getKey() + 1; i < behind.getKey(); i++) {
			if (level - height[i] > 0) {
				sum = sum + level - height[i];
			}
		}
		return sum;
	}
}
