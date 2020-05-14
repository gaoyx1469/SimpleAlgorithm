package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * 示例 2:
 * 
 * 输入: "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 
 * 示例 3:
 * 
 * 输入: "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。  
 * 
 * 请注意，你的答案必须是子串的长度，"pwke" 是一个子序列，不是子串。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0003_LongestSubstringWithoutRepeatingCharacters {

	@Test
	public void solution() {
		String str = "pwwkew";
		int expected = 3;

		int result = getLongestSubstringWithoutRepeatingCharactersOne(str);

		Assert.assertEquals(expected, result);
	}

	/**
	 * 笨办法试验
	 * 
	 * @param str
	 * @return
	 */
	private int getLongestSubstringWithoutRepeatingCharactersOne(String str) {

		int start = 0;
		int end = 0;
		int maxLen = 1;
		char[] strs = str.toCharArray();
		
		if(strs.length == 0) {
			return 0;
		}

		for (int i = 1; i < str.length(); i++) {

			// 循环比较串中字符
			for (int j = end; j >= start; j--) {
				if (strs[j] == strs[i]) {// 有重复的了
					start = j + 1;// start从重复位的下一位开始
					break;
				}
			}
			end = i;// end位取最后一位
			maxLen = Math.max(maxLen, (end - start + 1));
		}

		return maxLen;
	}
}
