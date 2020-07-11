package top.trial.leetcode.utils;

import java.util.List;

/**
 * ArrayList工具类
 *
 * @author Gaoyx-vm
 */

public class ArrayListUtil {

    /**
     * 对List进行一维输出
     *
     * @param list
     * @return
     */
    public static <T> void getOneDimensionalSysoLikeArray(List<T> list) {

        StringBuffer output = new StringBuffer();
        Boolean isFirstElement = true;

        if (list == null || list.isEmpty())
            output.append("[]");
        else {
            output.append("[");
            for (T str : list) {
                if (isFirstElement)
                    isFirstElement = false;
                else
                    output.append(",");
                output.append("\"").append(str.toString()).append("\"");

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
    public static <T> String getOneDimensionalStringLikeArray(List<T> list) {

        StringBuffer output = new StringBuffer();
        Boolean isFirstElement = true;

        if (list == null || list.isEmpty())
            output.append("[]");
        else {
            output.append("[");
            for (T str : list) {
                if (isFirstElement)
                    isFirstElement = false;
                else
                    output.append(",");
                output.append("\"").append(str.toString()).append("\"");

            }
            output.append("]");
        }

        return output.toString();
    }

    /**
     * 对List进行二维输出
     *
     * @param lists
     * @return
     */
    public static <T> void getTwoDimensionalSysoLikeArray(List<List<T>> lists) {

        Boolean isFirstElement = true;

        if (lists == null || lists.isEmpty())
            System.out.println("[]");
        else {
            System.out.println("[");
            for (List<T> list : lists) {

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
