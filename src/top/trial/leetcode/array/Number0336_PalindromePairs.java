package top.trial.leetcode.array;

import org.junit.Test;
import top.trial.leetcode.utils.ArrayListUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 * <p>
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/6
 * @Version 1.0
 */
public class Number0336_PalindromePairs {
    @Test
    public void solution() {

        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        //List<List<Integer>> result = palindromePairsOne(words);
        List<List<Integer>> result = palindromePairsTwo(words);
        ArrayListUtil.getTwoDimensionalSysoLikeArray(result);
    }

    /**
     * 由所有字符串两次遍历，变为字符串一次遍历+串内字符一次遍历
     * 参考官方题解
     *
     * @param words String[]
     * @return List<List < Integer>>
     */
    private List<List<Integer>> palindromePairsTwo(String[] words) {

        List<List<Integer>> result = new ArrayList<>();
        HashMap<String, Integer> reverses = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            StringBuilder sb = new StringBuilder(words[i]);
            reverses.put(sb.reverse().toString(), i);
        }


        for (int i = 0; i < words.length; ++i) {
            if (words[i].length() == 0)
                continue;
            for (int j = 0; j <= words[i].length(); ++j) {
                if (j != 0 && isPalindromePairs(words[i].substring(0, j)) && reverses.containsKey(words[i].substring(j)) && i != reverses.get(words[i].substring(j))) {
                    List<Integer> list = new ArrayList<>();
                    list.add(reverses.get(words[i].substring(j)));
                    list.add(i);
                    result.add(list);
                }
                if (isPalindromePairs(words[i].substring(j)) && reverses.containsKey(words[i].substring(0, j)) && i != reverses.get(words[i].substring(0, j))) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(reverses.get(words[i].substring(0, j)));
                    result.add(list);
                }
            }
        }
        return result;
    }

    private boolean isPalindromePairs(String word) {
        if (word == null || "".equals(word)) {
            return true;
        }
        int len = word.length();
        String str1 = word.substring(0, len / 2);
        StringBuffer str2 = len % 2 == 0 ? new StringBuffer(word.substring(len / 2)) : new StringBuffer(word.substring(len / 2 + 1));
        str2 = str2.reverse();
        return str1.equals(str2.toString());
    }

    /**
     * 暴力法，果然超时了~
     *
     * @param words String[]
     * @return List<List < Integer>>
     */
    private List<List<Integer>> palindromePairsOne(String[] words) {

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < words.length; ++j) {
                if (i != j && isPalindromePairs(words[i], words[j])) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(j);
                    result.add(pair);
                }
            }
        }
        return result;
    }

    private boolean isPalindromePairs(String word1, String word2) {
        return isPalindromePairs(word1 + word2);
    }
}
