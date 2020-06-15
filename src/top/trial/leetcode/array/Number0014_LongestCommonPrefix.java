package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"] 输出: "fl"
 * 
 * 示例 2:
 * 
 * 输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0014_LongestCommonPrefix {

	@Test
	public void solution() {
		String[] strs = { "flower", "flow", "flight" };
		String expected = "fl";
		String result = getLongestCommonPrefixOne(strs);
		Assert.assertEquals(expected, result);

		// TODO 根据官方题解，还有横向比较、纵向比较、字典树、二分法等
	}

	/**
	 * 暴力法
	 * 
	 * @param strs
	 * @return
	 */
	private String getLongestCommonPrefixOne(String[] strs) {

		if (strs.length == 0)
			return "";

		String result = "";
		String temp = "";
		String minLenStr = strs[0];

		for (int i = 1; i < strs.length; i++) {
			if (minLenStr.length() > strs[i].length())
				minLenStr = strs[i];
		}

		for (int i = 0; i < minLenStr.length(); i++) {
			temp = minLenStr.substring(0, i + 1);
			for (int j = 0; j < strs.length; j++) {
				if (!strs[j].startsWith(temp))
					return result;
			}
			result = temp;

		}

		return result;
	}

}
