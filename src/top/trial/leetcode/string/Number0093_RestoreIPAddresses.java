package top.trial.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/9
 * @Version 1.0
 */
public class Number0093_RestoreIPAddresses {
    @Test
    public void solution() {
        String s = "25525511135";
        List<String> result = restoreIpAddresses(s);
        System.out.println(result.toString());
    }

    private List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12)
            return new ArrayList<>();
        List<String> result = new ArrayList<>();
        fill(result, s, 1, "");
        return result;
    }

    private void fill(List<String> result, String s, int i, String m) {
        if (i == 4 && s.length() < 4 && Integer.parseInt(s) <= 255) {
            if (s.charAt(0) == '0' && s.length() != 1)
                return;
            result.add((m + "." + s).substring(1));
        }
        if (i < 4) {
            if (s.length() > (5 - i) * 3)
                return;
            if (s.length() < (5 - i) * 3 - 1 && s.length() > 1) {
                fill(result, s.substring(1), i + 1, m + "." + s.substring(0, 1));
            }
            if (s.length() < (5 - i) * 3 && s.length() > 2 && s.charAt(0) != '0') {
                fill(result, s.substring(2), i + 1, m + "." + s.substring(0, 2));
            }
            if (s.length() <= (5 - i) * 3 && s.length() > 3 && s.charAt(0) != '0' && Integer.parseInt(s.substring(0, 3)) <= 255) {
                fill(result, s.substring(3), i + 1, m + "." + s.substring(0, 3));
            }
        }
    }
}
