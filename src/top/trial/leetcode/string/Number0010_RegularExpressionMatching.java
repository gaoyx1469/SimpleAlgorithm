package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 
 * '.' 匹配任意单个字符 '*' 匹配零个或多个前面的那一个元素
 * 
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * 
 * 说明:
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 
 * 示例 1:
 * 
 * 输入: s = "aa" p = "a" 输出: false 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 示例 2:
 * 
 * 输入: s = "aa" p = "a*" 输出: true 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是
 * 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 
 * 示例 3:
 * 
 * 输入: s = "ab" p = ".*" 输出: true 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 
 * 示例 4:
 * 
 * 输入: s = "aab" p = "c*a*b" 输出: true 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a'
 * 被重复一次。因此可以匹配字符串 "aab"。
 * 
 * 示例 5:
 * 
 * 输入: s = "mississippi" p = "mis*is*p*." 输出: false
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0010_RegularExpressionMatching {

	@Test
	public void solution() {
		String s = "mississippi";
		String p = "mis*is*p*.";
		boolean expected = false;
		boolean result = isRegularExpressionMatchingOne(s, p);
		// boolean result = isRegularExpressionMatchingTwo(s, p);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 参考官方题解，动态规划结合回溯求解，动态规划数据存二维数组
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	private boolean isRegularExpressionMatchingTwo(String s, String p) {
		return false;
	}

	/**
	 * 参考官方题解，回溯算法
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	private boolean isRegularExpressionMatchingOne(String s, String p) {

		// 若模式串为空，返回结果为匹配串是否为空
		if (p.isEmpty())
			return s.isEmpty();

		// 若模式串不为空
		// 取模式串第一位，看是否与匹配串第一位一致，【要求匹配串不为空】
		boolean firstCharMatch = (!s.isEmpty()) && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));

		// 当模式串第二位是*，忽略模式串前两位或者在匹配串第一位能匹配的情况下忽略匹配串第一位
		if (p.length() >= 2 && p.charAt(1) == '*')
			return isRegularExpressionMatchingOne(s, p.substring(2))
					|| (firstCharMatch && isRegularExpressionMatchingOne(s.substring(1), p));
		else// 当模式串第二位不是*，则要求第一位匹配且第二位开始也能匹配
			return firstCharMatch && isRegularExpressionMatchingOne(s.substring(1), p.substring(1));

	}
}
