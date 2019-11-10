package top.trial.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3] nums2 = [2]
 * 
 * 则中位数是 2.0 示例 2:
 * 
 * nums1 = [1, 2] nums2 = [3, 4]
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author gaoyx
 *
 */
public class NumberFour_MedianOfTwoSortedArrays {
	@Test
	public void solution() {
		int[] nums1 = { 1, 2, 6, 7 };
		int[] nums2 = { 3, 4, 5, 8 };
		float expected = 4.5f;

//		float result = methodOne(nums1, nums2);
		float result = methodTwo(nums1, nums2);

		assertEquals(expected, result, 0.0);
	}

	/**
	 * 普通解法是将两个数组一起排序，然后取中位数，但是时间复杂度不满足。 而一个有序数组使用二分法排序，可以满足O(log(n))
	 * 分别求两个的中位数，然后比较，大的一个所在数组可以去掉右一半（短数组长度的一半），小的一个所在数组可以去掉左一半（短数组长度的一半）。因此每次可以去掉短数组剩余长度总量的一半，因此可看作二分法。
	 * 代码凌乱，待重构！
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	private float methodOne(int[] nums1, int[] nums2) {

		// 存放nums1数组剩余内容的中位数
		float median1;

		// 存放nums2数组剩余内容的中位数
		float median2;

		// 让nums1作为短数组
		if (nums1.length > nums2.length) {
			int[] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
		}

		// 存放nums1数组剩余内容的起始下标
		int indexFrom1 = 0;

		// 存放nums1数组剩余内容的结束下标
		int indexTo1 = nums1.length - 1;

		// 存放nums2数组剩余内容的起始下标
		int indexFrom2 = 0;

		// 存放nums2数组剩余内容的结束下标
		int indexTo2 = nums2.length - 1;

		// nums1为空的情况
		if (indexTo1 == -1) {
			if (indexTo2 % 2 == 0) {
				return nums2[indexTo2 / 2];
			} else {
				return (nums2[(indexTo2 - 1) / 2] + nums2[(indexTo2 + 1) / 2]) / 2.0F;
			}
		}

		// 当两个数组没缩减完之前
		while ((indexTo1 - indexFrom1 > 1) && (indexTo2 - indexFrom2) > 1) {

			median1 = (indexFrom1 + indexTo1) % 2 == 0 ? nums1[(indexFrom1 + indexTo1) / 2]
					: (nums1[(indexFrom1 + indexTo1 - 1) / 2] + nums1[(indexFrom1 + indexTo1 + 1) / 2]) / 2.0F;
			median2 = (indexFrom2 + indexTo2) % 2 == 0 ? nums2[(indexFrom2 + indexTo2) / 2]
					: (nums2[(indexFrom2 + indexTo2 - 1) / 2] + nums2[(indexFrom2 + indexTo2 + 1) / 2]) / 2.0F;

			if (median1 == median2) {// 两个中位数相同，不用比了
				return median1;
			} else if (median1 < median2) {// num1中位数小
				if ((indexTo1 - indexFrom1) % 2 == 0) {// 小数组剩下单数个元素
					indexTo2 = indexTo2 - ((indexFrom1 + indexTo1) % 2 == 0 ? ((indexTo1 - indexFrom1) / 2)
							: (indexTo1 - indexFrom1 + 1) / 2);
					indexFrom1 = (indexFrom1 + indexTo1) % 2 == 0 ? (indexFrom1 + indexTo1) / 2
							: (indexFrom1 + indexTo1 + 1) / 2;
				} else {
					indexTo2 = indexTo2 - ((indexFrom1 + indexTo1) % 2 == 0 ? ((indexTo1 - indexFrom1) / 2) + 1
							: (indexTo1 - indexFrom1 + 1) / 2) + 1;
					indexFrom1 = (indexFrom1 + indexTo1) % 2 == 0 ? (indexFrom1 + indexTo1) / 2 - 1
							: (indexFrom1 + indexTo1 - 1) / 2;
				}

			} else {
				if ((indexTo1 - indexFrom1) % 2 == 0) {// 小数组剩下单数个元素
					indexFrom2 = indexFrom2 + ((indexFrom1 + indexTo1) % 2 == 0 ? ((indexTo1 - indexFrom1) / 2)
							: (indexTo1 - indexFrom1 + 1) / 2);
					indexTo1 = (indexFrom1 + indexTo1) % 2 == 0 ? (indexFrom1 + indexTo1) / 2
							: (indexFrom1 + indexTo1 - 1) / 2;
				} else {
					indexFrom2 = indexFrom2 + ((indexFrom1 + indexTo1) % 2 == 0 ? ((indexTo1 - indexFrom1) / 2) - 1
							: (indexTo1 - indexFrom1 - 1) / 2);
					indexTo1 = (indexFrom1 + indexTo1) % 2 == 0 ? (indexFrom1 + indexTo1) / 2 + 1
							: (indexFrom1 + indexTo1 + 1) / 2;
				}
			}

		}

		// 运行到此处，两个数组中某一个已经剩下1有效值
		if ((indexTo1 == indexFrom1) && (indexTo2 == indexFrom2)) {
			return (nums1[indexFrom1] + nums2[indexFrom2]) / 2.0F;
		} else if (indexTo1 == indexFrom1) {
			if ((indexFrom2 + indexTo2) % 2 == 0) {// 剩余单数
				if (nums1[indexFrom1] > nums2[(indexFrom2 + indexTo2) / 2 + 1]) {
					return (nums2[(indexFrom2 + indexTo2) / 2] + nums2[(indexFrom2 + indexTo2) / 2 + 1]) / 2.0F;
				} else if (nums1[indexFrom1] < nums2[(indexFrom2 + indexTo2) / 2 - 1]) {
					return (nums2[(indexFrom2 + indexTo2) / 2] + nums2[(indexFrom2 + indexTo2) / 2 - 1]) / 2.0F;
				} else {
					return (nums2[(indexFrom2 + indexTo2) / 2] + nums1[indexFrom1]) / 2.0F;
				}
			} else {
				if (nums1[indexFrom1] > nums2[(indexFrom2 + indexTo2 + 1) / 2]) {
					return nums2[(indexFrom2 + indexTo2 + 1) / 2];
				} else if (nums1[indexFrom1] < nums2[(indexFrom2 + indexTo2 - 1) / 2]) {
					return nums2[(indexFrom2 + indexTo2 - 1) / 2];
				} else {
					return nums1[indexFrom1];
				}
			}
		} else {// num1剩余两个有效数字
			if ((indexFrom2 + indexTo2) % 2 == 0) {// 剩余单数
				if (nums1[indexFrom1] >= nums2[(indexFrom2 + indexTo2) / 2]
						&& nums1[indexFrom1] < nums2[(indexFrom2 + indexTo2) / 2 + 1]) {
					return nums1[indexFrom1];
				} else if (nums1[indexTo1] <= nums2[(indexFrom2 + indexTo2) / 2]
						&& nums1[indexTo1] > nums2[(indexFrom2 + indexTo2) / 2 - 1]) {
					return nums1[indexTo1];
				} else if (nums1[indexFrom1] >= nums2[(indexFrom2 + indexTo2) / 2]
						&& nums1[indexFrom1] >= nums2[(indexFrom2 + indexTo2) / 2 + 1]) {
					return nums2[(indexFrom2 + indexTo2) / 2 + 1];
				} else if (nums1[indexTo1] <= nums2[(indexFrom2 + indexTo2) / 2]
						&& nums1[indexTo1] <= nums2[(indexFrom2 + indexTo2) / 2 - 1]) {
					return nums2[(indexFrom2 + indexTo2) / 2 - 1];
				} else {
					return nums2[(indexFrom2 + indexTo2) / 2];
				}
			} else {
				if (indexTo2 - indexFrom2 == 1) {
					if (nums1[indexFrom1] <= nums2[indexFrom2] && nums1[indexTo1] >= nums2[indexTo2]) {
						return (nums2[indexFrom2] + nums2[indexTo2]) / 2.0F;
					} else if (nums1[indexFrom1] >= nums2[indexFrom2] && nums1[indexTo1] <= nums2[indexTo2]) {
						return (nums1[indexFrom1] + nums1[indexTo1]) / 2.0F;
					} else if (nums1[indexFrom1] > nums2[indexFrom2]) {
						return (nums1[indexFrom1] + nums2[indexTo2]) / 2.0F;
					} else {
						return (nums1[indexTo1] + nums2[indexFrom2]) / 2.0F;
					}
				} else {
					if (nums1[indexFrom1] >= nums2[(indexFrom2 + indexTo2 + 3) / 2]) {
						return (nums2[(indexFrom2 + indexTo2 + 1) / 2] + nums2[(indexFrom2 + indexTo2 + 3) / 2]) / 2.0F;
					} else if (nums1[indexTo1] <= nums2[(indexFrom2 + indexTo2 - 3) / 2]) {
						return (nums2[(indexFrom2 + indexTo2 - 1) / 2] + nums2[(indexFrom2 + indexTo2 - 3) / 2]) / 2.0F;
					} else if (nums1[indexFrom1] <= nums2[(indexFrom2 + indexTo2 - 1) / 2]
							&& nums1[indexTo1] <= nums2[(indexFrom2 + indexTo2 + 1) / 2]
							&& nums1[indexTo1] >= nums2[(indexFrom2 + indexTo2 - 3) / 2]) {
						return (nums2[(indexFrom2 + indexTo2 - 1) / 2] + nums1[indexTo1]) / 2.0F;
					} else if (nums1[indexFrom1] <= nums2[(indexFrom2 + indexTo2 - 1) / 2]
							&& nums1[indexTo1] > nums2[(indexFrom2 + indexTo2 + 1) / 2]) {
						return (nums2[(indexFrom2 + indexTo2 - 1) / 2] + nums2[(indexFrom2 + indexTo2 + 1) / 2]) / 2.0F;
					} else if (nums1[indexFrom1] > nums2[(indexFrom2 + indexTo2 - 1) / 2]
							&& nums1[indexTo1] <= nums2[(indexFrom2 + indexTo2 + 1) / 2]) {
						return (nums1[indexFrom1] + nums1[indexTo1]) / 2.0F;
					} else {
						return (nums1[indexFrom1] + nums2[(indexFrom2 + indexTo2 + 1) / 2]) / 2.0F;
					}
				}

			}
		}
	}

	/**
	 * leetCode标准答案，研究研究
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	private float methodTwo(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		if (m > n) { // to ensure m<=n
			int[] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			if (i < iMax && nums2[j - 1] > nums1[i]) {
				iMin = i + 1; // i is too small
			} else if (i > iMin && nums1[i - 1] > nums2[j]) {
				iMax = i - 1; // i is too big
			} else { // i is perfect
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = nums2[j - 1];
				} else if (j == 0) {
					maxLeft = nums1[i - 1];
				} else {
					maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
				}
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}

				int minRight = 0;
				if (i == m) {
					minRight = nums2[j];
				} else if (j == n) {
					minRight = nums1[i];
				} else {
					minRight = Math.min(nums2[j], nums1[i]);
				}

				return (maxLeft + minRight) / 2.0F;
			}
		}
		return 0.0F;
	}
}
