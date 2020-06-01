package top.trial.leetcode.array;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 * 
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有
 * 最多 的糖果数目。
 * 
 * 输入：candies = [4,2,1,1,2], extraCandies = 1 输出：[true,false,false,false,false]
 * 解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number1431_KidsWiththeGreatestNumberofCandies {

	@Test
	public void solution() {
		int[] candies = { 4, 2, 1, 1, 2 };
		int extraCandies = 1;
		List<Boolean> expected = new ArrayList<Boolean>();
		expected.add(true);
		expected.add(false);
		expected.add(false);
		expected.add(false);
		expected.add(false);

		// List<Boolean> result = getKidsWiththeGreatestNumberofCandiesOne(candies,
		// extraCandies);
		List<Boolean> result = getKidsWiththeGreatestNumberofCandiesTwo(candies, extraCandies);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 方法二，看看能否优化代码结构
	 * 
	 * @param candies
	 * @param extraCandies
	 * @return
	 */
	private List<Boolean> getKidsWiththeGreatestNumberofCandiesTwo(int[] candies, int extraCandies) {
		return null;
	}

	/**
	 * 方法一，暴力破解
	 * 
	 * @param candies
	 * @param extraCandies
	 * @return
	 */
	private List<Boolean> getKidsWiththeGreatestNumberofCandiesOne(int[] candies, int extraCandies) {

		int max = 0;

		// 使用增强for获取candies中最大值
		for (int candy : candies)
			max = Math.max(max, candy);

		List<Boolean> result = new ArrayList<Boolean>();

		// 计算max - extraCandies，使得循环中不用每次进行运算
		int trueMin = max - extraCandies;

		// 使用增强for
		for (int candy : candies)
			result.add(candy >= trueMin);

		return result;
	}

}
