package top.trial.leetcode.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * <p>
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",返回它的长度 5。
 * <p>
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/5
 * @Version 1.0
 */
public class Number0127_WordLadder {

    @Test
    public void solution() {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        int expected = 5;
        int result = ladderLength(beginWord, endWord, wordList);
        Assert.assertEquals(expected, result);
    }

    /**
     * 将字典中所有两个仅有一个字母不同的单词连线，形成图结构，从beginWord开始广度优先搜索，先到达endWord的步数是最小步数
     * 需要记录每个单次是否被访问
     *
     * @param beginWord String
     * @param endWord   String
     * @param wordList  String[]
     * @return int
     */
    private int ladderLength(String beginWord, String endWord, String[] wordList) {

        boolean haveEndWordFlag = false;

        for (String word : wordList) {
            if (endWord.equals(word)) {
                haveEndWordFlag = true;
                break;
            }
        }
        if (!haveEndWordFlag) {
            return 0;
        }

        Set<String> visitSet = new HashSet<>();
        boolean noChangeFlag = false;
        int result = 1;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while (!noChangeFlag) {

            //从起始点循环遍历，得到与之相连的且未访问的点，若没有，noChangeFlag为false，结束循环
            //若有，判断是否有endWord，若有，计算步数，结束循环，若没有，步数+1，继续循环
            Queue<String> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                String curr = queue.remove();
                for (String word : wordList) {
                    if (!visitSet.contains(word) && isLinked(word, curr)) {
                        tempQueue.add(word);
                        visitSet.add(word);
                    }
                }
            }

            if (tempQueue.isEmpty())
                noChangeFlag = true;
            else {
                result++;
                if (tempQueue.contains(endWord))
                    break;
                else
                    queue = tempQueue;
            }
        }
        if (noChangeFlag)
            return 0;
        else
            return result;
    }

    //判断两个字符串是否只相差一个字符
    private boolean isLinked(String word, String curr) {
        char[] wordChars = word.toCharArray();
        char[] currChars = curr.toCharArray();
        int len = word.length();
        int count = 0;
        for (int i = 0; i < len; ++i) {
            if (wordChars[i] != currChars[i])
                count++;
            if (count > 1)
                return false;
        }
        return true;
    }

}
