package top.trial.leetcode.string;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * 
 * '?' 可以匹配任何单个字符。 '*' 可以匹配任意字符串（包括空字符串）。 两个字符串完全匹配才算匹配成功。
 * 
 * 说明:
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 
 * 示例 1:
 * 
 * 输入: s = "aa" p = "a" 输出: false 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 示例 2:
 * 
 * 输入: s = "aa" p = "*" 输出: true 解释: '*' 可以匹配任意字符串。
 * 
 * 示例 3:
 * 
 * 输入: s = "cb" p = "?a" 输出: false 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 
 * 示例 4:
 * 
 * 输入: s = "adceb" p = "*a*b" 输出: true 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串
 * "dce".
 * 
 * 示例 5:
 * 
 * 输入: s = "acdcb" p = "a*c?b" 输出: false
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 
 * @author Gaoyx-vm
 *
 */
public class Number0044_WildcardMatching {

	@Test
	public void solution() {
		String s = "acdcb";
		String p = "a*c?b";
		boolean expected = false;
		// boolean result = isWildcardMatchingOne(s, p);
		boolean result = isWildcardMatchingTwo(s, p);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 动态规划，构造m*n数组及转移方程
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	private boolean isWildcardMatchingTwo(String s, String p) {

		int m = s.length();
		int n = p.length();
		boolean[][] metrix = new boolean[m + 1][n + 1];// 初始化默认都是false
		metrix[0][0] = true;
		// metrix[i][0]都为false
		// metrix[0][j]，当出现第一个非*字符后也都为false
		for (int j = 1; j <= n; j++) {
			if (p.charAt(j - 1) == '*') {
				metrix[0][j] = true;
			} else {
				break;
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) {
					metrix[i][j] = metrix[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					metrix[i][j] = metrix[i][j - 1] || metrix[i - 1][j];
				} else
					metrix[i][j] = false;
			}
		}

		return metrix[m][n];
	}

	/**
	 * 将p用*分隔，能按顺序在s中匹配上就行,方法有问题，需优化TODO
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	private boolean isWildcardMatchingOne(String s, String p) {

		// TODO 需优化
		if ("".equals(p)) {
			if ("".equals(s))
				return true;
			else
				return false;
		}
		String[] pStrings = p.split("\\*");// 对p进行分割
		System.out.println(pStrings.length);
		char[] sChars = s.toCharArray();
		char[] temp = sChars;
		for (int i = 0; i < pStrings.length; i++) {
			System.out.println(sChars);
			if (!"".equals(pStrings[i]) && (temp = isContains(sChars, pStrings[i])).length == sChars.length) {
				return false;
			}
			sChars = temp;
		}
		if (sChars.length == 0)
			return true;
		else
			return false;
	}

	/**
	 * s从前面开始匹配p，匹配的上，返回true，且匹配后的截取s，否则返回
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	private char[] isContains(char[] s, String p) {

		char[] ps = p.toCharArray();
		int start = 0;
		while (start < ps.length && ps[start] == '?') {
			start++;
		}

		for (int i = 0, j = 0; i < s.length; i++) {
			if (ps[j] == '?' || s[i] == ps[j]) {
				j++;
			} else if (j != start) {
				// j退回到第一个非？处
				j = start;
				i--;
			}
			if (j == ps.length) {
				return Arrays.copyOfRange(s, i + 1, s.length);
			}
		}
		return s;
	}

}
