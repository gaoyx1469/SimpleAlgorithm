package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * 
 * 示例 1:
 * 
 * 输入: "aba" 输出: True
 * 
 * 示例 2:
 * 
 * 输入: "abca" 输出: True 解释: 你可以删除c字符。
 * 
 * 注意:
 * 
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0680_ValidPalindromeII {

	@Test
	public void solution() {
		String str = "abca";
		boolean expected = true;
		boolean result = isValidPalindromeOne(str);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 暴力法没意义，参考官方题解，双指针法，while判断第一层，if判断第二层
	 * 
	 * @param str
	 * @return
	 */
	private boolean isValidPalindromeOne(String str) {
		int start = 0;
		int end = str.length() - 1;
		while (start < end) {
			char s = str.charAt(start);
			char e = str.charAt(end);
			if (s == e) {
				start++;
				end--;
			} else {
				boolean sFlag = true;// start位删除后是否是回文串
				boolean eFlag = true;// end位删除后是否是回文串

				// 对sFlag进行判断
				for (int i = start + 1, j = end; i < j; i++, j--) {
					char ci = str.charAt(i);
					char cj = str.charAt(j);
					if (ci != cj) {
						sFlag = false;
						break;
					}
				}

				// 对Flag进行判断
				for (int i = start, j = end - 1; i < j; i++, j--) {
					char ci = str.charAt(i);
					char cj = str.charAt(j);
					if (ci != cj) {
						eFlag = false;
						break;
					}
				}

				return sFlag || eFlag;
			}
		}

		return true;
	}

}
