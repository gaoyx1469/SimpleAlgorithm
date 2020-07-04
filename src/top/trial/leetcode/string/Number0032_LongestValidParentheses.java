package top.trial.leetcode.string;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 
 * 示例 1:
 * 
 * 输入: "(()" 输出: 2 解释: 最长有效括号子串为 "()"
 * 
 * 示例 2:
 * 
 * 输入: ")()())" 输出: 4 解释: 最长有效括号子串为 "()()"
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx-vm
 *
 */
public class Number0032_LongestValidParentheses {

	@Test
	public void solution() {
		String s = ")()())";
		int expected = 4;
		int result = getLongestValidParenthesesOne(s);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 使用栈
	 * 
	 * @param s
	 * @return
	 */
	private int getLongestValidParenthesesOne(String s) {

		int result = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.empty()) {
					stack.push(i);
				} else {
					result = Math.max(result, i - stack.peek());
				}
			}
		}
		return result;

	}

	/**
	 * 使用动态规划，状态转移方程dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2
	 * 
	 * @param s
	 * @return
	 */
	private int getLongestValidParenthesesTwo(String s) {
		// TODO
		return 0;

	}

	/**
	 * 正反双向遍历法
	 * 
	 * @param s
	 * @return
	 */
	private int getLongestValidParenthesesThree(String s) {
		// TODO
		return 0;

	}

}
