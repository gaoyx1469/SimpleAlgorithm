package top.trial.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import top.trial.leetcode.tree.LowerCaseTrie;

/**
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still
 * didn’t
 * boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * 
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * 
 * 示例：
 * 
 * 输入：
 * 
 * dictionary = ["looked","just","like","her","brother"]
 * 
 * sentence = "jesslookedjustliketimherbrother"
 * 
 * 输出： 7
 * 
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 
 * 提示：
 * 
 * 0 <= len(sentence) <= 1000
 * 
 * dictionary中总字符数不超过 150000。
 * 
 * 你可以认为dictionary和sentence中只包含小写字母。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/re-space-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberF1713_ReSpaceLCCI {

	@Test
	public void solution() {
		String sentence = "jesslookedjustliketimherbrother";
		String[] dictionary = { "looked", "just", "like", "her", "brother" };
		int expected = 7;
//		int result = reSpaceLCCIOne(sentence, dictionary);
		int result = reSpaceLCCITwo(sentence, dictionary);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 参考官方题解，one超时了，使用字典树优化
	 * 
	 * @param sentence
	 * @param dictionary
	 * @return
	 */
	private int reSpaceLCCITwo(String sentence, String[] dictionary) {

		int len = sentence.length();

		// 将方法one中dictionary放入ArrayList改为放入字典树
		LowerCaseTrie root = new LowerCaseTrie();
		for (String word : dictionary) {
			root.insert(word);
		}

		int[] result = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			result[i] = result[i - 1] + 1;// 当前最小？

			// 将方法one中循环前面所有可能并在ArrayList里查找的动作改为在字典树查找
			LowerCaseTrie curPos = root;
			for (int j = i; j >= 1; --j) {
				int end = sentence.charAt(j - 1) - 'a';
				if (curPos.next[end] == null) {
					break;
				} else if (curPos.next[end].isEnd) {
					result[i] = Math.min(result[i], result[j - 1]);
					if (result[i] == 0)
						break;
				}
				// 这个放外面，因为可能到了isEnd为true但是后面依然会有更长的匹配串
				curPos = curPos.next[end];

			}

		}
		System.out.println(Arrays.toString(result));
		return result[len];
	}

	/**
	 * 动态规划？ 状态转移方程？n^2的时间复杂度啊，妥妥超时
	 * 
	 * @param sentence
	 * @param dictionary
	 * @return
	 */
	private int reSpaceLCCIOne(String sentence, String[] dictionary) {

		int len = sentence.length();
		List<String> dics = Arrays.asList(dictionary);
		int[] result = new int[len + 1];

		for (int i = 1; i <= len; i++) {
			result[i] = result[i - 1] + 1;// 当前最小？
			String s = sentence.substring(0, i);
			for (int j = 0; j < i; j++) {
				if (dics.contains(s.substring(j))) {
					result[i] = Math.min(result[i], result[j]);
				}
			}
		}
		System.out.println(Arrays.toString(result));
		return result[len];
	}
}
