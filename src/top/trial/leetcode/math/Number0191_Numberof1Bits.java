package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * 
 *  
 * 
 * 示例 1：
 * 
 * 输入：00000000000000000000000000001011 输出：3
 * 解释：输入的二进制串00000000000000000000000000001011 中，共有三位为 '1'。
 * 
 * 示例 2：
 * 
 * 输入：00000000000000000000000010000000 输出：1
 * 解释：输入的二进制串00000000000000000000000010000000 中，共有一位为 '1'。
 * 
 * 示例 3：
 * 
 * 输入：11111111111111111111111111111101 输出：31
 * 解释：输入的二进制串11111111111111111111111111111101 中，共有 31 位为 '1'。  
 * 
 * 提示：
 * 
 * 请注意，在某些语言（如Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。  
 * 
 * 进阶: 如果多次调用这个函数，你将如何优化你的算法？
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author Gaoyx
 *
 */
public class Number0191_Numberof1Bits {

	@Test
	public void solution() {
		int num = -3;
		int expected = 31;
		int result = getNumberof1BitsOne(num);
		Assert.assertEquals(expected, result);
	}

	/**
	 * java中使用补码表示数字
	 * 
	 * @param num
	 * @return
	 */
	private int getNumberof1BitsOne(int num) {
		int result = 0;
		int comparedNum = 1;

		for (int i = 0; i < 32; i++) {
			if ((num & comparedNum) != 0)
				result++;
			num >>= 1;// 右移一位
		}

		return result;
	}

	/**
	 * 参考官方题解
	 * 
	 * 在二进制表示中，数字 n中最低位的 1 总是对应 n - 1 中的 0。因此，将 n 和 n - 1 与运算总是能把 n 中最低位的 1 变成
	 * 0，并保持其他位不变。
	 * 
	 * 
	 * @param num
	 * @return
	 */
	private int getNumberof1BitsTwo(int num) {
		int sum = 0;
		while (num != 0) {
			sum++;
			num &= (num - 1);
		}
		return sum;

	}

}
