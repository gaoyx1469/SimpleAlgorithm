package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 
 * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberEighteen_4Sum {

	@Test
	public void solution() {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		List<Integer> ex1 = new ArrayList<Integer>();
		ex1.add(-1);
		ex1.add(0);
		ex1.add(0);
		ex1.add(1);

		List<Integer> ex2 = new ArrayList<Integer>();
		ex2.add(-2);
		ex2.add(-1);
		ex2.add(1);
		ex2.add(2);

		List<Integer> ex3 = new ArrayList<Integer>();
		ex3.add(-2);
		ex3.add(0);
		ex3.add(0);
		ex3.add(2);

		expected.add(ex1);
		expected.add(ex2);
		expected.add(ex3);

		List<List<Integer>> result = get4SumOne(nums, target);

		Assert.assertEquals(expected, result);

	}

	/**
	 * 三数之和问题的基础上求解4数之和
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private List<List<Integer>> get4SumOne(int[] nums, int target) {
		
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		for(int i = 0; i < nums.length - 3; i++) {
			result.addAll(getAll3SumTwo(Arrays.copyOfRange(nums,i,nums.length-1), nums[i]));
		}
		
		return result;
	}
	
	/**
	 * 从左开始遍历小于等于0的元素，然后双指针遍历右侧部分，寻找三个值总和为0的，时间复杂度O(n*n)
	 * 
	 * @param nums
	 * @return
	 */
	private List<List<Integer>> getAll3SumTwo(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> temp;
		int start;
		int end;
		for (int i = 0; i < nums.length - 2; i++) {
			start = i + 1;
			end = nums.length - 1;
			while (nums[i] <= (0-target) && (i == 0 || (i > 0 && nums[i] != nums[i - 1])) && start < end) {
				if (nums[i] + nums[start] + nums[end] == (0-target)) {
					// 去重
					temp = new ArrayList<Integer>();
					temp.add(target);
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
				} else if (nums[i] + nums[start] + nums[end] < (0-target)) {
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
