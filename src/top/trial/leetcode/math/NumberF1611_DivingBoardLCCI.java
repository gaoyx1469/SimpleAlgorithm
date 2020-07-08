package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * 
 * 返回的长度需要从小到大排列。
 * 
 * 示例：
 * 
 * 输入： shorter = 1 longer = 2 k = 3 输出： {3,4,5,6}
 * 
 * 提示：
 * 
 * 0 < shorter <= longer
 * 
 * 0 <= k <= 100000
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/diving-board-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class NumberF1611_DivingBoardLCCI {

	@Test
	public void solution() {
		int shorter = 1;
		int longer = 2;
		int k = 3;
		int[] expecteds = { 3, 4, 5, 6 };
		int[] results = divingBoardLCCIOne(shorter, longer, k);
		Assert.assertArrayEquals(expecteds, results);
	}

	/**
	 * 数学方式
	 * 
	 * @param shorter
	 * @param longer
	 * @param k
	 * @return
	 */
	private int[] divingBoardLCCIOne(int shorter, int longer, int k) {

		if (k == 0) {
			return new int[0];
		}

		if (shorter == longer) {
			return new int[] { shorter * k };
		} 
		
		int diff = longer - shorter;
		int min = shorter * k;
		int[] result = new int[k + 1];
		for (int i = 0; i <= k; i++, min += diff) {
			result[i] = min;
		}
		return result;
		
	}

}
