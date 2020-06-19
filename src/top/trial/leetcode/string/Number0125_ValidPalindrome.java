package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 
 * 示例 1:
 * 
 * 输入: "A man, a plan, a canal: Panama" 输出: true
 * 
 * 示例 2:
 * 
 * 输入: "race a car" 输出: false
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0125_ValidPalindrome {

	@Test
	public void solution() {
		String s = "A man, a plan, a canal: Panama";
		boolean expected = true;
		boolean result = isValidPalindromeOne(s);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 头尾双指针，遇到非字母跳过
	 * 
	 * 参考官方题解之后，发现可改进的点：Character类中有判断是字母或数字的方法，也有将字母变为小写的方法
	 * 
	 * @param s
	 * @return
	 */
	private boolean isValidPalindromeOne(String s) {

		boolean result = true;

		char[] chars = s.toCharArray();
		int start = 0;
		int end = chars.length - 1;

		while (start < end) {

			if (!(chars[start] >= 'a' && chars[start] <= 'z') && !((chars[start] >= 'A' && chars[start] <= 'Z'))
					&& !((chars[start] >= '0' && chars[start] <= '9'))) {
				System.out.println(chars[start] + "---start++---");
				start++;
				continue;
			}
			if (!(chars[end] >= 'a' && chars[end] <= 'z') && !((chars[end] >= 'A' && chars[end] <= 'Z'))
					&& !((chars[end] >= '0' && chars[end] <= '9'))) {
				System.out.println(chars[end] + "---end-----");
				end--;
				continue;
			}
			if ((chars[start] + "").equalsIgnoreCase(chars[end] + "")) {
				System.out.println(chars[start] + "---==-----" + chars[end]);
				start++;
				end--;
			} else {
				System.out.println(chars[start] + "---break-----" + chars[end]);
				return false;
			}

		}
		return result;
	}

}
