package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 *  
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/14
 * @Version 1.0
 */
public class Number0120_Triangle {

    @Test
    public void solution() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> tri1 = new ArrayList<>();
        tri1.add(2);
        List<Integer> tri2 = new ArrayList<>();
        tri2.add(3);
        tri2.add(4);
        List<Integer> tri3 = new ArrayList<>();
        tri3.add(6);
        tri3.add(5);
        tri3.add(7);
        List<Integer> tri4 = new ArrayList<>();
        tri4.add(4);
        tri4.add(1);
        tri4.add(8);
        tri4.add(3);
        triangle.add(tri1);
        triangle.add(tri2);
        triangle.add(tri3);
        triangle.add(tri4);

        int expected = 11;
        //  int result = triangleOne(triangle);
        int result = triangleTwo(triangle);

        Assert.assertEquals(expected, result);
    }

    /**
     * 对方法ONE的改进，因为方法one后一行只与前一行有关，因此不用构造二维List，一维就行
     * <p>
     * 另外，一位定长，因此可以直接构造数组而不用List
     *
     * @param triangle 三角阵
     * @return 最小路径和
     */
    private int triangleTwo(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] result = new int[len + 2];
        result[0] = Integer.MAX_VALUE;
        result[1] = 0;
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j > 0; --j) {
                result[j] = Math.min(result[j - 1], result[j]) + triangle.get(i).get(j - 1);
            }
            result[i + 2] = Integer.MAX_VALUE;
        }


        int returnVal = result[0];
        for (int i : result)
            returnVal = Math.min(returnVal, i);
        return returnVal;
    }

    /**
     * 明显动态规划
     * <p>
     * 常规方法是构造同等大小三角阵
     *
     * @param triangle 三角阵
     * @return 最小路径和
     */
    private int triangleOne(List<List<Integer>> triangle) {

        int len = triangle.size();
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(0);
        results.add(result);
        for (int i = 0; i < len; ++i) {
            result = new ArrayList<>();
            result.add(Integer.MAX_VALUE);
            for (int j = 0; j <= i; ++j) {
                result.add(Math.min(results.get(i).get(j), results.get(i).get(j + 1)) + triangle.get(i).get(j));
            }
            result.add(Integer.MAX_VALUE);
            results.add(result);
        }


        int returnVal = Integer.MAX_VALUE;
        for (int i : results.get(len))
            returnVal = Math.min(returnVal, i);

        return returnVal;
    }

}
