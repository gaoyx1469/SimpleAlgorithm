package top.trial.leetcode.utils;

/**
 * 格式化小工具
 * 
 * @author Gaoyx
 *
 */
public class FormatUtil {

	/**
	 * 为字符串增加方括号
	 * 
	 * @param str
	 * @return
	 */
	public static String addSquareBrackets(String str) {
		if (str == null || "".equals(str))
			return "[]";
		return "[" + str + "]";
	}

	/**
	 * 为字符串增加方括号，并去掉最后一个多余的分隔符
	 * 
	 * @param str
	 * @return
	 */
	public static String addSquareBracketsPlus(String str) {
		if (str == null || "".equals(str))
			return "[]";
		return "[" + str.substring(0, str.length() - 1) + "]";
	}

	/**
	 * 为字符串增加花括号
	 * 
	 * @param str
	 * @return
	 */
	public static String addCurlyBraces(String str) {
		if (str == null || "".equals(str))
			return "{}";
		return "{" + str + "}";
	}

	/**
	 * 为字符串增加花括号，并去掉最后一个多余的分隔符
	 * 
	 * @param str
	 * @return
	 */
	public static String addCurlyBracesPlus(String str) {
		if (str == null || "".equals(str))
			return "{}";
		return "{" + str.substring(0, str.length() - 1) + "}";
	}

}
