package top.trial.leetcode.math;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *  
 * 提示：
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/10
 * @Version 1.0
 */
public class Number0973_NClosestPointsToOrigin {
    @Test
    public void solution() {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int K = 2;
        System.out.println(Arrays.deepToString(getClosestPoints(points, K)));
    }

    /**
     * 就是找points里平方和最小的K个
     *
     * @param points int[][]
     * @param K      int
     * @return int[][]
     */
    private int[][] getClosestPoints(int[][] points, int K) {

        int len = points.length;
        if (len == K)
            return points;
        int[][] result = new int[K][];

        //平方和做key，下标集合做value，遍历
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            int num = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (map.containsKey(num))
                map.get(num).add(i);
            else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(num, list);
            }
        }
        //平方和排序，取值
        Iterator<Integer> iterator = map.keySet().stream().sorted().iterator();
        while (K > 0 && iterator.hasNext()) {
            for (int i : map.get(iterator.next()))
                result[--K] = points[i];
        }
        return result;
    }
}
