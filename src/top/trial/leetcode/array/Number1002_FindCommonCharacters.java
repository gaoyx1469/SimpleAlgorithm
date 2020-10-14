package top.trial.leetcode.array;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 示例 1：
 * <p>
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * <p>
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/14
 * @Version 1.0
 */
public class Number1002_FindCommonCharacters {
    @Test
    public void solution() {
        String[] A = {"bella", "label", "roller"};
        ArrayListUtil.getOneDimensionalSysoLikeArray(commonCharsTwo(A));
    }

    //commonCharsOne26个字母，遍历了26次，每个字母又遍历了全部的字符串，实际上字符串遍历一次记住结果就好，因此进行如下改进。
    private List<String> commonCharsTwo(String[] A) {
        int[] minNum = new int[26];
        Arrays.fill(minNum, Integer.MAX_VALUE);
        for (String str : A) {
            int[] num = new int[26];
            char[] chars = str.toCharArray();
            for (char c : chars)
                ++num[c - 'a'];
            for (int i = 0; i < 26; ++i)
                minNum[i] = Math.min(minNum[i], num[i]);
        }

        List<String> results = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < minNum[i]; ++j)
                results.add(String.valueOf((char) ('a' + i)));
        }
        return results;
    }

    private List<String> commonCharsOne(String[] A) {
        String str = A[0];
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            char ch = (char) ('a' + i);
            if (str.indexOf(ch) != -1) {
                int num = Integer.MAX_VALUE;
                for (String s : A) {
                    int numC = 0;
                    char[] chars = s.toCharArray();
                    for (char c : chars) {
                        if (c == ch)
                            numC++;
                    }
                    num = Math.min(num, numC);
                }
                if (num > 0) {
                    for (int j = 0; j < num; ++j)
                        results.add(ch + "");
                }
            }
        }
        return results;
    }
}
