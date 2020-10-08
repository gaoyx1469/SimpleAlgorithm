package top.trial.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * <p>
 * 示例 1：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/8
 * @Version 1.0
 */
public class Number0344_ReverseString {
    @Test
    public void solution() {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseStringOne(s);
        System.out.println(Arrays.toString(s));
    }

    private void reverseStringOne(char[] s) {
        int len = s.length;
        if (len <= 1)
            return;
        char temp;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            if (s[start] != s[end]) {
                temp = s[start];
                s[start] = s[end];
                s[end] = temp;
            }
            start++;
            end--;
        }
    }
}
