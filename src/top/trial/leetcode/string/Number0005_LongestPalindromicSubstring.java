package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 
 * 示例 1：
 * 
 * 输入: "babad" 输出: "bab" 注意: "aba" 也是一个有效答案。
 * 
 * 示例 2：
 * 
 * 输入: "cbbd" 输出: "bb"
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0005_LongestPalindromicSubstring {

	@Test
	public void solution() {
		String s = "cbbd";
		String expected = "bb";
//		String result = getLongestPalindromicSubstringOne(s);
		String result = getLongestPalindromicSubstringTwo(s);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 优化版回文中心，将判断回文长度的方法提出
	 * 
	 * @param s
	 * @return
	 */
	private String getLongestPalindromicSubstringTwo(String s) {
		if (s == null || s.length() < 1)
			return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}

	/**
	 * 第一版，暴力法，遍历，取到最长回文子串，解法有问题，待复盘。 其实思路是官方题解的回文中心思路，只是代码未优化
	 * 
	 * @param s
	 * @return
	 */
	private String getLongestPalindromicSubstringOne(String s) {

		char[] chars = s.toCharArray();
		int maxLen = 1;// 默认初始长度1
		String result = s.substring(0, 1);// 默认初始回文子串是第一位

		// 长度为1的字符串，回文子串是其自身
		if (s.length() == 1)
			return s;

		int mid = s.length() / 2;
		int front = mid;
		int behind = mid + 1;

		while (behind + maxLen / 2 < s.length() && front - maxLen / 2 > 1) {

			for (int j = 1; front - j >= 0 && front + j < s.length(); j++) {// 奇数位
				if (chars[front - j] != chars[front + j]) {// 最长回文串，长度为2j-1
					if (2 * j - 1 > maxLen) {
						maxLen = 2 * j - 1;
						result = s.substring(front - j + 1, front + j);
						break;
					}
				}
			}
			for (int j = 1; front - j >= 0 && front + j <= s.length(); j++) {// 偶数位
				if (chars[front - j] != chars[front + j - 1]) {// 最长回文串，长度为2j-1
					if (2 * j - 2 > maxLen) {
						maxLen = 2 * j - 2;
						result = s.substring(front - j + 1, front + j - 1);
						break;
					}
				}
			}
			front--;
			for (int j = 1; behind - j >= 0 && behind + j < s.length(); j++) {// 奇数位
				if (chars[behind - j] != chars[behind + j]) {// 最长回文串，长度为2j-1
					if (2 * j - 1 > maxLen) {
						maxLen = 2 * j - 1;
						result = s.substring(behind - j + 1, behind + j);
						break;
					}
				}
			}
			for (int j = 1; behind - j >= 0 && behind + j < s.length(); j++) {// 奇数位
				if (chars[behind - j] != chars[behind + j - 1]) {// 最长回文串，长度为2j-1
					if (2 * j - 2 > maxLen) {
						maxLen = 2 * j - 2;
						result = s.substring(behind - j + 1, behind + j - 1);
						break;
					}
				}
			}
			behind++;

		}

		return result;
	}
}
