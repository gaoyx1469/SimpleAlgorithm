package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;


/**
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * <p>
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * <p>
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 * <p>
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * <p>
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * @Author gaoyx1469
 * @Date 2020/7/22
 * @Version 1.0
 */
public class NumberF1626_CalculatorLCCI {

    @Test
    public void solution() {
        String[] s = {"3+2*2", " 3/2 ", " 3+5 / 2 "};
        int[] expected = {7, 1, 5};
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; ++i)
            result[i] = calculatorOne(s[i]);
        Assert.assertArrayEquals(result, expected);
    }

    private int calculatorOne(String s) {
        int result = 0;
        s = s + "+";
        Stack<Integer> stack = new Stack<>();//存由+/-分隔开的数据

        int curr = 0;//当前数值
        int front = 1;//乘基
        boolean symbol = true;//true:+;false:-
        int symbols = 0;//0：无   1：*  2：/

        for (int i = 0; i < s.length(); ++i) {

            if (s.charAt(i) == ' ')
                continue;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (symbols == 0) {
                    front = curr;
                } else if (symbols == 1) {
                    front *= curr;
                } else {
                    front /= curr;
                }
                //前面的数入栈，然后清空
                stack.push(symbol ? front : front * -1);
                curr = 0;
                front = 1;
                symbol = s.charAt(i) == '+';
                symbols = 0;
            } else if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                //前面的数暂存，符号暂存
                if (symbols == 0) {
                    front = curr;
                } else if (symbols == 1) {
                    front *= curr;
                } else {
                    front /= curr;
                }
                curr = 0;
                symbols = s.charAt(i) == '*' ? 1 : 2;
            } else {
                //当前数暂存
                curr = curr * 10 + Integer.parseInt(s.substring(i, i + 1));
            }


        }
        for (Integer i : stack)
            result += i;
        return result;
    }
}
