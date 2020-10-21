package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * <p>
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 * 示例 3：
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 * 示例 4：
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *  
 * 提示：
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/long-pressed-name
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/21
 * @Version 1.0
 */
public class Number0925_LongPressedName {
    @Test
    public void solution() {
        String name = "alex";
        String typed = "aaleex";
        Assert.assertTrue(isLongPressedNameOne(name, typed));
    }

    private boolean isLongPressedNameOne(String name, String typed) {

        int nameLen = name.length();
        int typedLen = typed.length();
        if (nameLen > typedLen)
            return false;
        int nameCurr = 0;
        int typedCurr = 0;
        while (nameCurr < nameLen && typedCurr < typedLen) {
            if (name.charAt(nameCurr) == typed.charAt(typedCurr)) {
                nameCurr++;
                typedCurr++;
            } else if (typedCurr > 0 && typed.charAt(typedCurr) == typed.charAt(typedCurr - 1)) {
                typedCurr++;
            } else
                return false;
        }
        if (nameCurr == nameLen) {
            while (typedCurr < typedLen) {
                if (typedCurr > 0 && typed.charAt(typedCurr) == typed.charAt(typedCurr - 1))
                    typedCurr++;
                else
                    return false;
            }
        } else
            return false;
        return true;
    }
}
