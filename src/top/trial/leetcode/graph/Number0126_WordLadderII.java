package top.trial.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import top.trial.leetcode.utils.ArrayListUtil;

/**
 * 
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord
 * 的最短转换序列。转换需遵循如下规则：
 * 
 * 每次转换只能改变一个字母。 转换过程中的中间单词必须是字典中的单词。 说明:
 * 
 * 如果不存在这样的转换序列，返回一个空列表。 所有单词具有相同的长度。 所有单词只由小写字母组成。 字典中不存在重复的单词。 你可以假设 beginWord
 * 和 endWord 是非空的，且二者不相同。
 * 
 * 示例 1:
 * 
 * 输入: beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出: [ ["hit","hot","dot","dog","cog"],   ["hit","hot","lot","log","cog"] ]
 * 
 * 示例 2:
 * 
 * 输入: beginWord = "hit" endWord = "cog" wordList =
 * ["hot","dot","dog","lot","log"]
 * 
 * 输出: []
 * 
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/word-ladder-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx-vm
 *
 */
public class Number0126_WordLadderII {

	@Test
	public void solution() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<String>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		List<List<String>> expected = new ArrayList<List<String>>();
		List<String> list1 = new ArrayList<String>();
		list1.add("hit");
		list1.add("hot");
		list1.add("dot");
		list1.add("dog");
		list1.add("cog");
		List<String> list2 = new ArrayList<String>();
		list2.add("hit");
		list2.add("hot");
		list2.add("lot");
		list2.add("log");
		list2.add("cog");
		expected.add(list1);
		expected.add(list2);

		List<List<String>> result = getWordLadderOne(beginWord, endWord, wordList);

		ArrayListUtil.getTwoDimensionalSysoLikeArray(expected);
		ArrayListUtil.getTwoDimensionalSysoLikeArray(result);

	}

	/**
	 * 将wordList中单词全部列出，并将单词间仅有一个字母不同的所有单词对相连，形成图，然后找到beginWord到endWord的所有最短路径
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	private List<List<String>> getWordLadderOne(String beginWord, String endWord, List<String> wordList) {
		return null;
	}

}
