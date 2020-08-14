package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/14
 * @Version 1.0
 */
public class Number0020_ValidParentheses {
    @Test
    public void solution() {
        String s = "([)]";
        Assert.assertFalse(validParentheses(s));
    }

    /**
     * 括号问题，是不是用栈就行
     *
     * @param s String
     * @return boolean
     */
    private boolean validParentheses(String s) {

        if (s == null)
            return false;
        int len = s.length();
        if (len == 0)
            return true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; ++i) {

            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.push(s.charAt(i));
            else if (s.charAt(i) == ')') {
                if (!stack.empty() && stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            } else if (s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (!stack.empty() && s.charAt(i) - stack.peek() == 2)
                    stack.pop();
                else
                    return false;
            }
            System.out.println(stack.empty());
        }

        return stack.empty();
    }
}
