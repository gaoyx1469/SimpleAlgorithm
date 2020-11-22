package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/22
 * @Version 1.0
 */
public class Number0242_ValidAnagram {
    @Test
    public void solution() {
        String s = "anagram";
        String t = "nagaram";
        Assert.assertTrue(isAnagram(s, t));
    }

    private boolean isAnagram(String s, String t) {

        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] times = new int[26];

        for (char c : sc)
            times[c - 'a']++;

        for (char c : tc)
            times[c - 'a']--;

        for (int i : times) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}
