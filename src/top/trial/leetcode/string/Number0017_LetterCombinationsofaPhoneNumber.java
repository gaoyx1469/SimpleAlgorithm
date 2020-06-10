package top.trial.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import top.trial.leetcode.utils.ArrayListUtil;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 示例:
 * 
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 说明: 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0017_LetterCombinationsofaPhoneNumber {

	@Test
	public void solution() {
		String digits = "23";
		List<String> result = getLetterCombinationsofaPhoneNumberOne(digits);
		ArrayListUtil.getOneDimensionalSysoLikeArray(result);
	}

	/**
	 * 每个数字都有对应字母，直接暴力法
	 * 
	 * @param digits
	 * @return
	 */
	private List<String> getLetterCombinationsofaPhoneNumberOne(String digits) {

		// 初始化键位对应字母
		Map<String, String> map = new HashMap<String, String>();
		map.put("2", "abc");
		map.put("3", "def");
		map.put("4", "ghi");
		map.put("5", "jkl");
		map.put("6", "mno");
		map.put("7", "pqrs");
		map.put("8", "tuv");
		map.put("9", "wxyz");

		List<String> result = new ArrayList<String>();
		if ("".equals(digits))// 此处判断防止出现digits为空字符串时List<String>中存在一个值为空字符串的元素
			return result;
		getLetterCombinations("", digits, map, result);

		return result;
	}

	private void getLetterCombinations(String str, String digits, Map<String, String> map, List<String> result) {

		// digits是空字符串，则str为最终结果
		if ("".equals(digits))
			result.add(str);
		else {
			// 取digits第一个字符
			String firstNum = digits.substring(0, 1);
			String chars = map.get(firstNum);
			for (int i = 0; i < chars.length(); i++) {
				getLetterCombinations(str + chars.substring(i, i + 1), digits.substring(1), map, result);
			}
		}
	}

}
