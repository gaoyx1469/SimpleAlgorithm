package top.trial.leetcode.string;

import org.junit.Test;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * 
 * 示例 1:
 * 
 * 输入: "aacecaaa" 输出: "aaacecaaa" 示例 2:
 * 
 * 输入: "abcd" 输出: "dcbabcd"
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shortest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0214_ShortedPalindrome {
	
	@Test
	public void solition() {
		String s = "aacecaaa";
		System.out.println(shortestPalindromeOne(s));
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	private String shortestPalindromeOne(String s) {
		int n = s.length();
        int base = 131, mod = 1000000007;
        int left = 0, right = 0, mul = 1;
        int best = -1;
        for (int i = 0; i < n; ++i) {
            left = (int) (((long) left * base + s.charAt(i)) % mod);
            right = (int) ((right + (long) mul * s.charAt(i)) % mod);
            if (left == right) {
                best = i;
            }
            mul = (int) ((long) mul * base % mod);
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }

}
