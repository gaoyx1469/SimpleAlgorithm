package top.trial.leetcode.utils;

import java.util.List;

/**
 * ArrayList工具类
 * 
 * @author Gaoyx-vm
 *
 */

public class ArrayListUtil {

	/**
	 * 对List进行一维输出
	 * 
	 * @param list
	 * @return
	 */
	public static void getOneDimensionalSysoLikeArray(List<String> list) {

		StringBuffer output = new StringBuffer();
		Boolean isFirstElement = true;

		if (list == null || list.isEmpty())
			output.append("[]");
		else {
			output.append("[");
			for (String str : list) {
				if (!isFirstElement) {
					output.append(",");
					isFirstElement = false;
				}
				output.append("\"").append(str).append("\"");

			}
			output.append("]");
		}

		System.out.println(output);
	}

	/**
	 * 获取list的一维数组形式String
	 * 
	 * @param list
	 * @return
	 */
	public static String getOneDimensionalStringLikeArray(List<String> list) {

		StringBuffer output = new StringBuffer();
		Boolean isFirstElement = true;

		if (list == null || list.isEmpty())
			output.append("[]");
		else {
			output.append("[");
			for (String str : list) {
				if (!isFirstElement) {
					output.append(",");
					isFirstElement = false;
				}
				output.append("\"").append(str).append("\"");

			}
			output.append("]");
		}

		return output.toString();
	}

	/**
	 * 对List进行二维输出
	 * 
	 * @param list
	 * @return
	 */
	public static void getTwoDimensionalSysoLikeArray(List<List<String>> lists) {

		Boolean isFirstElement = true;

		if (lists == null || lists.isEmpty())
			System.out.println("[]");
		else {
			System.out.println("[");
			for (List<String> list : lists) {

				if (isFirstElement)
					isFirstElement = false;
				else
					System.out.println(",");
				System.out.print(" " + getOneDimensionalStringLikeArray(list));
			}
			System.out.println();
			System.out.println("]");
		}

	}

}
