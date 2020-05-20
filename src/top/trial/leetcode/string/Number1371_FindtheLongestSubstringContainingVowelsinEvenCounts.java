package top.trial.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u'
 * ，在子字符串中都恰好出现了偶数次。
 * 
 * 示例 1：
 * 
 * 输入：s = "eleetminicoworoep" 输出：13 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2
 * 个，以及 0 个 a，u 。
 * 
 * 示例 2：
 * 
 * 输入：s = "leetcodeisgreat" 输出：5 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 
 * 示例 3：
 * 
 * 输入：s = "bcbcbc" 输出：6 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0
 * 次。  
 * 
 * 提示：
 * 
 * 1 <= s.length <= 5 x 10^5 s 只包含小写英文字母。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number1371_FindtheLongestSubstringContainingVowelsinEvenCounts {

	@Test
	public void solution() {
		String s = "eleetminicoworoep";
		int expected = 13;
		int result = getLongestSubstringOne(s);
		Assert.assertEquals(expected, result);
	}

	/**
	 * 没想到解法，参考官方题解
	 * 
	 * @param s
	 * @return
	 */
	private int getLongestSubstringOne(String s) {
		// TODO Auto-generated method stub

		return 0;
	}
}
