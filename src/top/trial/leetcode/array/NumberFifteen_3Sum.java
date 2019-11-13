package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * ？找出所有满足条件且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4],满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2]]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberFifteen_3Sum {

	@Test
	public void solution() {

		int[] nums = { -1, 0, 1, 2, -1, -4 };
		// int[] nums = { 3, 0, -2, -1, 1, 2 };

		List<List<Integer>> result = getAll3SumTwo(nums);

		System.out.println(result.toString());

		if ((result.get(0).get(0) == -1) && (result.get(0).get(1) == 0) && (result.get(1).get(0) == -1)
				&& (result.get(1).get(1) == -1)) {
			System.out.println("SUCCESS");
		}

	}

	/**
	 * 暴力法，三层循环嵌套，时间复杂度O(n*n*n)
	 * 
	 * @param nums
	 * @return
	 */
	private List<List<Integer>> getAll3SumOne(int[] nums) {

		List<List<Integer>> all3SumOne = new ArrayList<List<Integer>>();

		Boolean temp;

		int num = 0;
		int[] arrayTemp;
		List<Integer> listTemp = new ArrayList<Integer>();

		for (int i = 2; i < nums.length; i++) {
			for (int j = 1; j < i; j++) {
				for (int k = 0; k < j; k++) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						listTemp = new ArrayList<Integer>();
						temp = false;
						arrayTemp = new int[] { nums[i], nums[j], nums[k] };
						// 判重
						if (num > 0) {
							for (int m = 0; m < num; m++) {
								Arrays.sort(arrayTemp);
								listTemp.clear();
								listTemp.add(arrayTemp[0]);
								listTemp.add(arrayTemp[1]);
								listTemp.add(arrayTemp[2]);
								if (all3SumOne.get(m).get(0) == listTemp.get(0)
										&& all3SumOne.get(m).get(1) == listTemp.get(1)) {
									// 重了
									temp = true;
								}
							}
							if (!temp) {
								all3SumOne.add(listTemp);
								System.out.println(listTemp.toString());
								num++;
							}
						} else {
							Arrays.sort(arrayTemp);
							listTemp.clear();
							listTemp.add(arrayTemp[0]);
							listTemp.add(arrayTemp[1]);
							listTemp.add(arrayTemp[2]);
							all3SumOne.add(listTemp);
							System.out.println(listTemp.toString());
							num++;
						}

					}
				}
			}
		}

		return all3SumOne;
	}

	/**
	 * 从左开始遍历小于等于0的元素，然后双指针遍历右侧部分，寻找三个值总和为0的
	 * 
	 * @param nums
	 * @return
	 */
	private List<List<Integer>> getAll3SumTwo(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> temp;
		int start;
		int end;
		for (int i = 0; i < nums.length - 2; i++) {
			start = i + 1;
			end = nums.length - 1;
			while (nums[i] <= 0 && (i == 0 || (i > 0 && nums[i] != nums[i - 1])) && start < end) {
				if (nums[i] + nums[start] + nums[end] == 0) {
					// 去重
					temp = new ArrayList<Integer>();
					temp.add(nums[i]);
					temp.add(nums[start]);
					temp.add(nums[end]);
					result.add(temp);
					start++;
					while (start < nums.length && start > 1 && nums[start] == nums[start - 1]) {
						start++;
					}
					end--;
					while (end + 1 < nums.length && end > 1 && nums[end] == nums[end + 1]) {
						end--;
					}
				} else if (nums[i] + nums[start] + nums[end] < 0) {
					start++;
					while (start < nums.length && start > 1 && nums[start] == nums[start - 1]) {
						start++;
					}
				} else {
					end--;
					while (end < nums.length - 1 && end > 1 && nums[end] == nums[end + 1]) {
						end--;
					}
				}
			}
		}

		return result;
	}

}
